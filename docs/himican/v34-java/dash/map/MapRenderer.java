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
import androidx.appcompat.app.AppCompatDelegate;
import androidx.autofill.HintConstants;
import androidx.compose.runtime.ComposerKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.TrafficInterval;
import com.example.wayfindr.dash.nav.TrafficSpeed;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import okhttp3.internal.ws.WebSocketProtocol;
import org.maplibre.turf.TurfConstants;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: MapRenderer.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0010\b\u0007\u0018\u0000 f2\u00020\u0001:\u0003defB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001f2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020#0&H\u0082\bJ*\u0010%\u001a\u00020#2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\b\b\u0002\u0010H\u001a\u00020\u00072\b\b\u0002\u0010I\u001a\u00020\u0007JF\u0010J\u001a\u00020#2\u0006\u0010D\u001a\u00020E2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u00072\u0006\u0010K\u001a\u00020\u001f2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0M2\u0006\u0010O\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u001fH\u0002J.\u0010Q\u001a\u00020#2\u0006\u0010D\u001a\u00020E2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u00072\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u001f0MH\u0002J \u0010S\u001a\u00020\u001f2\u0006\u0010T\u001a\u00020\u001f2\u0006\u0010U\u001a\u00020\u000f2\u0006\u0010V\u001a\u00020WH\u0002J8\u0010X\u001a\u00020#2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u00072\u0006\u0010Y\u001a\u00020W2\u0006\u0010Z\u001a\u00020W2\u0006\u0010[\u001a\u00020W2\u0006\u0010\\\u001a\u000200H\u0002J\u0010\u0010]\u001a\u00020W2\u0006\u0010^\u001a\u00020WH\u0002J0\u0010_\u001a\u00020#2\u0006\u0010D\u001a\u00020E2\u0006\u0010`\u001a\u00020\u00072\u0006\u0010a\u001a\u00020\u00072\u0006\u0010b\u001a\u00020\u00072\u0006\u0010c\u001a\u000200H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u001d\u001a&\u0012\f\u0012\n  *\u0004\u0018\u00010\u001f0\u001f  *\u0012\u0012\f\u0012\n  *\u0004\u0018\u00010\u001f0\u001f\u0018\u00010!0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020BX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006g"}, d2 = {"Lcom/example/wayfindr/dash/map/MapRenderer;", "", "tiles", "Lcom/example/wayfindr/dash/map/TileProvider;", "<init>", "(Lcom/example/wayfindr/dash/map/TileProvider;)V", "lightBg", "", "darkBg", "routeBlue", "trafficAmber", "trafficRed", "googleRed", "batteryYellow", "tilePaint", "Landroid/graphics/Paint;", "lightTileFilter", "Landroid/graphics/ColorMatrixColorFilter;", "darkTileFilter", "routeCasing", "routePaint", "dotPaint", "textPaint", "subTextPaint", "bannerPaint", "standbyPaint", "etaBgPaint", "etaBorderPaint", "etaBigPaint", "failedOverlays", "", "", "kotlin.jvm.PlatformType", "", "overlay", "", HintConstants.AUTOFILL_HINT_NAME, "draw", "Lkotlin/Function0;", "attributionPaint", "boltPath", "Landroid/graphics/Path;", "boltPaint", "etaSmallPaint", "gpsPillText", "routePath", "riderPath", "tmpRect", "Landroid/graphics/RectF;", "pillRect", "pickerBgPaint", "pickerHiPaint", "pickerTitlePaint", "pickerItemPaint", "pickerHintPaint", "pickerRow", "routingBadgePaint", "pickerSectionPaint", "pickerMarkPaint", "tileSrc", "Landroid/graphics/Rect;", "childDst", "textBounds", "tiltMatrix", "Landroid/graphics/Matrix;", "tiltSrc", "", "tiltDst", "canvas", "Landroid/graphics/Canvas;", "f", "Lcom/example/wayfindr/dash/map/MapRenderer$Frame;", "w", "h", "drawPicker", "title", "items", "", "Lcom/example/wayfindr/dash/map/MapRenderer$MenuRow;", "index", "selectHint", "drawNotice", "lines", "ellipsize", "s", "p", "maxW", "", "placeInSafeCircle", "bw", "bh", "angleDeg", "out", "normalizeHeading", TurfConstants.UNIT_DEGREES, "drawTileBestEffort", "z", "tx", "ty", "dst", "Frame", "MenuRow", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
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
    private final Set<String> failedOverlays;
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

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final int ROUTING_GREEN = Color.rgb(30, 142, 62);
    private static final int ROUTING_ORANGE = Color.rgb(239, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 0);
    private static final int ROUTING_BROWN = Color.rgb(121, 85, 72);
    private static final int ACTIVE_GREEN = Color.rgb(96, 205, 128);

    /* compiled from: MapRenderer.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TrafficSpeed.values().length];
            try {
                iArr[TrafficSpeed.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TrafficSpeed.SLOW.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TrafficSpeed.TRAFFIC_JAM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TrafficSpeed.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[RoutingSource.values().length];
            try {
                iArr2[RoutingSource.GOOGLE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[RoutingSource.OSM.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[RoutingSource.BROUTER.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public MapRenderer(TileProvider tiles) {
        Intrinsics.checkNotNullParameter(tiles, "tiles");
        this.tiles = tiles;
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
        this.darkTileFilter = new ColorMatrixColorFilter(INSTANCE.nightTileMatrix());
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
        paint9.setColor(Color.rgb(WebSocketProtocol.PAYLOAD_SHORT, 217, 87));
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
        paint11.setColor(Color.rgb(WebSocketProtocol.PAYLOAD_SHORT, 217, 87));
        this.boltPaint = paint11;
        Paint paint12 = new Paint(1);
        paint12.setColor(Color.rgb(196, ComposerKt.providerKey, 208));
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

    /* compiled from: MapRenderer.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\\\b\u0087\b\u0018\u00002\u00020\u0001B«\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0013\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0016\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0016\u0012\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0013\u0012\b\b\u0002\u0010!\u001a\u00020\u0016\u0012\b\b\u0002\u0010\"\u001a\u00020\u0006\u0012\b\b\u0002\u0010#\u001a\u00020\u0016\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0016\u0012\u0010\b\u0002\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0013\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0016\u0012\u0016\b\u0002\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0018\u00010,\u0012\b\b\u0002\u0010-\u001a\u00020\u000b\u0012\b\b\u0002\u0010.\u001a\u00020\u000b\u0012\b\b\u0002\u0010/\u001a\u00020\u000b¢\u0006\u0004\b0\u00101J\t\u0010_\u001a\u00020\u0003HÆ\u0003J\t\u0010`\u001a\u00020\u0003HÆ\u0003J\t\u0010a\u001a\u00020\u0006HÆ\u0003J\t\u0010b\u001a\u00020\bHÆ\u0003J\t\u0010c\u001a\u00020\bHÆ\u0003J\t\u0010d\u001a\u00020\u000bHÆ\u0003J\t\u0010e\u001a\u00020\bHÆ\u0003J\t\u0010f\u001a\u00020\bHÆ\u0003J\u0010\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\u0010\u0010h\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\u0010\u0010i\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\u0010\u0010j\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\u000f\u0010k\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013HÆ\u0003J\u000f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u00190\u0013HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\t\u0010q\u001a\u00020\u000bHÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u0011\u0010t\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0013HÆ\u0003J\t\u0010u\u001a\u00020\u0016HÆ\u0003J\t\u0010v\u001a\u00020\u0006HÆ\u0003J\t\u0010w\u001a\u00020\u0016HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010y\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010z\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u0011\u0010{\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0013HÆ\u0003J\u000b\u0010|\u001a\u0004\u0018\u00010)HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u0017\u0010~\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0018\u00010,HÆ\u0003J\t\u0010\u007f\u001a\u00020\u000bHÆ\u0003J\n\u0010\u0080\u0001\u001a\u00020\u000bHÆ\u0003J\n\u0010\u0081\u0001\u001a\u00020\u000bHÆ\u0003Jº\u0003\u0010\u0082\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00132\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00162\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u00132\b\b\u0002\u0010!\u001a\u00020\u00162\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u00162\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00162\u0010\b\u0002\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00132\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00162\u0016\b\u0002\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0018\u00010,2\b\b\u0002\u0010-\u001a\u00020\u000b2\b\b\u0002\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010/\u001a\u00020\u000bHÆ\u0001¢\u0006\u0003\u0010\u0083\u0001J\u0015\u0010\u0084\u0001\u001a\u00020\u000b2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\n\u0010\u0086\u0001\u001a\u00020\u0006HÖ\u0001J\n\u0010\u0087\u0001\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u00103R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b9\u00108R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b<\u00108R\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b=\u00108R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\b>\u0010?R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\bA\u0010?R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\bB\u0010?R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\bC\u0010?R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\bH\u0010ER\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0013¢\u0006\b\n\u0000\u001a\u0004\bI\u0010ER\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010GR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bK\u0010GR\u0011\u0010\u001c\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\bL\u0010;R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bM\u0010GR\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bN\u0010GR\u0019\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\bO\u0010ER\u0011\u0010!\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\bP\u0010GR\u0011\u0010\"\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bQ\u00106R\u0011\u0010#\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\bR\u0010GR\u0013\u0010$\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bS\u0010GR\u0013\u0010%\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bT\u0010GR\u0013\u0010&\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bU\u0010GR\u0019\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\bV\u0010ER\u0013\u0010(\u001a\u0004\u0018\u00010)¢\u0006\b\n\u0000\u001a\u0004\bW\u0010XR\u0013\u0010*\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\bY\u0010GR\u001f\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b\u0018\u00010,¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010[R\u0011\u0010-\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010;R\u0011\u0010.\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b]\u0010;R\u0011\u0010/\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b^\u0010;¨\u0006\u0088\u0001"}, d2 = {"Lcom/example/wayfindr/dash/map/MapRenderer$Frame;", "", "centerLat", "", "centerLng", "zoom", "", "panX", "", "panY", "headingUp", "", "cameraHeading", "riderHeading", "riderLat", "riderLng", "destLat", "destLng", "destinations", "", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "destName", "", "route", "traffic", "Lcom/example/wayfindr/dash/nav/TrafficInterval;", "maneuverText", "remainingText", "tilt3d", "etaPrimary", "etaSecondary", "pickerRows", "Lcom/example/wayfindr/dash/map/MapRenderer$MenuRow;", "pickerTitle", "pickerIndex", "pickerSelectHint", "zoomLabel", "joystickTrail", "attribution", "notice", "routingBadge", "Lcom/example/wayfindr/dash/RoutingSource;", "downHint", "phoneBattery", "Lkotlin/Pair;", "gpsWeak", "gpsLost", "dark", "<init>", "(DDIFFZFFLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/example/wayfindr/dash/RoutingSource;Ljava/lang/String;Lkotlin/Pair;ZZZ)V", "getCenterLat", "()D", "getCenterLng", "getZoom", "()I", "getPanX", "()F", "getPanY", "getHeadingUp", "()Z", "getCameraHeading", "getRiderHeading", "getRiderLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getRiderLng", "getDestLat", "getDestLng", "getDestinations", "()Ljava/util/List;", "getDestName", "()Ljava/lang/String;", "getRoute", "getTraffic", "getManeuverText", "getRemainingText", "getTilt3d", "getEtaPrimary", "getEtaSecondary", "getPickerRows", "getPickerTitle", "getPickerIndex", "getPickerSelectHint", "getZoomLabel", "getJoystickTrail", "getAttribution", "getNotice", "getRoutingBadge", "()Lcom/example/wayfindr/dash/RoutingSource;", "getDownHint", "getPhoneBattery", "()Lkotlin/Pair;", "getGpsWeak", "getGpsLost", "getDark", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "component35", "copy", "(DDIFFZFFLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/example/wayfindr/dash/RoutingSource;Ljava/lang/String;Lkotlin/Pair;ZZZ)Lcom/example/wayfindr/dash/map/MapRenderer$Frame;", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Frame {
        public static final int $stable = 8;
        private final String attribution;
        private final float cameraHeading;
        private final double centerLat;
        private final double centerLng;
        private final boolean dark;
        private final Double destLat;
        private final Double destLng;
        private final String destName;
        private final List<GeoPoint> destinations;
        private final String downHint;
        private final String etaPrimary;
        private final String etaSecondary;
        private final boolean gpsLost;
        private final boolean gpsWeak;
        private final boolean headingUp;
        private final String joystickTrail;
        private final String maneuverText;
        private final List<String> notice;
        private final float panX;
        private final float panY;
        private final Pair<Integer, Boolean> phoneBattery;
        private final int pickerIndex;
        private final List<MenuRow> pickerRows;
        private final String pickerSelectHint;
        private final String pickerTitle;
        private final String remainingText;
        private final float riderHeading;
        private final Double riderLat;
        private final Double riderLng;
        private final List<GeoPoint> route;
        private final RoutingSource routingBadge;
        private final boolean tilt3d;
        private final List<TrafficInterval> traffic;
        private final int zoom;
        private final String zoomLabel;

        public static /* synthetic */ Frame copy$default(Frame frame, double d, double d2, int i, float f, float f2, boolean z, float f3, float f4, Double d3, Double d4, Double d5, Double d6, List list, String str, List list2, List list3, String str2, String str3, boolean z2, String str4, String str5, List list4, String str6, int i2, String str7, String str8, String str9, String str10, List list5, RoutingSource routingSource, String str11, Pair pair, boolean z3, boolean z4, boolean z5, int i3, int i4, Object obj) {
            boolean z6;
            boolean z7;
            String str12;
            String str13;
            boolean z8;
            String str14;
            String str15;
            List list6;
            String str16;
            int i5;
            String str17;
            String str18;
            String str19;
            String str20;
            List list7;
            RoutingSource routingSource2;
            String str21;
            Pair pair2;
            boolean z9;
            List list8;
            String str22;
            double d7;
            int i6;
            float f5;
            float f6;
            boolean z10;
            float f7;
            float f8;
            Double d8;
            Double d9;
            Double d10;
            Double d11;
            List list9;
            List list10;
            double d12 = (i3 & 1) != 0 ? frame.centerLat : d;
            double d13 = (i3 & 2) != 0 ? frame.centerLng : d2;
            int i7 = (i3 & 4) != 0 ? frame.zoom : i;
            float f9 = (i3 & 8) != 0 ? frame.panX : f;
            float f10 = (i3 & 16) != 0 ? frame.panY : f2;
            boolean z11 = (i3 & 32) != 0 ? frame.headingUp : z;
            float f11 = (i3 & 64) != 0 ? frame.cameraHeading : f3;
            float f12 = (i3 & 128) != 0 ? frame.riderHeading : f4;
            Double d14 = (i3 & 256) != 0 ? frame.riderLat : d3;
            Double d15 = (i3 & 512) != 0 ? frame.riderLng : d4;
            Double d16 = (i3 & 1024) != 0 ? frame.destLat : d5;
            Double d17 = (i3 & 2048) != 0 ? frame.destLng : d6;
            double d18 = d12;
            List list11 = (i3 & 4096) != 0 ? frame.destinations : list;
            String str23 = (i3 & 8192) != 0 ? frame.destName : str;
            List list12 = list11;
            List list13 = (i3 & 16384) != 0 ? frame.route : list2;
            List list14 = (i3 & 32768) != 0 ? frame.traffic : list3;
            String str24 = (i3 & 65536) != 0 ? frame.maneuverText : str2;
            String str25 = (i3 & 131072) != 0 ? frame.remainingText : str3;
            boolean z12 = (i3 & 262144) != 0 ? frame.tilt3d : z2;
            String str26 = (i3 & 524288) != 0 ? frame.etaPrimary : str4;
            String str27 = (i3 & 1048576) != 0 ? frame.etaSecondary : str5;
            List list15 = (i3 & 2097152) != 0 ? frame.pickerRows : list4;
            String str28 = (i3 & 4194304) != 0 ? frame.pickerTitle : str6;
            int i8 = (i3 & 8388608) != 0 ? frame.pickerIndex : i2;
            String str29 = (i3 & 16777216) != 0 ? frame.pickerSelectHint : str7;
            String str30 = (i3 & 33554432) != 0 ? frame.zoomLabel : str8;
            String str31 = (i3 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? frame.joystickTrail : str9;
            String str32 = (i3 & 134217728) != 0 ? frame.attribution : str10;
            List list16 = (i3 & 268435456) != 0 ? frame.notice : list5;
            RoutingSource routingSource3 = (i3 & 536870912) != 0 ? frame.routingBadge : routingSource;
            String str33 = (i3 & 1073741824) != 0 ? frame.downHint : str11;
            Pair pair3 = (i3 & Integer.MIN_VALUE) != 0 ? frame.phoneBattery : pair;
            boolean z13 = (i4 & 1) != 0 ? frame.gpsWeak : z3;
            boolean z14 = (i4 & 2) != 0 ? frame.gpsLost : z4;
            if ((i4 & 4) != 0) {
                z7 = z14;
                z6 = frame.dark;
                str13 = str25;
                z8 = z12;
                str14 = str26;
                str15 = str27;
                list6 = list15;
                str16 = str28;
                i5 = i8;
                str17 = str29;
                str18 = str30;
                str19 = str31;
                str20 = str32;
                list7 = list16;
                routingSource2 = routingSource3;
                str21 = str33;
                pair2 = pair3;
                z9 = z13;
                list8 = list13;
                str22 = str23;
                d7 = d13;
                i6 = i7;
                f5 = f9;
                f6 = f10;
                z10 = z11;
                f7 = f11;
                f8 = f12;
                d8 = d14;
                d9 = d15;
                d10 = d16;
                d11 = d17;
                list9 = list12;
                list10 = list14;
                str12 = str24;
            } else {
                z6 = z5;
                z7 = z14;
                str12 = str24;
                str13 = str25;
                z8 = z12;
                str14 = str26;
                str15 = str27;
                list6 = list15;
                str16 = str28;
                i5 = i8;
                str17 = str29;
                str18 = str30;
                str19 = str31;
                str20 = str32;
                list7 = list16;
                routingSource2 = routingSource3;
                str21 = str33;
                pair2 = pair3;
                z9 = z13;
                list8 = list13;
                str22 = str23;
                d7 = d13;
                i6 = i7;
                f5 = f9;
                f6 = f10;
                z10 = z11;
                f7 = f11;
                f8 = f12;
                d8 = d14;
                d9 = d15;
                d10 = d16;
                d11 = d17;
                list9 = list12;
                list10 = list14;
            }
            return frame.copy(d18, d7, i6, f5, f6, z10, f7, f8, d8, d9, d10, d11, list9, str22, list8, list10, str12, str13, z8, str14, str15, list6, str16, i5, str17, str18, str19, str20, list7, routingSource2, str21, pair2, z9, z7, z6);
        }

        /* renamed from: component1, reason: from getter */
        public final double getCenterLat() {
            return this.centerLat;
        }

        /* renamed from: component10, reason: from getter */
        public final Double getRiderLng() {
            return this.riderLng;
        }

        /* renamed from: component11, reason: from getter */
        public final Double getDestLat() {
            return this.destLat;
        }

        /* renamed from: component12, reason: from getter */
        public final Double getDestLng() {
            return this.destLng;
        }

        public final List<GeoPoint> component13() {
            return this.destinations;
        }

        /* renamed from: component14, reason: from getter */
        public final String getDestName() {
            return this.destName;
        }

        public final List<GeoPoint> component15() {
            return this.route;
        }

        public final List<TrafficInterval> component16() {
            return this.traffic;
        }

        /* renamed from: component17, reason: from getter */
        public final String getManeuverText() {
            return this.maneuverText;
        }

        /* renamed from: component18, reason: from getter */
        public final String getRemainingText() {
            return this.remainingText;
        }

        /* renamed from: component19, reason: from getter */
        public final boolean getTilt3d() {
            return this.tilt3d;
        }

        /* renamed from: component2, reason: from getter */
        public final double getCenterLng() {
            return this.centerLng;
        }

        /* renamed from: component20, reason: from getter */
        public final String getEtaPrimary() {
            return this.etaPrimary;
        }

        /* renamed from: component21, reason: from getter */
        public final String getEtaSecondary() {
            return this.etaSecondary;
        }

        public final List<MenuRow> component22() {
            return this.pickerRows;
        }

        /* renamed from: component23, reason: from getter */
        public final String getPickerTitle() {
            return this.pickerTitle;
        }

        /* renamed from: component24, reason: from getter */
        public final int getPickerIndex() {
            return this.pickerIndex;
        }

        /* renamed from: component25, reason: from getter */
        public final String getPickerSelectHint() {
            return this.pickerSelectHint;
        }

        /* renamed from: component26, reason: from getter */
        public final String getZoomLabel() {
            return this.zoomLabel;
        }

        /* renamed from: component27, reason: from getter */
        public final String getJoystickTrail() {
            return this.joystickTrail;
        }

        /* renamed from: component28, reason: from getter */
        public final String getAttribution() {
            return this.attribution;
        }

        public final List<String> component29() {
            return this.notice;
        }

        /* renamed from: component3, reason: from getter */
        public final int getZoom() {
            return this.zoom;
        }

        /* renamed from: component30, reason: from getter */
        public final RoutingSource getRoutingBadge() {
            return this.routingBadge;
        }

        /* renamed from: component31, reason: from getter */
        public final String getDownHint() {
            return this.downHint;
        }

        public final Pair<Integer, Boolean> component32() {
            return this.phoneBattery;
        }

        /* renamed from: component33, reason: from getter */
        public final boolean getGpsWeak() {
            return this.gpsWeak;
        }

        /* renamed from: component34, reason: from getter */
        public final boolean getGpsLost() {
            return this.gpsLost;
        }

        /* renamed from: component35, reason: from getter */
        public final boolean getDark() {
            return this.dark;
        }

        /* renamed from: component4, reason: from getter */
        public final float getPanX() {
            return this.panX;
        }

        /* renamed from: component5, reason: from getter */
        public final float getPanY() {
            return this.panY;
        }

        /* renamed from: component6, reason: from getter */
        public final boolean getHeadingUp() {
            return this.headingUp;
        }

        /* renamed from: component7, reason: from getter */
        public final float getCameraHeading() {
            return this.cameraHeading;
        }

        /* renamed from: component8, reason: from getter */
        public final float getRiderHeading() {
            return this.riderHeading;
        }

        /* renamed from: component9, reason: from getter */
        public final Double getRiderLat() {
            return this.riderLat;
        }

        public final Frame copy(double centerLat, double centerLng, int zoom, float panX, float panY, boolean headingUp, float cameraHeading, float riderHeading, Double riderLat, Double riderLng, Double destLat, Double destLng, List<GeoPoint> destinations, String destName, List<GeoPoint> route, List<TrafficInterval> traffic, String maneuverText, String remainingText, boolean tilt3d, String etaPrimary, String etaSecondary, List<MenuRow> pickerRows, String pickerTitle, int pickerIndex, String pickerSelectHint, String zoomLabel, String joystickTrail, String attribution, List<String> notice, RoutingSource routingBadge, String downHint, Pair<Integer, Boolean> phoneBattery, boolean gpsWeak, boolean gpsLost, boolean dark) {
            Intrinsics.checkNotNullParameter(destinations, "destinations");
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(traffic, "traffic");
            Intrinsics.checkNotNullParameter(pickerTitle, "pickerTitle");
            Intrinsics.checkNotNullParameter(pickerSelectHint, "pickerSelectHint");
            return new Frame(centerLat, centerLng, zoom, panX, panY, headingUp, cameraHeading, riderHeading, riderLat, riderLng, destLat, destLng, destinations, destName, route, traffic, maneuverText, remainingText, tilt3d, etaPrimary, etaSecondary, pickerRows, pickerTitle, pickerIndex, pickerSelectHint, zoomLabel, joystickTrail, attribution, notice, routingBadge, downHint, phoneBattery, gpsWeak, gpsLost, dark);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Frame)) {
                return false;
            }
            Frame frame = (Frame) other;
            return Double.compare(this.centerLat, frame.centerLat) == 0 && Double.compare(this.centerLng, frame.centerLng) == 0 && this.zoom == frame.zoom && Float.compare(this.panX, frame.panX) == 0 && Float.compare(this.panY, frame.panY) == 0 && this.headingUp == frame.headingUp && Float.compare(this.cameraHeading, frame.cameraHeading) == 0 && Float.compare(this.riderHeading, frame.riderHeading) == 0 && Intrinsics.areEqual((Object) this.riderLat, (Object) frame.riderLat) && Intrinsics.areEqual((Object) this.riderLng, (Object) frame.riderLng) && Intrinsics.areEqual((Object) this.destLat, (Object) frame.destLat) && Intrinsics.areEqual((Object) this.destLng, (Object) frame.destLng) && Intrinsics.areEqual(this.destinations, frame.destinations) && Intrinsics.areEqual(this.destName, frame.destName) && Intrinsics.areEqual(this.route, frame.route) && Intrinsics.areEqual(this.traffic, frame.traffic) && Intrinsics.areEqual(this.maneuverText, frame.maneuverText) && Intrinsics.areEqual(this.remainingText, frame.remainingText) && this.tilt3d == frame.tilt3d && Intrinsics.areEqual(this.etaPrimary, frame.etaPrimary) && Intrinsics.areEqual(this.etaSecondary, frame.etaSecondary) && Intrinsics.areEqual(this.pickerRows, frame.pickerRows) && Intrinsics.areEqual(this.pickerTitle, frame.pickerTitle) && this.pickerIndex == frame.pickerIndex && Intrinsics.areEqual(this.pickerSelectHint, frame.pickerSelectHint) && Intrinsics.areEqual(this.zoomLabel, frame.zoomLabel) && Intrinsics.areEqual(this.joystickTrail, frame.joystickTrail) && Intrinsics.areEqual(this.attribution, frame.attribution) && Intrinsics.areEqual(this.notice, frame.notice) && this.routingBadge == frame.routingBadge && Intrinsics.areEqual(this.downHint, frame.downHint) && Intrinsics.areEqual(this.phoneBattery, frame.phoneBattery) && this.gpsWeak == frame.gpsWeak && this.gpsLost == frame.gpsLost && this.dark == frame.dark;
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((Double.hashCode(this.centerLat) * 31) + Double.hashCode(this.centerLng)) * 31) + Integer.hashCode(this.zoom)) * 31) + Float.hashCode(this.panX)) * 31) + Float.hashCode(this.panY)) * 31) + Boolean.hashCode(this.headingUp)) * 31) + Float.hashCode(this.cameraHeading)) * 31) + Float.hashCode(this.riderHeading)) * 31) + (this.riderLat == null ? 0 : this.riderLat.hashCode())) * 31) + (this.riderLng == null ? 0 : this.riderLng.hashCode())) * 31) + (this.destLat == null ? 0 : this.destLat.hashCode())) * 31) + (this.destLng == null ? 0 : this.destLng.hashCode())) * 31) + this.destinations.hashCode()) * 31) + (this.destName == null ? 0 : this.destName.hashCode())) * 31) + this.route.hashCode()) * 31) + this.traffic.hashCode()) * 31) + (this.maneuverText == null ? 0 : this.maneuverText.hashCode())) * 31) + (this.remainingText == null ? 0 : this.remainingText.hashCode())) * 31) + Boolean.hashCode(this.tilt3d)) * 31) + (this.etaPrimary == null ? 0 : this.etaPrimary.hashCode())) * 31) + (this.etaSecondary == null ? 0 : this.etaSecondary.hashCode())) * 31) + (this.pickerRows == null ? 0 : this.pickerRows.hashCode())) * 31) + this.pickerTitle.hashCode()) * 31) + Integer.hashCode(this.pickerIndex)) * 31) + this.pickerSelectHint.hashCode()) * 31) + (this.zoomLabel == null ? 0 : this.zoomLabel.hashCode())) * 31) + (this.joystickTrail == null ? 0 : this.joystickTrail.hashCode())) * 31) + (this.attribution == null ? 0 : this.attribution.hashCode())) * 31) + (this.notice == null ? 0 : this.notice.hashCode())) * 31) + (this.routingBadge == null ? 0 : this.routingBadge.hashCode())) * 31) + (this.downHint == null ? 0 : this.downHint.hashCode())) * 31) + (this.phoneBattery != null ? this.phoneBattery.hashCode() : 0)) * 31) + Boolean.hashCode(this.gpsWeak)) * 31) + Boolean.hashCode(this.gpsLost)) * 31) + Boolean.hashCode(this.dark);
        }

        public String toString() {
            return "Frame(centerLat=" + this.centerLat + ", centerLng=" + this.centerLng + ", zoom=" + this.zoom + ", panX=" + this.panX + ", panY=" + this.panY + ", headingUp=" + this.headingUp + ", cameraHeading=" + this.cameraHeading + ", riderHeading=" + this.riderHeading + ", riderLat=" + this.riderLat + ", riderLng=" + this.riderLng + ", destLat=" + this.destLat + ", destLng=" + this.destLng + ", destinations=" + this.destinations + ", destName=" + this.destName + ", route=" + this.route + ", traffic=" + this.traffic + ", maneuverText=" + this.maneuverText + ", remainingText=" + this.remainingText + ", tilt3d=" + this.tilt3d + ", etaPrimary=" + this.etaPrimary + ", etaSecondary=" + this.etaSecondary + ", pickerRows=" + this.pickerRows + ", pickerTitle=" + this.pickerTitle + ", pickerIndex=" + this.pickerIndex + ", pickerSelectHint=" + this.pickerSelectHint + ", zoomLabel=" + this.zoomLabel + ", joystickTrail=" + this.joystickTrail + ", attribution=" + this.attribution + ", notice=" + this.notice + ", routingBadge=" + this.routingBadge + ", downHint=" + this.downHint + ", phoneBattery=" + this.phoneBattery + ", gpsWeak=" + this.gpsWeak + ", gpsLost=" + this.gpsLost + ", dark=" + this.dark + ")";
        }

        public Frame(double centerLat, double centerLng, int zoom, float panX, float panY, boolean headingUp, float cameraHeading, float riderHeading, Double riderLat, Double riderLng, Double destLat, Double destLng, List<GeoPoint> destinations, String destName, List<GeoPoint> route, List<TrafficInterval> traffic, String maneuverText, String remainingText, boolean tilt3d, String etaPrimary, String etaSecondary, List<MenuRow> list, String pickerTitle, int pickerIndex, String pickerSelectHint, String zoomLabel, String joystickTrail, String attribution, List<String> list2, RoutingSource routingBadge, String downHint, Pair<Integer, Boolean> pair, boolean gpsWeak, boolean gpsLost, boolean dark) {
            Intrinsics.checkNotNullParameter(destinations, "destinations");
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(traffic, "traffic");
            Intrinsics.checkNotNullParameter(pickerTitle, "pickerTitle");
            Intrinsics.checkNotNullParameter(pickerSelectHint, "pickerSelectHint");
            this.centerLat = centerLat;
            this.centerLng = centerLng;
            this.zoom = zoom;
            this.panX = panX;
            this.panY = panY;
            this.headingUp = headingUp;
            this.cameraHeading = cameraHeading;
            this.riderHeading = riderHeading;
            this.riderLat = riderLat;
            this.riderLng = riderLng;
            this.destLat = destLat;
            this.destLng = destLng;
            this.destinations = destinations;
            this.destName = destName;
            this.route = route;
            this.traffic = traffic;
            this.maneuverText = maneuverText;
            this.remainingText = remainingText;
            this.tilt3d = tilt3d;
            this.etaPrimary = etaPrimary;
            this.etaSecondary = etaSecondary;
            this.pickerRows = list;
            this.pickerTitle = pickerTitle;
            this.pickerIndex = pickerIndex;
            this.pickerSelectHint = pickerSelectHint;
            this.zoomLabel = zoomLabel;
            this.joystickTrail = joystickTrail;
            this.attribution = attribution;
            this.notice = list2;
            this.routingBadge = routingBadge;
            this.downHint = downHint;
            this.phoneBattery = pair;
            this.gpsWeak = gpsWeak;
            this.gpsLost = gpsLost;
            this.dark = dark;
        }

        public /* synthetic */ Frame(double d, double d2, int i, float f, float f2, boolean z, float f3, float f4, Double d3, Double d4, Double d5, Double d6, List list, String str, List list2, List list3, String str2, String str3, boolean z2, String str4, String str5, List list4, String str6, int i2, String str7, String str8, String str9, String str10, List list5, RoutingSource routingSource, String str11, Pair pair, boolean z3, boolean z4, boolean z5, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(d, d2, i, (i3 & 8) != 0 ? 0.0f : f, (i3 & 16) != 0 ? 0.0f : f2, (i3 & 32) != 0 ? false : z, (i3 & 64) != 0 ? 0.0f : f3, (i3 & 128) != 0 ? 0.0f : f4, (i3 & 256) != 0 ? null : d3, (i3 & 512) != 0 ? null : d4, (i3 & 1024) != 0 ? null : d5, (i3 & 2048) != 0 ? null : d6, (i3 & 4096) != 0 ? CollectionsKt.emptyList() : list, (i3 & 8192) != 0 ? null : str, (i3 & 16384) != 0 ? CollectionsKt.emptyList() : list2, (32768 & i3) != 0 ? CollectionsKt.emptyList() : list3, (65536 & i3) != 0 ? null : str2, (131072 & i3) != 0 ? null : str3, (262144 & i3) != 0 ? false : z2, (524288 & i3) != 0 ? null : str4, (1048576 & i3) != 0 ? null : str5, (2097152 & i3) != 0 ? null : list4, (4194304 & i3) != 0 ? "MENÜ" : str6, (8388608 & i3) != 0 ? 0 : i2, (16777216 & i3) != 0 ? "Sol/sağ: gezin · sağa basılı: seç" : str7, (33554432 & i3) != 0 ? null : str8, (67108864 & i3) != 0 ? null : str9, (134217728 & i3) != 0 ? null : str10, (268435456 & i3) != 0 ? null : list5, (536870912 & i3) != 0 ? null : routingSource, (1073741824 & i3) != 0 ? null : str11, (i3 & Integer.MIN_VALUE) != 0 ? null : pair, (i4 & 1) != 0 ? false : z3, (i4 & 2) != 0 ? false : z4, (i4 & 4) != 0 ? false : z5);
        }

        public final double getCenterLat() {
            return this.centerLat;
        }

        public final double getCenterLng() {
            return this.centerLng;
        }

        public final int getZoom() {
            return this.zoom;
        }

        public final float getPanX() {
            return this.panX;
        }

        public final float getPanY() {
            return this.panY;
        }

        public final boolean getHeadingUp() {
            return this.headingUp;
        }

        public final float getCameraHeading() {
            return this.cameraHeading;
        }

        public final float getRiderHeading() {
            return this.riderHeading;
        }

        public final Double getRiderLat() {
            return this.riderLat;
        }

        public final Double getRiderLng() {
            return this.riderLng;
        }

        public final Double getDestLat() {
            return this.destLat;
        }

        public final Double getDestLng() {
            return this.destLng;
        }

        public final List<GeoPoint> getDestinations() {
            return this.destinations;
        }

        public final String getDestName() {
            return this.destName;
        }

        public final List<GeoPoint> getRoute() {
            return this.route;
        }

        public final List<TrafficInterval> getTraffic() {
            return this.traffic;
        }

        public final String getManeuverText() {
            return this.maneuverText;
        }

        public final String getRemainingText() {
            return this.remainingText;
        }

        public final boolean getTilt3d() {
            return this.tilt3d;
        }

        public final String getEtaPrimary() {
            return this.etaPrimary;
        }

        public final String getEtaSecondary() {
            return this.etaSecondary;
        }

        public final List<MenuRow> getPickerRows() {
            return this.pickerRows;
        }

        public final String getPickerTitle() {
            return this.pickerTitle;
        }

        public final int getPickerIndex() {
            return this.pickerIndex;
        }

        public final String getPickerSelectHint() {
            return this.pickerSelectHint;
        }

        public final String getZoomLabel() {
            return this.zoomLabel;
        }

        public final String getJoystickTrail() {
            return this.joystickTrail;
        }

        public final String getAttribution() {
            return this.attribution;
        }

        public final List<String> getNotice() {
            return this.notice;
        }

        public final RoutingSource getRoutingBadge() {
            return this.routingBadge;
        }

        public final String getDownHint() {
            return this.downHint;
        }

        public final Pair<Integer, Boolean> getPhoneBattery() {
            return this.phoneBattery;
        }

        public final boolean getGpsWeak() {
            return this.gpsWeak;
        }

        public final boolean getGpsLost() {
            return this.gpsLost;
        }

        public final boolean getDark() {
            return this.dark;
        }
    }

    /* compiled from: MapRenderer.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/example/wayfindr/dash/map/MapRenderer$MenuRow;", "", "label", "", "header", "", "active", "submenu", "<init>", "(Ljava/lang/String;ZZZ)V", "getLabel", "()Ljava/lang/String;", "getHeader", "()Z", "getActive", "getSubmenu", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class MenuRow {
        public static final int $stable = 0;
        private final boolean active;
        private final boolean header;
        private final String label;
        private final boolean submenu;

        public static /* synthetic */ MenuRow copy$default(MenuRow menuRow, String str, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = menuRow.label;
            }
            if ((i & 2) != 0) {
                z = menuRow.header;
            }
            if ((i & 4) != 0) {
                z2 = menuRow.active;
            }
            if ((i & 8) != 0) {
                z3 = menuRow.submenu;
            }
            return menuRow.copy(str, z, z2, z3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getHeader() {
            return this.header;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getActive() {
            return this.active;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getSubmenu() {
            return this.submenu;
        }

        public final MenuRow copy(String label, boolean header, boolean active, boolean submenu) {
            Intrinsics.checkNotNullParameter(label, "label");
            return new MenuRow(label, header, active, submenu);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MenuRow)) {
                return false;
            }
            MenuRow menuRow = (MenuRow) other;
            return Intrinsics.areEqual(this.label, menuRow.label) && this.header == menuRow.header && this.active == menuRow.active && this.submenu == menuRow.submenu;
        }

        public int hashCode() {
            return (((((this.label.hashCode() * 31) + Boolean.hashCode(this.header)) * 31) + Boolean.hashCode(this.active)) * 31) + Boolean.hashCode(this.submenu);
        }

        public String toString() {
            return "MenuRow(label=" + this.label + ", header=" + this.header + ", active=" + this.active + ", submenu=" + this.submenu + ")";
        }

        public MenuRow(String label, boolean header, boolean active, boolean submenu) {
            Intrinsics.checkNotNullParameter(label, "label");
            this.label = label;
            this.header = header;
            this.active = active;
            this.submenu = submenu;
        }

        public /* synthetic */ MenuRow(String str, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? false : z3);
        }

        public final String getLabel() {
            return this.label;
        }

        public final boolean getHeader() {
            return this.header;
        }

        public final boolean getActive() {
            return this.active;
        }

        public final boolean getSubmenu() {
            return this.submenu;
        }
    }

    private final void overlay(String name, Function0<Unit> draw) {
        try {
            draw.invoke();
        } catch (Exception e) {
            if (this.failedOverlays.add(name)) {
                RideDiagnostics.INSTANCE.log("render", "overlay '" + name + "' failed: " + e);
            }
        }
    }

    public static /* synthetic */ void draw$default(MapRenderer mapRenderer, Canvas canvas, Frame frame, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = canvas.getWidth();
        }
        if ((i3 & 8) != 0) {
            i2 = canvas.getHeight();
        }
        mapRenderer.draw(canvas, frame, i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x074b A[Catch: Exception -> 0x07ba, TRY_LEAVE, TryCatch #17 {Exception -> 0x07ba, blocks: (B:148:0x0745, B:150:0x074b), top: B:147:0x0745 }] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x07f1 A[Catch: Exception -> 0x085a, TRY_LEAVE, TryCatch #16 {Exception -> 0x085a, blocks: (B:157:0x07eb, B:159:0x07f1), top: B:156:0x07eb }] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0893 A[Catch: Exception -> 0x0a0c, TRY_LEAVE, TryCatch #5 {Exception -> 0x0a0c, blocks: (B:165:0x088d, B:167:0x0893), top: B:164:0x088d }] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0a46 A[Catch: Exception -> 0x0ac5, TRY_LEAVE, TryCatch #20 {Exception -> 0x0ac5, blocks: (B:195:0x0a40, B:197:0x0a46), top: B:194:0x0a40 }] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0afd A[Catch: Exception -> 0x0c06, TRY_LEAVE, TryCatch #19 {Exception -> 0x0c06, blocks: (B:209:0x0af7, B:211:0x0afd, B:220:0x0b42, B:273:0x0b36), top: B:208:0x0af7 }] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0c39  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0cb0  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0cc3  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0ce4  */
    /* JADX WARN: Removed duplicated region for block: B:248:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0cdc  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0cbb  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0c45  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0c52  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0c55  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0c48  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0bfd  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0abd  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0a02  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0854  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x07b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void draw(Canvas canvas, Frame f, int w, int h) {
        int i;
        int txMax;
        float displayHeading;
        int i2;
        int ts;
        Canvas canvas2;
        String str;
        boolean rotate;
        int ts2;
        double left;
        double top;
        MapRenderer mapRenderer;
        float etaTop;
        float f2;
        float f3;
        String str2;
        float bottomEdge;
        float stackTop;
        List notice;
        int i3;
        List pickerRows;
        MapRenderer mapRenderer2;
        Canvas canvas3;
        RoutingSource routingBadge;
        Pair pair;
        float stackTop2;
        float stackTop3;
        String zoomLabel;
        Pair<Integer, Boolean> phoneBattery;
        String downHint;
        String joystickTrail;
        float f4;
        float f5;
        Path path;
        Iterable iterable;
        String str3;
        double left2;
        int i4;
        boolean rotate2;
        int i5;
        int pad;
        int ts3;
        String str4 = "render";
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(f, "f");
        canvas.drawColor(f.getDark() ? this.darkBg : this.lightBg);
        this.tilePaint.setColorFilter(f.getDark() ? this.darkTileFilter : this.lightTileFilter);
        this.routeCasing.setColor(f.getDark() ? Color.rgb(18, 22, 28) : -1);
        this.standbyPaint.setColor(f.getDark() ? Color.rgb(186, 192, ComposerKt.invocationKey) : Color.rgb(60, 64, 67));
        boolean rotate3 = f.getHeadingUp();
        boolean tilt = rotate3 && f.getTilt3d();
        float pivotY = rotate3 ? h * (tilt ? 0.74f : 0.66f) : h / 2.0f;
        int ts4 = 256;
        int ts5 = 0;
        double cx = f.getPanX() + (Mercator.INSTANCE.lngToTileX(f.getCenterLng(), f.getZoom()) * 256);
        double cy = (256 * Mercator.INSTANCE.latToTileY(f.getCenterLat(), f.getZoom())) + f.getPanY();
        double left3 = cx - (w / 2.0d);
        double top2 = cy - pivotY;
        float displayHeading2 = normalizeHeading(f.getCameraHeading());
        if (rotate3) {
            canvas.save();
        }
        if (rotate3) {
            if (tilt) {
                float inset = w * 0.18f;
                this.tiltSrc[0] = 0.0f;
                this.tiltSrc[1] = 0.0f;
                i = 2;
                this.tiltSrc[2] = w;
                this.tiltSrc[3] = 0.0f;
                this.tiltSrc[4] = w;
                this.tiltSrc[5] = h;
                this.tiltSrc[6] = 0.0f;
                this.tiltSrc[7] = h;
                this.tiltDst[0] = inset;
                this.tiltDst[1] = 0.0f;
                this.tiltDst[2] = w - inset;
                this.tiltDst[3] = 0.0f;
                this.tiltDst[4] = w;
                this.tiltDst[5] = h;
                this.tiltDst[6] = 0.0f;
                this.tiltDst[7] = h;
                this.tiltMatrix.setPolyToPoly(this.tiltSrc, 0, this.tiltDst, 0, 4);
                canvas.concat(this.tiltMatrix);
            } else {
                i = 2;
            }
            canvas.rotate(-displayHeading2, w / 2.0f, pivotY);
        } else {
            i = 2;
        }
        int pad2 = rotate3 ? (int) (Math.max(w, h) * 0.45d) : 0;
        int txMin = Math.floorDiv((int) (left3 - pad2), 256);
        int tyMin = Math.floorDiv((int) (top2 - pad2), 256);
        int tx = Math.floorDiv((int) (left3 + w + pad2), 256);
        float displayHeading3 = displayHeading2;
        int tyMax = Math.floorDiv((int) (top2 + h + pad2), 256);
        int tx2 = txMin;
        if (tx2 <= tx) {
            while (true) {
                int ty = tyMin;
                if (ty <= tyMax) {
                    while (true) {
                        pad = pad2;
                        float dstL = (float) ((tx2 * ts4) - left3);
                        float dstT = (float) ((ty * ts4) - top2);
                        int txMax2 = tx;
                        this.tmpRect.set(dstL, dstT, ts4 + dstL, ts4 + dstT);
                        displayHeading = displayHeading3;
                        i2 = ts5;
                        txMax = txMax2;
                        ts = ts4;
                        ts3 = tx2;
                        drawTileBestEffort(canvas, f.getZoom(), ts3, ty, this.tmpRect);
                        if (ty == tyMax) {
                            break;
                        }
                        ty++;
                        tx2 = ts3;
                        tx = txMax;
                        ts4 = ts;
                        pad2 = pad;
                        ts5 = i2;
                        displayHeading3 = displayHeading;
                    }
                } else {
                    pad = pad2;
                    txMax = tx;
                    displayHeading = displayHeading3;
                    i2 = ts5;
                    ts3 = tx2;
                    ts = ts4;
                }
                if (ts3 == txMax) {
                    break;
                }
                tx2 = ts3 + 1;
                tx = txMax;
                ts4 = ts;
                pad2 = pad;
                ts5 = i2;
                displayHeading3 = displayHeading;
            }
        } else {
            txMax = tx;
            displayHeading = displayHeading3;
            i2 = 0;
            ts = 256;
        }
        if (f.getRoute().size() >= i) {
            this.routePath.reset();
            ts2 = ts;
            double left4 = left3;
            top = top2;
            this.routePath.moveTo(draw$sx(f, ts2, left3, f.getRoute().get(i2).getLng()), draw$sy(f, ts2, top2, f.getRoute().get(i2).getLat()));
            int i6 = 1;
            int size = f.getRoute().size();
            while (true) {
                path = this.routePath;
                if (i6 >= size) {
                    break;
                }
                path.lineTo(draw$sx(f, ts2, left4, f.getRoute().get(i6).getLng()), draw$sy(f, ts2, top, f.getRoute().get(i6).getLat()));
                i6++;
                tyMax = tyMax;
                txMax = txMax;
            }
            mapRenderer = this;
            canvas2 = canvas;
            canvas2.drawPath(path, mapRenderer.routeCasing);
            canvas2.drawPath(mapRenderer.routePath, mapRenderer.routePaint);
            Iterable<TrafficInterval> traffic = f.getTraffic();
            int i7 = 0;
            for (TrafficInterval trafficInterval : traffic) {
                int coerceIn = RangesKt.coerceIn(trafficInterval.getStartPointIndex(), 0, CollectionsKt.getLastIndex(f.getRoute()));
                int coerceIn2 = RangesKt.coerceIn(trafficInterval.getEndPointIndex(), coerceIn, CollectionsKt.getLastIndex(f.getRoute()));
                if (coerceIn2 > coerceIn) {
                    mapRenderer.routePath.reset();
                    iterable = traffic;
                    double left5 = left4;
                    i4 = i7;
                    rotate2 = rotate3;
                    left2 = left5;
                    mapRenderer.routePath.moveTo(draw$sx(f, ts2, left5, f.getRoute().get(coerceIn).getLng()), draw$sy(f, ts2, top, f.getRoute().get(coerceIn).getLat()));
                    int i8 = coerceIn + 1;
                    if (i8 <= coerceIn2) {
                        int i9 = i8;
                        while (true) {
                            str3 = str4;
                            mapRenderer.routePath.lineTo(draw$sx(f, ts2, left2, f.getRoute().get(i9).getLng()), draw$sy(f, ts2, top, f.getRoute().get(i9).getLat()));
                            if (i9 != coerceIn2) {
                                i9++;
                                str4 = str3;
                            }
                        }
                    } else {
                        str3 = str4;
                    }
                    Paint paint = mapRenderer.routePaint;
                    switch (WhenMappings.$EnumSwitchMapping$0[trafficInterval.getSpeed().ordinal()]) {
                        case 1:
                            i5 = mapRenderer.routeBlue;
                            break;
                        case 2:
                            i5 = mapRenderer.trafficAmber;
                            break;
                        case 3:
                            i5 = mapRenderer.trafficRed;
                            break;
                        case 4:
                            i5 = mapRenderer.routeBlue;
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    paint.setColor(i5);
                    canvas2.drawPath(mapRenderer.routePath, mapRenderer.routePaint);
                } else {
                    iterable = traffic;
                    str3 = str4;
                    left2 = left4;
                    i4 = i7;
                    rotate2 = rotate3;
                }
                i7 = i4;
                rotate3 = rotate2;
                traffic = iterable;
                left4 = left2;
                str4 = str3;
            }
            str = str4;
            left = left4;
            rotate = rotate3;
            mapRenderer.routePaint.setColor(mapRenderer.routeBlue);
        } else {
            canvas2 = canvas;
            str = "render";
            rotate = rotate3;
            ts2 = ts;
            left = left3;
            top = top2;
            mapRenderer = this;
        }
        List destinations = f.getDestinations();
        if (destinations.isEmpty()) {
            Double destLat = f.getDestLat();
            GeoPoint geoPoint = null;
            if (destLat != null) {
                double doubleValue = destLat.doubleValue();
                Double destLng = f.getDestLng();
                if (destLng != null) {
                    geoPoint = new GeoPoint(doubleValue, destLng.doubleValue());
                }
            }
            destinations = CollectionsKt.listOfNotNull(geoPoint);
        }
        List destinationPoints = destinations;
        List list = destinationPoints;
        int i10 = 0;
        for (Object obj : list) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            GeoPoint geoPoint2 = (GeoPoint) obj;
            Iterable iterable2 = list;
            int i12 = i10;
            float draw$sx = draw$sx(f, ts2, left, geoPoint2.getLng());
            List destinationPoints2 = destinationPoints;
            double top3 = top;
            float draw$sy = draw$sy(f, ts2, top3, geoPoint2.getLat());
            int ts6 = ts2;
            mapRenderer.dotPaint.setColor(-1);
            canvas2.drawCircle(draw$sx, draw$sy, 12.0f, mapRenderer.dotPaint);
            mapRenderer.dotPaint.setColor(i12 == CollectionsKt.getLastIndex(destinationPoints2) ? mapRenderer.googleRed : Color.rgb(242, 169, 59));
            canvas2.drawCircle(draw$sx, draw$sy, 9.0f, mapRenderer.dotPaint);
            mapRenderer.dotPaint.setColor(-1);
            canvas2.drawCircle(draw$sx, draw$sy, 3.5f, mapRenderer.dotPaint);
            ts2 = ts6;
            i10 = i11;
            list = iterable2;
            destinationPoints = destinationPoints2;
            top = top3;
        }
        if (rotate) {
            canvas2.restore();
        }
        if (f.getRiderLat() != null && f.getRiderLng() != null) {
            float rx = w / 2.0f;
            int markerColor = f.getGpsLost() ? Color.rgb(150, 154, 160) : f.getGpsWeak() ? Color.rgb(251, 188, 5) : mapRenderer.routeBlue;
            int haloColor = Color.argb(60, Color.red(markerColor), Color.green(markerColor), Color.blue(markerColor));
            mapRenderer.dotPaint.setColor(haloColor);
            canvas2.drawCircle(rx, pivotY, NOTICE_GAP_ABOVE_CENTRE, mapRenderer.dotPaint);
            float relativeHeading = f.getHeadingUp() ? mapRenderer.normalizeHeading(f.getRiderHeading() - displayHeading) : mapRenderer.normalizeHeading(f.getRiderHeading());
            canvas2.save();
            canvas2.translate(rx, pivotY);
            canvas2.rotate(relativeHeading);
            mapRenderer.dotPaint.setColor(-1);
            canvas2.drawCircle(0.0f, 0.0f, 10.0f, mapRenderer.dotPaint);
            mapRenderer.riderPath.reset();
            mapRenderer.riderPath.moveTo(0.0f, -8.0f);
            mapRenderer.riderPath.lineTo(6.5f, 7.0f);
            mapRenderer.riderPath.lineTo(0.0f, 3.5f);
            mapRenderer.riderPath.lineTo(-6.5f, 7.0f);
            mapRenderer.riderPath.close();
            mapRenderer.dotPaint.setColor(markerColor);
            canvas2.drawPath(mapRenderer.riderPath, mapRenderer.dotPaint);
            canvas2.restore();
        }
        float etaTop2 = h - 8.0f;
        String etaPrimary = f.getEtaPrimary();
        if (etaPrimary != null) {
            String etaSecondary = f.getEtaSecondary();
            f3 = 24.0f;
            Paint.FontMetrics fontMetrics = mapRenderer.etaBigPaint.getFontMetrics();
            Paint.FontMetrics fontMetrics2 = mapRenderer.etaSmallPaint.getFontMetrics();
            f2 = 8.0f;
            float f6 = fontMetrics.descent;
            float etaTop3 = fontMetrics.ascent;
            float f7 = f6 - etaTop3;
            float f8 = etaSecondary != null ? fontMetrics2.descent - fontMetrics2.ascent : 0.0f;
            float measureText = mapRenderer.etaBigPaint.measureText(etaPrimary);
            if (etaSecondary != null) {
                f4 = f7;
                f5 = mapRenderer.etaSmallPaint.measureText(etaSecondary);
            } else {
                f4 = f7;
                f5 = 0.0f;
            }
            mapRenderer.pillRect.set((w - 24.0f) - RangesKt.coerceAtMost((9.0f * 2.0f) + Math.max(measureText, f5), h * 0.5f), (h - 8.0f) - (((4.0f * 2.0f) + f4) + (etaSecondary != null ? 1.0f + f8 : 0.0f)), w - 24.0f, h - 8.0f);
            float centerX = mapRenderer.pillRect.centerX();
            float f9 = mapRenderer.pillRect.top;
            canvas2.drawRoundRect(mapRenderer.pillRect, 8.0f, 8.0f, mapRenderer.etaBgPaint);
            canvas2.drawRoundRect(mapRenderer.pillRect, 8.0f, 8.0f, mapRenderer.etaBorderPaint);
            float f10 = (f9 + 4.0f) - fontMetrics.ascent;
            canvas2.drawText(etaPrimary, centerX, f10, mapRenderer.etaBigPaint);
            if (etaSecondary != null) {
                canvas2.drawText(etaSecondary, centerX, f10 + ((fontMetrics.descent + 1.0f) - fontMetrics2.ascent), mapRenderer.etaSmallPaint);
            }
            Unit unit = Unit.INSTANCE;
            Unit unit2 = Unit.INSTANCE;
            etaTop = f9;
        } else {
            etaTop = etaTop2;
            f2 = 8.0f;
            f3 = 24.0f;
        }
        float bottomEdge2 = h - f2;
        try {
            String attribution = f.getAttribution();
            if (attribution != null) {
                Paint.FontMetrics fontMetrics3 = mapRenderer.attributionPaint.getFontMetrics();
                float measureText2 = mapRenderer.attributionPaint.measureText(attribution);
                try {
                    mapRenderer.pillRect.set(((w / 2.0f) - (measureText2 / 2.0f)) - 5.0f, (h - 3.0f) - ((fontMetrics3.descent - fontMetrics3.ascent) + 4.0f), (w / 2.0f) + (measureText2 / 2.0f) + 5.0f, h - 3.0f);
                    canvas2.drawRoundRect(mapRenderer.pillRect, 4.0f, 4.0f, mapRenderer.etaBgPaint);
                    canvas2.drawText(attribution, w / 2.0f, (mapRenderer.pillRect.top + 2.0f) - fontMetrics3.ascent, mapRenderer.attributionPaint);
                    bottomEdge2 = mapRenderer.pillRect.top - 3.0f;
                } catch (Exception e) {
                    e = e;
                    bottomEdge2 = bottomEdge2;
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
                    joystickTrail = f.getJoystickTrail();
                    if (joystickTrail == null) {
                    }
                    bottomEdge = bottomEdge2;
                    downHint = f.getDownHint();
                    if (downHint != null) {
                    }
                    float stackTop4 = etaTop;
                    phoneBattery = f.getPhoneBattery();
                    if (phoneBattery == null) {
                    }
                    zoomLabel = f.getZoomLabel();
                    if (zoomLabel != null) {
                    }
                    routingBadge = f.getRoutingBadge();
                    if (routingBadge == null) {
                    }
                    if (!f.getGpsLost()) {
                    }
                    if (f.getGpsLost()) {
                    }
                    mapRenderer.gpsPillText.setColor(f.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                    Paint.FontMetrics fm = mapRenderer.gpsPillText.getFontMetrics();
                    float textH = fm.descent - fm.ascent;
                    float pillW = mapRenderer.gpsPillText.measureText(label) + (14.0f * 2.0f);
                    float cxp = w / 2.0f;
                    float bottom = (6.0f * 2.0f) + 14.0f + textH;
                    mapRenderer.pillRect.set(cxp - (pillW / 2.0f), 14.0f, (pillW / 2.0f) + cxp, bottom);
                    float r = (bottom - 14.0f) / 2.0f;
                    canvas2.drawRoundRect(mapRenderer.pillRect, r, r, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, r, r, mapRenderer.etaBorderPaint);
                    canvas2.drawText(label, cxp, (14.0f + 6.0f) - fm.ascent, mapRenderer.gpsPillText);
                    notice = f.getNotice();
                    if (notice != null) {
                    }
                    pickerRows = f.getPickerRows();
                    if (pickerRows != null) {
                    }
                    if (f.getRiderLat() != null) {
                    }
                }
            }
            str2 = str;
        } catch (Exception e3) {
            e = e3;
        }
        try {
            joystickTrail = f.getJoystickTrail();
            if (joystickTrail == null) {
                Paint.FontMetrics fontMetrics4 = mapRenderer.etaSmallPaint.getFontMetrics();
                float measureText3 = mapRenderer.etaSmallPaint.measureText("WWWW") + (7.0f * 2.0f);
                float f11 = w / 2.0f;
                try {
                    mapRenderer.pillRect.set(f11 - (measureText3 / 2.0f), bottomEdge2 - (((3.0f * 2.0f) + fontMetrics4.descent) - fontMetrics4.ascent), f11 + (measureText3 / 2.0f), bottomEdge2);
                    canvas2.drawRoundRect(mapRenderer.pillRect, 6.0f, 6.0f, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, 6.0f, 6.0f, mapRenderer.etaBorderPaint);
                    canvas2.drawText(joystickTrail, f11, (mapRenderer.pillRect.top + 3.0f) - fontMetrics4.ascent, mapRenderer.etaSmallPaint);
                    bottomEdge2 = mapRenderer.pillRect.top - 4.0f;
                    Unit unit5 = Unit.INSTANCE;
                    Unit unit6 = Unit.INSTANCE;
                } catch (Exception e4) {
                    e = e4;
                    if (this.failedOverlays.add("joystick presses")) {
                        RideDiagnostics.INSTANCE.log(str2, "overlay 'joystick presses' failed: " + e);
                    }
                    bottomEdge = bottomEdge2;
                    downHint = f.getDownHint();
                    if (downHint != null) {
                    }
                    float stackTop42 = etaTop;
                    phoneBattery = f.getPhoneBattery();
                    if (phoneBattery == null) {
                    }
                    zoomLabel = f.getZoomLabel();
                    if (zoomLabel != null) {
                    }
                    routingBadge = f.getRoutingBadge();
                    if (routingBadge == null) {
                    }
                    if (!f.getGpsLost()) {
                    }
                    if (f.getGpsLost()) {
                    }
                    mapRenderer.gpsPillText.setColor(f.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                    Paint.FontMetrics fm2 = mapRenderer.gpsPillText.getFontMetrics();
                    float textH2 = fm2.descent - fm2.ascent;
                    float pillW2 = mapRenderer.gpsPillText.measureText(label) + (14.0f * 2.0f);
                    float cxp2 = w / 2.0f;
                    float bottom2 = (6.0f * 2.0f) + 14.0f + textH2;
                    mapRenderer.pillRect.set(cxp2 - (pillW2 / 2.0f), 14.0f, (pillW2 / 2.0f) + cxp2, bottom2);
                    float r2 = (bottom2 - 14.0f) / 2.0f;
                    canvas2.drawRoundRect(mapRenderer.pillRect, r2, r2, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, r2, r2, mapRenderer.etaBorderPaint);
                    canvas2.drawText(label, cxp2, (14.0f + 6.0f) - fm2.ascent, mapRenderer.gpsPillText);
                    notice = f.getNotice();
                    if (notice != null) {
                    }
                    pickerRows = f.getPickerRows();
                    if (pickerRows != null) {
                    }
                    if (f.getRiderLat() != null) {
                    }
                }
            }
            bottomEdge = bottomEdge2;
        } catch (Exception e5) {
            e = e5;
        }
        try {
            downHint = f.getDownHint();
            if (downHint != null) {
                Paint.FontMetrics fontMetrics5 = mapRenderer.etaSmallPaint.getFontMetrics();
                float measureText4 = mapRenderer.etaSmallPaint.measureText(downHint) + (9.0f * 2.0f);
                float f12 = w / 2.0f;
                try {
                    mapRenderer.pillRect.set(f12 - (measureText4 / 2.0f), bottomEdge - (((3.0f * 2.0f) + fontMetrics5.descent) - fontMetrics5.ascent), f12 + (measureText4 / 2.0f), bottomEdge);
                    float f13 = f2;
                    canvas2.drawRoundRect(mapRenderer.pillRect, f13, f13, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, f13, f13, mapRenderer.etaBorderPaint);
                    canvas2.drawText(downHint, f12, (mapRenderer.pillRect.top + 3.0f) - fontMetrics5.ascent, mapRenderer.etaSmallPaint);
                    Unit unit7 = Unit.INSTANCE;
                    Unit unit8 = Unit.INSTANCE;
                } catch (Exception e6) {
                    e = e6;
                    if (this.failedOverlays.add("down hint")) {
                        RideDiagnostics.INSTANCE.log(str2, "overlay 'down hint' failed: " + e);
                    }
                    float stackTop422 = etaTop;
                    phoneBattery = f.getPhoneBattery();
                    if (phoneBattery == null) {
                    }
                    zoomLabel = f.getZoomLabel();
                    if (zoomLabel != null) {
                    }
                    routingBadge = f.getRoutingBadge();
                    if (routingBadge == null) {
                    }
                    if (!f.getGpsLost()) {
                    }
                    if (f.getGpsLost()) {
                    }
                    mapRenderer.gpsPillText.setColor(f.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                    Paint.FontMetrics fm22 = mapRenderer.gpsPillText.getFontMetrics();
                    float textH22 = fm22.descent - fm22.ascent;
                    float pillW22 = mapRenderer.gpsPillText.measureText(label) + (14.0f * 2.0f);
                    float cxp22 = w / 2.0f;
                    float bottom22 = (6.0f * 2.0f) + 14.0f + textH22;
                    mapRenderer.pillRect.set(cxp22 - (pillW22 / 2.0f), 14.0f, (pillW22 / 2.0f) + cxp22, bottom22);
                    float r22 = (bottom22 - 14.0f) / 2.0f;
                    canvas2.drawRoundRect(mapRenderer.pillRect, r22, r22, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, r22, r22, mapRenderer.etaBorderPaint);
                    canvas2.drawText(label, cxp22, (14.0f + 6.0f) - fm22.ascent, mapRenderer.gpsPillText);
                    notice = f.getNotice();
                    if (notice != null) {
                    }
                    pickerRows = f.getPickerRows();
                    if (pickerRows != null) {
                    }
                    if (f.getRiderLat() != null) {
                    }
                }
            }
        } catch (Exception e7) {
            e = e7;
        }
        float stackTop4222 = etaTop;
        try {
            phoneBattery = f.getPhoneBattery();
            if (phoneBattery == null) {
                int intValue = phoneBattery.component1().intValue();
                boolean booleanValue = phoneBattery.component2().booleanValue();
                String str5 = intValue + "%";
                Paint.FontMetrics fontMetrics6 = mapRenderer.etaSmallPaint.getFontMetrics();
                try {
                    float f14 = fontMetrics6.descent - fontMetrics6.ascent;
                    float f15 = booleanValue ? (f14 * 0.55f) + 4.0f : 0.0f;
                    float f16 = (4.0f * 2.0f) + f14;
                    float f17 = stackTop4222 - 6.0f;
                    try {
                        try {
                            mapRenderer.pillRect.set((w - f3) - ((mapRenderer.etaSmallPaint.measureText(str5) + f15) + (9.0f * 2.0f)), f17 - f16, w - f3, f17);
                            canvas2.drawRoundRect(mapRenderer.pillRect, 8.0f, 8.0f, mapRenderer.etaBgPaint);
                            canvas2.drawRoundRect(mapRenderer.pillRect, 8.0f, 8.0f, mapRenderer.etaBorderPaint);
                            float f18 = mapRenderer.pillRect.left + 9.0f + f15;
                            if (booleanValue) {
                                float f19 = mapRenderer.pillRect.left + 9.0f;
                                float f20 = mapRenderer.pillRect.top + 4.0f + 1.0f;
                                float f21 = f14 - 2.0f;
                                float f22 = f14 * 0.55f;
                                mapRenderer.boltPath.reset();
                                mapRenderer.boltPath.moveTo(f19 + (0.65f * f22), f20);
                                mapRenderer.boltPath.lineTo(f19, (0.56f * f21) + f20);
                                mapRenderer.boltPath.lineTo((0.45f * f22) + f19, f20 + (0.56f * f21));
                                mapRenderer.boltPath.lineTo(f19 + (0.3f * f22), f20 + f21);
                                mapRenderer.boltPath.lineTo(f19 + f22, (0.4f * f21) + f20);
                                mapRenderer.boltPath.lineTo(f19 + (0.55f * f22), (0.4f * f21) + f20);
                                mapRenderer.boltPath.close();
                                canvas2.drawPath(mapRenderer.boltPath, mapRenderer.boltPaint);
                            }
                            int color = mapRenderer.etaSmallPaint.getColor();
                            Paint.Align textAlign = mapRenderer.etaSmallPaint.getTextAlign();
                            if (!booleanValue) {
                                mapRenderer.etaSmallPaint.setColor(intValue <= 15 ? mapRenderer.googleRed : mapRenderer.batteryYellow);
                            }
                            mapRenderer.etaSmallPaint.setTextAlign(Paint.Align.LEFT);
                            canvas2.drawText(str5, f18, (mapRenderer.pillRect.top + 4.0f) - fontMetrics6.ascent, mapRenderer.etaSmallPaint);
                            mapRenderer.etaSmallPaint.setColor(color);
                            mapRenderer.etaSmallPaint.setTextAlign(textAlign);
                            stackTop4222 = mapRenderer.pillRect.top;
                        } catch (Exception e8) {
                            e = e8;
                            stackTop4222 = stackTop4222;
                        }
                        try {
                            Unit unit9 = Unit.INSTANCE;
                            Unit unit10 = Unit.INSTANCE;
                        } catch (Exception e9) {
                            e = e9;
                            if (this.failedOverlays.add("battery")) {
                                RideDiagnostics.INSTANCE.log(str2, "overlay 'battery' failed: " + e);
                            }
                            zoomLabel = f.getZoomLabel();
                            if (zoomLabel != null) {
                            }
                            routingBadge = f.getRoutingBadge();
                            if (routingBadge == null) {
                            }
                            if (!f.getGpsLost()) {
                            }
                            if (f.getGpsLost()) {
                            }
                            mapRenderer.gpsPillText.setColor(f.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                            Paint.FontMetrics fm222 = mapRenderer.gpsPillText.getFontMetrics();
                            float textH222 = fm222.descent - fm222.ascent;
                            float pillW222 = mapRenderer.gpsPillText.measureText(label) + (14.0f * 2.0f);
                            float cxp222 = w / 2.0f;
                            float bottom222 = (6.0f * 2.0f) + 14.0f + textH222;
                            mapRenderer.pillRect.set(cxp222 - (pillW222 / 2.0f), 14.0f, (pillW222 / 2.0f) + cxp222, bottom222);
                            float r222 = (bottom222 - 14.0f) / 2.0f;
                            canvas2.drawRoundRect(mapRenderer.pillRect, r222, r222, mapRenderer.etaBgPaint);
                            canvas2.drawRoundRect(mapRenderer.pillRect, r222, r222, mapRenderer.etaBorderPaint);
                            canvas2.drawText(label, cxp222, (14.0f + 6.0f) - fm222.ascent, mapRenderer.gpsPillText);
                            notice = f.getNotice();
                            if (notice != null) {
                            }
                            pickerRows = f.getPickerRows();
                            if (pickerRows != null) {
                            }
                            if (f.getRiderLat() != null) {
                                return;
                            }
                        }
                    } catch (Exception e10) {
                        e = e10;
                        stackTop4222 = stackTop4222;
                    }
                } catch (Exception e11) {
                    e = e11;
                    stackTop4222 = stackTop4222;
                }
            }
        } catch (Exception e12) {
            e = e12;
        }
        try {
            zoomLabel = f.getZoomLabel();
            if (zoomLabel != null) {
                Paint.FontMetrics fontMetrics7 = mapRenderer.etaSmallPaint.getFontMetrics();
                float f23 = stackTop4222 - 6.0f;
                stackTop = stackTop4222;
                try {
                    try {
                        mapRenderer.pillRect.set((w - f3) - (mapRenderer.etaSmallPaint.measureText(zoomLabel) + (9.0f * 2.0f)), f23 - (((4.0f * 2.0f) + fontMetrics7.descent) - fontMetrics7.ascent), w - f3, f23);
                        canvas2.drawRoundRect(mapRenderer.pillRect, 8.0f, 8.0f, mapRenderer.etaBgPaint);
                        canvas2.drawRoundRect(mapRenderer.pillRect, 8.0f, 8.0f, mapRenderer.etaBorderPaint);
                        canvas2.drawText(zoomLabel, mapRenderer.pillRect.centerX(), (mapRenderer.pillRect.top + 4.0f) - fontMetrics7.ascent, mapRenderer.etaSmallPaint);
                        stackTop4222 = mapRenderer.pillRect.top;
                    } catch (Exception e13) {
                        e = e13;
                        stackTop4222 = stackTop;
                    }
                } catch (Exception e14) {
                    e = e14;
                    stackTop4222 = stackTop;
                }
                try {
                    Unit unit11 = Unit.INSTANCE;
                    Unit unit12 = Unit.INSTANCE;
                } catch (Exception e15) {
                    e = e15;
                    if (this.failedOverlays.add("zoom")) {
                        RideDiagnostics.INSTANCE.log(str2, "overlay 'zoom' failed: " + e);
                    }
                    routingBadge = f.getRoutingBadge();
                    if (routingBadge == null) {
                    }
                    if (!f.getGpsLost()) {
                    }
                    if (f.getGpsLost()) {
                    }
                    mapRenderer.gpsPillText.setColor(f.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                    Paint.FontMetrics fm2222 = mapRenderer.gpsPillText.getFontMetrics();
                    float textH2222 = fm2222.descent - fm2222.ascent;
                    float pillW2222 = mapRenderer.gpsPillText.measureText(label) + (14.0f * 2.0f);
                    float cxp2222 = w / 2.0f;
                    float bottom2222 = (6.0f * 2.0f) + 14.0f + textH2222;
                    mapRenderer.pillRect.set(cxp2222 - (pillW2222 / 2.0f), 14.0f, (pillW2222 / 2.0f) + cxp2222, bottom2222);
                    float r2222 = (bottom2222 - 14.0f) / 2.0f;
                    canvas2.drawRoundRect(mapRenderer.pillRect, r2222, r2222, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, r2222, r2222, mapRenderer.etaBorderPaint);
                    canvas2.drawText(label, cxp2222, (14.0f + 6.0f) - fm2222.ascent, mapRenderer.gpsPillText);
                    notice = f.getNotice();
                    if (notice != null) {
                    }
                    pickerRows = f.getPickerRows();
                    if (pickerRows != null) {
                    }
                    if (f.getRiderLat() != null) {
                    }
                }
            } else {
                stackTop = stackTop4222;
            }
        } catch (Exception e16) {
            e = e16;
            stackTop = stackTop4222;
        }
        try {
            routingBadge = f.getRoutingBadge();
            if (routingBadge == null) {
                try {
                    try {
                        switch (WhenMappings.$EnumSwitchMapping$1[routingBadge.ordinal()]) {
                            case 1:
                                pair = TuplesKt.to("G", Integer.valueOf(ROUTING_GREEN));
                                String str6 = (String) pair.component1();
                                int intValue2 = ((Number) pair.component2()).intValue();
                                Paint.FontMetrics fontMetrics8 = mapRenderer.etaSmallPaint.getFontMetrics();
                                stackTop2 = stackTop4222;
                                try {
                                    float f24 = stackTop2 - 6.0f;
                                    mapRenderer.pillRect.set((w - f3) - (Math.max(mapRenderer.etaSmallPaint.measureText("BR"), mapRenderer.etaSmallPaint.measureText(str6)) + (9.0f * 2.0f)), f24 - (((4.0f * 2.0f) + fontMetrics8.descent) - fontMetrics8.ascent), w - f3, f24);
                                    mapRenderer.routingBadgePaint.setColor(intValue2);
                                    canvas2.drawRoundRect(mapRenderer.pillRect, 8.0f, 8.0f, mapRenderer.routingBadgePaint);
                                    int color2 = mapRenderer.etaSmallPaint.getColor();
                                    boolean isFakeBoldText = mapRenderer.etaSmallPaint.isFakeBoldText();
                                    mapRenderer.etaSmallPaint.setColor(-1);
                                    mapRenderer.etaSmallPaint.setFakeBoldText(true);
                                    canvas2.drawText(str6, mapRenderer.pillRect.centerX(), (mapRenderer.pillRect.top + 4.0f) - fontMetrics8.ascent, mapRenderer.etaSmallPaint);
                                    mapRenderer.etaSmallPaint.setColor(color2);
                                    mapRenderer.etaSmallPaint.setFakeBoldText(isFakeBoldText);
                                    stackTop3 = mapRenderer.pillRect.top;
                                } catch (Exception e17) {
                                    e = e17;
                                    stackTop4222 = stackTop2;
                                }
                                try {
                                    Unit unit13 = Unit.INSTANCE;
                                    Unit unit14 = Unit.INSTANCE;
                                    stackTop4222 = stackTop3;
                                    break;
                                } catch (Exception e18) {
                                    e = e18;
                                    stackTop4222 = stackTop3;
                                    if (this.failedOverlays.add("routing source")) {
                                        RideDiagnostics.INSTANCE.log(str2, "overlay 'routing source' failed: " + e);
                                    }
                                    if (!f.getGpsLost()) {
                                    }
                                    if (f.getGpsLost()) {
                                    }
                                    mapRenderer.gpsPillText.setColor(f.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                                    Paint.FontMetrics fm22222 = mapRenderer.gpsPillText.getFontMetrics();
                                    float textH22222 = fm22222.descent - fm22222.ascent;
                                    float pillW22222 = mapRenderer.gpsPillText.measureText(label) + (14.0f * 2.0f);
                                    float cxp22222 = w / 2.0f;
                                    float bottom22222 = (6.0f * 2.0f) + 14.0f + textH22222;
                                    mapRenderer.pillRect.set(cxp22222 - (pillW22222 / 2.0f), 14.0f, (pillW22222 / 2.0f) + cxp22222, bottom22222);
                                    float r22222 = (bottom22222 - 14.0f) / 2.0f;
                                    canvas2.drawRoundRect(mapRenderer.pillRect, r22222, r22222, mapRenderer.etaBgPaint);
                                    canvas2.drawRoundRect(mapRenderer.pillRect, r22222, r22222, mapRenderer.etaBorderPaint);
                                    canvas2.drawText(label, cxp22222, (14.0f + 6.0f) - fm22222.ascent, mapRenderer.gpsPillText);
                                    notice = f.getNotice();
                                    if (notice != null) {
                                    }
                                    pickerRows = f.getPickerRows();
                                    if (pickerRows != null) {
                                    }
                                    if (f.getRiderLat() != null) {
                                    }
                                }
                                break;
                            case 2:
                                pair = TuplesKt.to("O", Integer.valueOf(ROUTING_ORANGE));
                                String str62 = (String) pair.component1();
                                int intValue22 = ((Number) pair.component2()).intValue();
                                Paint.FontMetrics fontMetrics82 = mapRenderer.etaSmallPaint.getFontMetrics();
                                stackTop2 = stackTop4222;
                                float f242 = stackTop2 - 6.0f;
                                mapRenderer.pillRect.set((w - f3) - (Math.max(mapRenderer.etaSmallPaint.measureText("BR"), mapRenderer.etaSmallPaint.measureText(str62)) + (9.0f * 2.0f)), f242 - (((4.0f * 2.0f) + fontMetrics82.descent) - fontMetrics82.ascent), w - f3, f242);
                                mapRenderer.routingBadgePaint.setColor(intValue22);
                                canvas2.drawRoundRect(mapRenderer.pillRect, 8.0f, 8.0f, mapRenderer.routingBadgePaint);
                                int color22 = mapRenderer.etaSmallPaint.getColor();
                                boolean isFakeBoldText2 = mapRenderer.etaSmallPaint.isFakeBoldText();
                                mapRenderer.etaSmallPaint.setColor(-1);
                                mapRenderer.etaSmallPaint.setFakeBoldText(true);
                                canvas2.drawText(str62, mapRenderer.pillRect.centerX(), (mapRenderer.pillRect.top + 4.0f) - fontMetrics82.ascent, mapRenderer.etaSmallPaint);
                                mapRenderer.etaSmallPaint.setColor(color22);
                                mapRenderer.etaSmallPaint.setFakeBoldText(isFakeBoldText2);
                                stackTop3 = mapRenderer.pillRect.top;
                                Unit unit132 = Unit.INSTANCE;
                                Unit unit142 = Unit.INSTANCE;
                                stackTop4222 = stackTop3;
                                break;
                            case 3:
                                pair = TuplesKt.to("BR", Integer.valueOf(ROUTING_BROWN));
                                String str622 = (String) pair.component1();
                                int intValue222 = ((Number) pair.component2()).intValue();
                                Paint.FontMetrics fontMetrics822 = mapRenderer.etaSmallPaint.getFontMetrics();
                                stackTop2 = stackTop4222;
                                float f2422 = stackTop2 - 6.0f;
                                mapRenderer.pillRect.set((w - f3) - (Math.max(mapRenderer.etaSmallPaint.measureText("BR"), mapRenderer.etaSmallPaint.measureText(str622)) + (9.0f * 2.0f)), f2422 - (((4.0f * 2.0f) + fontMetrics822.descent) - fontMetrics822.ascent), w - f3, f2422);
                                mapRenderer.routingBadgePaint.setColor(intValue222);
                                canvas2.drawRoundRect(mapRenderer.pillRect, 8.0f, 8.0f, mapRenderer.routingBadgePaint);
                                int color222 = mapRenderer.etaSmallPaint.getColor();
                                boolean isFakeBoldText22 = mapRenderer.etaSmallPaint.isFakeBoldText();
                                mapRenderer.etaSmallPaint.setColor(-1);
                                mapRenderer.etaSmallPaint.setFakeBoldText(true);
                                canvas2.drawText(str622, mapRenderer.pillRect.centerX(), (mapRenderer.pillRect.top + 4.0f) - fontMetrics822.ascent, mapRenderer.etaSmallPaint);
                                mapRenderer.etaSmallPaint.setColor(color222);
                                mapRenderer.etaSmallPaint.setFakeBoldText(isFakeBoldText22);
                                stackTop3 = mapRenderer.pillRect.top;
                                Unit unit1322 = Unit.INSTANCE;
                                Unit unit1422 = Unit.INSTANCE;
                                stackTop4222 = stackTop3;
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                    } catch (Exception e19) {
                        e = e19;
                    }
                } catch (Exception e20) {
                    e = e20;
                    stackTop4222 = stackTop;
                }
            }
        } catch (Exception e21) {
            e = e21;
        }
        if (!f.getGpsLost() || f.getGpsWeak()) {
            String label = f.getGpsLost() ? "GPS SİNYALİ YOK" : "GPS SİNYALİ ZAYIF";
            mapRenderer.gpsPillText.setColor(f.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
            Paint.FontMetrics fm222222 = mapRenderer.gpsPillText.getFontMetrics();
            float textH222222 = fm222222.descent - fm222222.ascent;
            float pillW222222 = mapRenderer.gpsPillText.measureText(label) + (14.0f * 2.0f);
            float cxp222222 = w / 2.0f;
            float bottom222222 = (6.0f * 2.0f) + 14.0f + textH222222;
            mapRenderer.pillRect.set(cxp222222 - (pillW222222 / 2.0f), 14.0f, (pillW222222 / 2.0f) + cxp222222, bottom222222);
            float r222222 = (bottom222222 - 14.0f) / 2.0f;
            canvas2.drawRoundRect(mapRenderer.pillRect, r222222, r222222, mapRenderer.etaBgPaint);
            canvas2.drawRoundRect(mapRenderer.pillRect, r222222, r222222, mapRenderer.etaBorderPaint);
            canvas2.drawText(label, cxp222222, (14.0f + 6.0f) - fm222222.ascent, mapRenderer.gpsPillText);
        }
        notice = f.getNotice();
        if (notice != null) {
            i3 = h;
            mapRenderer.drawNotice(canvas2, w, i3, notice);
            Unit unit15 = Unit.INSTANCE;
            Unit unit16 = Unit.INSTANCE;
        } else {
            i3 = h;
        }
        pickerRows = f.getPickerRows();
        if (pickerRows != null) {
            mapRenderer2 = mapRenderer;
            canvas3 = canvas2;
            mapRenderer2.drawPicker(canvas3, w, i3, f.getPickerTitle(), pickerRows, f.getPickerIndex(), f.getPickerSelectHint());
            Unit unit17 = Unit.INSTANCE;
            Unit unit18 = Unit.INSTANCE;
        } else {
            mapRenderer2 = mapRenderer;
            canvas3 = canvas2;
        }
        if (f.getRiderLat() != null && f.getDestLat() == null) {
            mapRenderer2.standbyPaint.getTextBounds("HIMALAYAN 450 · GPS BEKLENİYOR", 0, "HIMALAYAN 450 · GPS BEKLENİYOR".length(), mapRenderer2.textBounds);
            canvas3.drawText("HIMALAYAN 450 · GPS BEKLENİYOR", (w - mapRenderer2.textBounds.width()) / 2.0f, i3 / 2.0f, mapRenderer2.standbyPaint);
        }
    }

    private static final float draw$sx(Frame $f, int ts, double left, double lng) {
        return (float) ((Mercator.INSTANCE.lngToTileX(lng, $f.getZoom()) * ts) - left);
    }

    private static final float draw$sy(Frame $f, int ts, double top, double lat) {
        return (float) ((Mercator.INSTANCE.latToTileY(lat, $f.getZoom()) * ts) - top);
    }

    private final void drawPicker(Canvas canvas, int w, int h, String title, List<MenuRow> items, int index, String selectHint) {
        float maxTextW;
        float right;
        int sel;
        int rgb;
        float textX;
        int rgb2;
        int rows = Math.min(4, items.size());
        float right2 = (w / 2.0f) + 190.0f;
        float panelW = right2 - PICKER_LEFT;
        float panelH = 6.0f + 28.0f + (rows * 40.0f) + 26.0f + 6.0f;
        float cx = (PICKER_LEFT + right2) / 2.0f;
        float cy = h / 2.0f;
        float rowH = cy - (panelH / 2.0f);
        float headH = cy + (panelH / 2.0f);
        this.pillRect.set(PICKER_LEFT, rowH, right2, headH);
        canvas.drawRoundRect(this.pillRect, 12.0f, 12.0f, this.pickerBgPaint);
        canvas.drawRoundRect(this.pillRect, 12.0f, 12.0f, this.etaBorderPaint);
        float y = this.pillRect.top + 6.0f;
        canvas.drawText(title, cx, ((28.0f / 2.0f) + y) - ((this.pickerTitlePaint.descent() + this.pickerTitlePaint.ascent()) / 2.0f), this.pickerTitlePaint);
        float y2 = y + 28.0f;
        int sel2 = RangesKt.coerceIn(index, 0, CollectionsKt.getLastIndex(items));
        int first = RangesKt.coerceIn(sel2 - (rows / 2), 0, Math.max(0, items.size() - rows));
        float textX2 = this.pillRect.left + 16.0f;
        float maxTextW2 = panelW - 32.0f;
        int first2 = first + rows;
        int rows2 = first;
        while (rows2 < first2) {
            int i = first2;
            MenuRow row = items.get(rows2);
            if (row.getHeader()) {
                float ty = (y2 + (40.0f / 2.0f)) - ((this.pickerSectionPaint.descent() + this.pickerSectionPaint.ascent()) / 2.0f);
                right = right2;
                String upperCase = row.getLabel().toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                maxTextW = maxTextW2;
                canvas.drawText(ellipsize(upperCase, this.pickerSectionPaint, maxTextW2), textX2 - 6.0f, ty, this.pickerSectionPaint);
                y2 += 40.0f;
                sel = sel2;
                textX = textX2;
            } else {
                maxTextW = maxTextW2;
                right = right2;
                sel = sel2;
                boolean selected = rows2 == sel2;
                this.pickerRow.set(this.pillRect.left + 5.0f, y2 + 1.0f, this.pillRect.right - 5.0f, (y2 + 40.0f) - 1.0f);
                if (selected) {
                    canvas.drawRoundRect(this.pickerRow, 7.0f, 7.0f, this.pickerHiPaint);
                }
                Paint paint = this.pickerItemPaint;
                if (selected) {
                    rgb = Color.rgb(20, 22, 26);
                } else {
                    rgb = row.getActive() ? ACTIVE_GREEN : Color.rgb(236, 238, 241);
                }
                paint.setColor(rgb);
                this.pickerItemPaint.setFakeBoldText(selected || row.getActive());
                String mark = row.getActive() ? "✓" : row.getSubmenu() ? "›" : null;
                float markW = mark != null ? 28.0f : 0.0f;
                float textY = (y2 + (40.0f / 2.0f)) - ((this.pickerItemPaint.descent() + this.pickerItemPaint.ascent()) / 2.0f);
                String label = ellipsize(row.getLabel(), this.pickerItemPaint, maxTextW - markW);
                canvas.drawText(label, textX2, textY, this.pickerItemPaint);
                if (mark != null) {
                    Paint paint2 = this.pickerMarkPaint;
                    if (selected) {
                        textX = textX2;
                        rgb2 = Color.rgb(20, 22, 26);
                    } else {
                        textX = textX2;
                        rgb2 = row.getActive() ? ACTIVE_GREEN : Color.rgb(150, 156, 164);
                    }
                    paint2.setColor(rgb2);
                    canvas.drawText(mark, this.pillRect.right - 16.0f, textY, this.pickerMarkPaint);
                } else {
                    textX = textX2;
                }
                y2 += 40.0f;
            }
            rows2++;
            first2 = i;
            right2 = right;
            maxTextW2 = maxTextW;
            textX2 = textX;
            sel2 = sel;
        }
        canvas.drawText(ellipsize(selectHint, this.pickerHintPaint, panelW - 10.0f), cx, (y2 + (26.0f / 2.0f)) - ((this.pickerHintPaint.descent() + this.pickerHintPaint.ascent()) / 2.0f), this.pickerHintPaint);
    }

    private final void drawNotice(Canvas canvas, int w, int h, List<String> lines) {
        float textW;
        float panelW;
        int i;
        if (lines.isEmpty()) {
            return;
        }
        float lineH = 30.0f;
        float padV = 8.0f;
        float maxTextW = 316.0f;
        List<String> list = lines;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            float lineH2 = lineH;
            this.pickerItemPaint.setFakeBoldText(i2 == 0);
            arrayList.add(ellipsize(str, this.pickerItemPaint, 316.0f));
            i2 = i3;
            lineH = lineH2;
        }
        float lineH3 = lineH;
        List texts = (List) arrayList;
        float textW2 = 0.0f;
        int i4 = 0;
        for (Object obj2 : texts) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) obj2;
            this.pickerItemPaint.setFakeBoldText(i4 == 0);
            textW2 = Math.max(textW2, this.pickerItemPaint.measureText(str2));
            i4 = i5;
        }
        float panelW2 = 24.0f + textW2;
        float panelH = (8.0f * 2.0f) + (texts.size() * lineH3);
        float cx = w / 2.0f;
        float bottom = (h / 2.0f) - NOTICE_GAP_ABOVE_CENTRE;
        this.pillRect.set(cx - (panelW2 / 2.0f), bottom - panelH, cx + (panelW2 / 2.0f), bottom);
        canvas.drawRoundRect(this.pillRect, 10.0f, 10.0f, this.pickerBgPaint);
        canvas.drawRoundRect(this.pillRect, 10.0f, 10.0f, this.etaBorderPaint);
        Paint.Align align = this.pickerItemPaint.getTextAlign();
        this.pickerItemPaint.setTextAlign(Paint.Align.CENTER);
        float y = this.pillRect.top + 8.0f;
        int i6 = 0;
        for (Object obj3 : texts) {
            int i7 = i6 + 1;
            if (i6 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            List texts2 = texts;
            String str3 = (String) obj3;
            float padV2 = padV;
            float maxTextW2 = maxTextW;
            this.pickerItemPaint.setFakeBoldText(i6 == 0);
            Paint paint = this.pickerItemPaint;
            if (i6 == 0) {
                textW = textW2;
                panelW = panelW2;
                i = Color.rgb(236, 238, 241);
            } else {
                textW = textW2;
                panelW = panelW2;
                i = ACTIVE_GREEN;
            }
            paint.setColor(i);
            canvas.drawText(str3, cx, ((lineH3 / 2.0f) + y) - ((this.pickerItemPaint.descent() + this.pickerItemPaint.ascent()) / 2.0f), this.pickerItemPaint);
            y += lineH3;
            i6 = i7;
            texts = texts2;
            padV = padV2;
            maxTextW = maxTextW2;
            textW2 = textW;
            panelW2 = panelW;
        }
        this.pickerItemPaint.setTextAlign(align);
        this.pickerItemPaint.setFakeBoldText(false);
    }

    private final String ellipsize(String s, Paint p, float maxW) {
        if (p.measureText(s) <= maxW) {
            return s;
        }
        int end = s.length();
        while (end > 1 && p.measureText(s, 0, end) + p.measureText("…") > maxW) {
            end--;
        }
        String substring = s.substring(0, end);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring + "…";
    }

    private final void placeInSafeCircle(int w, int h, float bw, float bh, float angleDeg, RectF out) {
        float f;
        float b = 2.0f;
        float cx = w / 2.0f;
        float cy = h / 2.0f;
        float radius = (h / 2.0f) * SAFE_RADIUS_FRAC;
        float ux = (float) Math.cos(Math.toRadians(angleDeg));
        float uy = (float) Math.sin(Math.toRadians(angleDeg));
        float best = 0.0f;
        float d = 0.0f;
        while (true) {
            if (d > radius) {
                f = b;
                break;
            }
            float bx = (ux * d) + cx;
            float by = (uy * d) + cy;
            float l = bx - (bw / b);
            float t = by - (bh / b);
            float rr = (bw / b) + bx;
            f = b;
            float b2 = by + (bh / b);
            if (!placeInSafeCircle$inside(cx, cy, radius, l, t) || !placeInSafeCircle$inside(cx, cy, radius, rr, t) || !placeInSafeCircle$inside(cx, cy, radius, l, b2) || !placeInSafeCircle$inside(cx, cy, radius, rr, b2)) {
                break;
            }
            best = d;
            d += 1.0f;
            b = f;
        }
        float bx2 = (ux * best) + cx;
        float by2 = (uy * best) + cy;
        out.set(bx2 - (bw / f), by2 - (bh / f), (bw / f) + bx2, (bh / f) + by2);
    }

    private static final boolean placeInSafeCircle$inside(float cx, float cy, float radius, float x, float y) {
        return ((x - cx) * (x - cx)) + ((y - cy) * (y - cy)) <= radius * radius;
    }

    private final float normalizeHeading(float degrees) {
        float value = degrees % 360.0f;
        if (value < 0.0f) {
            return value + 360.0f;
        }
        return value;
    }

    private final void drawTileBestEffort(Canvas canvas, int z, int tx, int ty, RectF dst) {
        Object obj;
        int i = z;
        Bitmap bitmap = this.tiles.get(i, tx, ty);
        Object obj2 = null;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (Rect) null, dst, this.tilePaint);
            return;
        }
        for (int up = 1; up <= 4 && i - up >= 0; up++) {
            Bitmap anc = this.tiles.getCached(i - up, tx >> up, ty >> up);
            if (anc != null) {
                int cells = 1 << up;
                int sub = anc.getWidth() / cells;
                int sxOff = ((cells - 1) & tx) * sub;
                int syOff = ((cells - 1) & ty) * sub;
                this.tileSrc.set(sxOff, syOff, sxOff + sub, syOff + sub);
                canvas.drawBitmap(anc, this.tileSrc, dst, this.tilePaint);
                return;
            }
        }
        float halfW = (dst.right - dst.left) / 2.0f;
        float halfH = (dst.bottom - dst.top) / 2.0f;
        int qx = 0;
        while (true) {
            if (qx >= 2) {
                return;
            }
            int qy = 0;
            for (int i2 = 2; qy < i2; i2 = 2) {
                Bitmap child = this.tiles.getCached(i + 1, (tx * 2) + qx, (ty * 2) + qy);
                if (child == null) {
                    obj = obj2;
                } else {
                    this.childDst.set(dst.left + (qx * halfW), dst.top + (qy * halfH), dst.left + (qx * halfW) + halfW, dst.top + (qy * halfH) + halfH);
                    obj = null;
                    canvas.drawBitmap(child, (Rect) null, this.childDst, this.tilePaint);
                }
                qy++;
                i = z;
                obj2 = obj;
            }
            qx++;
            i = z;
        }
    }

    /* compiled from: MapRenderer.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/example/wayfindr/dash/map/MapRenderer$Companion;", "", "<init>", "()V", "SAFE_RADIUS_FRAC", "", "ETA_ANGLE_DEG", "ETA_MARGIN_RIGHT", "ETA_MARGIN_BOTTOM", "PICKER_W", "ROUTING_GREEN", "", "ROUTING_ORANGE", "ROUTING_BROWN", "NOTICE_W", "NOTICE_GAP_ABOVE_CENTRE", "ACTIVE_GREEN", "PICKER_ROWS", "PICKER_LEFT", "nightTileMatrix", "Landroid/graphics/ColorMatrix;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ColorMatrix nightTileMatrix() {
            ColorMatrix invert = new ColorMatrix(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
            ColorMatrix hue = new ColorMatrix(new float[]{-0.574f, 1.43f, 0.144f, 0.0f, 0.0f, 0.426f, 0.43f, 0.144f, 0.0f, 0.0f, 0.426f, 1.43f, -0.856f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
            ColorMatrix sat = new ColorMatrix();
            sat.setSaturation(1.15f);
            ColorMatrix dim = new ColorMatrix(new float[]{0.86f, 0.0f, 0.0f, 0.0f, 6.0f, 0.0f, 0.86f, 0.0f, 0.0f, 6.0f, 0.0f, 0.0f, 0.86f, 0.0f, 6.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
            ColorMatrix out = new ColorMatrix(invert);
            out.postConcat(hue);
            out.postConcat(sat);
            out.postConcat(dim);
            return out;
        }
    }
}
