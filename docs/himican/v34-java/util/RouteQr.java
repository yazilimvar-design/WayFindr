package com.example.wayfindr.util;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.autofill.HintConstants;
import androidx.core.content.FileProvider;
import com.example.wayfindr.MainActivity;
import com.example.wayfindr.data.SavedLocation;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.maplibre.android.style.layers.Property;

/* compiled from: RouteQr.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0007J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0007J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0002¨\u0006\u001b"}, d2 = {"Lcom/example/wayfindr/util/RouteQr;", "", "<init>", "()V", "matrix", "Lcom/google/zxing/common/BitMatrix;", "text", "", "image", "Landroid/graphics/Bitmap;", "loc", "Lcom/example/wayfindr/data/SavedLocation;", "shareImage", "", "context", "Landroid/content/Context;", "bmp", HintConstants.AUTOFILL_HINT_NAME, "saveToGallery", "", "read", "uri", "Landroid/net/Uri;", "decode", Property.SYMBOL_Z_ORDER_SOURCE, "Lcom/google/zxing/LuminanceSource;", "fileName", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class RouteQr {
    public static final int $stable = 0;
    public static final RouteQr INSTANCE = new RouteQr();

    private RouteQr() {
    }

    public final BitMatrix matrix(String text) {
        String text2;
        Intrinsics.checkNotNullParameter(text, "text");
        for (ErrorCorrectionLevel level : CollectionsKt.listOf((Object[]) new ErrorCorrectionLevel[]{ErrorCorrectionLevel.M, ErrorCorrectionLevel.L})) {
            try {
                text2 = text;
                try {
                    return new QRCodeWriter().encode(text2, BarcodeFormat.QR_CODE, 0, 0, MapsKt.mapOf(TuplesKt.to(EncodeHintType.ERROR_CORRECTION, level), TuplesKt.to(EncodeHintType.CHARACTER_SET, "UTF-8"), TuplesKt.to(EncodeHintType.MARGIN, 0)));
                } catch (WriterException e) {
                    text = text2;
                }
            } catch (WriterException e2) {
                text2 = text;
            }
        }
        return null;
    }

    public final Bitmap image(SavedLocation loc) {
        String str;
        int i;
        Canvas c;
        Intrinsics.checkNotNullParameter(loc, "loc");
        List stops = RouteShare.INSTANCE.stopsOf(loc);
        BitMatrix m = matrix(RouteShare.INSTANCE.link(loc.getName(), stops));
        if (m == null) {
            return null;
        }
        int module = (1080 - 32) / (m.getWidth() + 8);
        int qrPx = m.getWidth() * module;
        int quiet = module * 4;
        TextPaint title = new TextPaint(1);
        title.setColor(-16777216);
        title.setTextSize(64.0f);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        StaticLayout titleLayout = StaticLayout.Builder.obtain(loc.getName(), 0, loc.getName().length(), title, 1080 - (72 * 2)).setAlignment(Layout.Alignment.ALIGN_CENTER).setMaxLines(3).setEllipsize(TextUtils.TruncateAt.END).build();
        Intrinsics.checkNotNullExpressionValue(titleLayout, "build(...)");
        TextPaint textPaint = new TextPaint(1);
        textPaint.setColor(Color.rgb(85, 91, 96));
        textPaint.setTextSize(36.0f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        if (stops.size() == 1) {
            str = "1 stop";
        } else {
            str = stops.size() + " stops";
        }
        String subText = str + " · scan to open in Wayfindr";
        int top = titleLayout.getHeight() + 72 + 20 + 44;
        int height = top + quiet + qrPx + quiet;
        Bitmap bmp = Bitmap.createBitmap(1080, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bmp, "createBitmap(...)");
        Canvas c2 = new Canvas(bmp);
        c2.drawColor(-1);
        c2.save();
        c2.translate(72, 72);
        titleLayout.draw(c2);
        c2.restore();
        c2.drawText(subText, 1080 / 2.0f, 72 + titleLayout.getHeight() + 20 + 36, textPaint);
        Paint black = new Paint();
        black.setColor(-16777216);
        float left = (1080 - qrPx) / 2.0f;
        float qrTop = top + quiet;
        int y = 0;
        int height2 = m.getHeight();
        while (y < height2) {
            int i2 = height2;
            int width = m.getWidth();
            float left2 = left;
            int x = 0;
            while (x < width) {
                if (m.get(x, y)) {
                    i = width;
                    c = c2;
                    c.drawRect(left2 + (x * module), qrTop + (y * module), left2 + ((x + 1) * module), qrTop + ((y + 1) * module), black);
                } else {
                    i = width;
                    c = c2;
                }
                x++;
                c2 = c;
                width = i;
            }
            y++;
            height2 = i2;
            left = left2;
        }
        return bmp;
    }

    public final void shareImage(Context context, Bitmap bmp, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bmp, "bmp");
        Intrinsics.checkNotNullParameter(name, "name");
        File dir = new File(context.getCacheDir(), "route_qr");
        dir.mkdirs();
        File[] listFiles = dir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                file.delete();
            }
        }
        File file2 = new File(dir, fileName(name));
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        try {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            CloseableKt.closeFinally(fileOutputStream, null);
            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file2);
            Intent send = new Intent("android.intent.action.SEND");
            send.setType("image/png");
            send.putExtra("android.intent.extra.STREAM", uri);
            send.putExtra("android.intent.extra.SUBJECT", "Wayfindr route: " + name);
            send.setClipData(ClipData.newRawUri(name, uri));
            send.addFlags(1);
            Intent chooser = Intent.createChooser(send, "Share " + name).putExtra("android.intent.extra.EXCLUDE_COMPONENTS", new ComponentName[]{new ComponentName(context, (Class<?>) MainActivity.class)}).addFlags(268435456);
            Intrinsics.checkNotNullExpressionValue(chooser, "addFlags(...)");
            context.startActivity(chooser);
        } finally {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean saveToGallery(Context context, Bitmap bmp, String name) {
        boolean z;
        Object m8392constructorimpl;
        ContentResolver contentResolver;
        Uri insert;
        OutputStream outputStream;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bmp, "bmp");
        Intrinsics.checkNotNullParameter(name, "name");
        try {
            Result.Companion companion = Result.INSTANCE;
            contentResolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", fileName(name));
            contentValues.put("mime_type", "image/png");
            contentValues.put("relative_path", "Pictures/Wayfindr");
            contentValues.put("is_pending", (Integer) 1);
            insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            Intrinsics.checkNotNull(insert);
        } catch (Throwable th) {
            th = th;
            z = false;
        }
        try {
            try {
                try {
                    OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                    Intrinsics.checkNotNull(openOutputStream);
                    outputStream = openOutputStream;
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion2 = Result.INSTANCE;
                    m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                    Boolean valueOf = Boolean.valueOf(z);
                    if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                    }
                    return ((Boolean) m8392constructorimpl).booleanValue();
                }
                try {
                    z = false;
                    try {
                        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                        CloseableKt.closeFinally(outputStream, null);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("is_pending", (Integer) 0);
                        Unit unit = Unit.INSTANCE;
                        contentResolver.update(insert, contentValues2, null, null);
                        m8392constructorimpl = Result.m8392constructorimpl(true);
                        Boolean valueOf2 = Boolean.valueOf(z);
                        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                            m8392constructorimpl = valueOf2;
                        }
                        return ((Boolean) m8392constructorimpl).booleanValue();
                    } catch (Throwable th3) {
                        th = th3;
                        Throwable th4 = th;
                        try {
                            throw th4;
                        } catch (Throwable th5) {
                            CloseableKt.closeFinally(outputStream, th4);
                            throw th5;
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Exception e) {
                e = e;
                contentResolver.delete(insert, null, null);
                throw e;
            }
        } catch (Exception e2) {
            e = e2;
            contentResolver.delete(insert, null, null);
            throw e;
        }
    }

    public final String read(Context context, Uri uri) {
        Object m8392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            Result.Companion companion = Result.INSTANCE;
            RouteQr routeQr = this;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            Intrinsics.checkNotNull(openInputStream);
            InputStream inputStream = openInputStream;
            try {
                BitmapFactory.decodeStream(inputStream, null, options);
                CloseableKt.closeFinally(inputStream, null);
                int i = 1;
                while (Math.max(options.outWidth, options.outHeight) / (i * 2) >= 1600) {
                    i *= 2;
                }
                InputStream openInputStream2 = context.getContentResolver().openInputStream(uri);
                Intrinsics.checkNotNull(openInputStream2);
                inputStream = openInputStream2;
                try {
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inSampleSize = i;
                    Unit unit = Unit.INSTANCE;
                    Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options2);
                    CloseableKt.closeFinally(inputStream, null);
                    Intrinsics.checkNotNull(decodeStream);
                    int[] iArr = new int[decodeStream.getWidth() * decodeStream.getHeight()];
                    decodeStream.getPixels(iArr, 0, decodeStream.getWidth(), 0, 0, decodeStream.getWidth(), decodeStream.getHeight());
                    m8392constructorimpl = Result.m8392constructorimpl(routeQr.decode(new RGBLuminanceSource(decodeStream.getWidth(), decodeStream.getHeight(), iArr)));
                } finally {
                }
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        return (String) (Result.m8398isFailureimpl(m8392constructorimpl) ? null : m8392constructorimpl);
    }

    public final String decode(LuminanceSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        Map hints = MapsKt.mapOf(TuplesKt.to(DecodeHintType.POSSIBLE_FORMATS, CollectionsKt.listOf(BarcodeFormat.QR_CODE)), TuplesKt.to(DecodeHintType.TRY_HARDER, true), TuplesKt.to(DecodeHintType.CHARACTER_SET, "UTF-8"));
        Iterator it = CollectionsKt.listOf((Object[]) new GlobalHistogramBinarizer[]{new HybridBinarizer(source), new GlobalHistogramBinarizer(source)}).iterator();
        while (it.hasNext()) {
            GlobalHistogramBinarizer binarizer = (GlobalHistogramBinarizer) it.next();
            try {
                Result.Companion companion = Result.INSTANCE;
                RouteQr routeQr = this;
                return new MultiFormatReader().decode(new BinaryBitmap(binarizer), hints).getText();
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
        }
        return null;
    }

    private final String fileName(String name) {
        String take = StringsKt.take(StringsKt.trim((CharSequence) new Regex("[^\\p{L}\\p{N} _-]").replace(name, "")).toString(), 40);
        if (StringsKt.isBlank(take)) {
            take = "route";
        }
        return "Wayfindr - " + ((Object) take) + ".png";
    }
}
