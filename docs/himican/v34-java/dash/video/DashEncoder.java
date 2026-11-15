package com.example.wayfindr.dash.video;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import com.google.android.gms.common.Scopes;
import java.nio.ByteBuffer;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DashEncoder.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B!\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\r\u001a\u00020\u0006J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018J\u001a\u0010\u0019\u001a\u00020\u00062\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00060\u001bJ\u0006\u0010\u001d\u001a\u00020\u0006J\u000e\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u0006J\u0006\u0010\"\u001a\u00020\u0006R \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/example/wayfindr/dash/video/DashEncoder;", "", "onEncodedData", "Lkotlin/Function2;", "", "", "", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "codec", "Landroid/media/MediaCodec;", "inputSurface", "Landroid/view/Surface;", "prepare", "selectHardwareEncoder", "", "format", "Landroid/media/MediaFormat;", "scalePaint", "Landroid/graphics/Paint;", "dstRect", "Landroid/graphics/Rect;", "renderBitmap", "bmp", "Landroid/graphics/Bitmap;", "renderFrame", "draw", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "drain", "requestBitrate", "bps", "", "requestSyncFrame", "release", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DashEncoder {
    public static final int BITRATE = 200000;
    public static final int BITRATE_HQ = 400000;
    public static final int BITRATE_IDLE = 100000;
    private static final long FIRST_FRAME_TIMEOUT_US = 40000;
    public static final int FPS = 4;
    public static final int HEIGHT = 300;
    private static final String MIME = "video/avc";
    private static final String TAG = "DashEncoder";
    public static final int WIDTH = 526;
    private MediaCodec codec;
    private final Rect dstRect;
    private Surface inputSurface;
    private final Function2<byte[], Boolean, Unit> onEncodedData;
    private final Paint scalePaint;
    public static final int $stable = 8;

    /* JADX WARN: Multi-variable type inference failed */
    public DashEncoder(Function2<? super byte[], ? super Boolean, Unit> onEncodedData) {
        Intrinsics.checkNotNullParameter(onEncodedData, "onEncodedData");
        this.onEncodedData = onEncodedData;
        this.scalePaint = new Paint(2);
        this.dstRect = new Rect(0, 0, WIDTH, 300);
    }

    public final void prepare() {
        Object m8392constructorimpl;
        Object obj;
        MediaCodecInfo.CodecCapabilities capabilitiesForType;
        MediaCodecInfo.EncoderCapabilities encoderCapabilities;
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(MIME, WIDTH, 300);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", BITRATE);
        createVideoFormat.setInteger("frame-rate", 4);
        createVideoFormat.setInteger("i-frame-interval", 1);
        createVideoFormat.setInteger(Scopes.PROFILE, 1);
        createVideoFormat.setInteger("level", 4096);
        Intrinsics.checkNotNullExpressionValue(createVideoFormat, "apply(...)");
        String name = selectHardwareEncoder(createVideoFormat);
        try {
            Result.Companion companion = Result.INSTANCE;
            DashEncoder dashEncoder = this;
            Object[] codecInfos = new MediaCodecList(0).getCodecInfos();
            Intrinsics.checkNotNullExpressionValue(codecInfos, "getCodecInfos(...)");
            Object[] objArr = codecInfos;
            int length = objArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    obj = null;
                    break;
                }
                obj = objArr[i];
                if (Intrinsics.areEqual(((MediaCodecInfo) obj).getName(), name)) {
                    break;
                } else {
                    i++;
                }
            }
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) obj;
            m8392constructorimpl = Result.m8392constructorimpl(Boolean.valueOf((mediaCodecInfo == null || (capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(MIME)) == null || (encoderCapabilities = capabilitiesForType.getEncoderCapabilities()) == null || !encoderCapabilities.isBitrateModeSupported(2)) ? false : true));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = false;
        }
        boolean cbrOk = ((Boolean) m8392constructorimpl).booleanValue();
        if (cbrOk) {
            createVideoFormat.setInteger("bitrate-mode", 2);
        }
        Log.i(TAG, "Bitrate mode: " + (cbrOk ? "CBR" : "encoder default"));
        MediaCodec createByCodecName = name != null ? MediaCodec.createByCodecName(name) : MediaCodec.createEncoderByType(MIME);
        MediaCodec mediaCodec = createByCodecName;
        Log.i(TAG, "Encoder: " + mediaCodec.getName());
        MediaFormat mediaFormat = new MediaFormat(createVideoFormat);
        if (Build.VERSION.SDK_INT >= 30) {
            mediaFormat.setInteger("latency", 1);
        }
        mediaFormat.setInteger("priority", 0);
        mediaFormat.setInteger("vendor.qti-ext-enc-low-latency.enable", 1);
        try {
            mediaCodec.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
            Log.i(TAG, "Configured low-latency");
        } catch (Exception e) {
            Log.w(TAG, "Low-latency configure rejected (" + e.getMessage() + ") — using default");
            mediaCodec.reset();
            createVideoFormat.removeKey("bitrate-mode");
            mediaCodec.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        }
        this.inputSurface = mediaCodec.createInputSurface();
        mediaCodec.start();
        this.codec = createByCodecName;
    }

    private final String selectHardwareEncoder(MediaFormat format) {
        Object m8392constructorimpl;
        boolean z;
        MediaCodecList list = new MediaCodecList(0);
        Iterator it = ArrayIteratorKt.iterator(list.getCodecInfos());
        while (it.hasNext()) {
            MediaCodecInfo info = (MediaCodecInfo) it.next();
            if (info.isEncoder()) {
                Object[] supportedTypes = info.getSupportedTypes();
                Intrinsics.checkNotNullExpressionValue(supportedTypes, "getSupportedTypes(...)");
                Object[] objArr = supportedTypes;
                int length = objArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        z = true;
                        if (StringsKt.equals((String) objArr[i], MIME, true)) {
                            break;
                        }
                        i++;
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z && info.isHardwareAccelerated() && !info.isSoftwareOnly()) {
                    return info.getName();
                }
            }
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            DashEncoder dashEncoder = this;
            m8392constructorimpl = Result.m8392constructorimpl(list.findEncoderForFormat(format));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = null;
        }
        return (String) m8392constructorimpl;
    }

    public final void renderBitmap(final Bitmap bmp) {
        Intrinsics.checkNotNullParameter(bmp, "bmp");
        renderFrame(new Function1() { // from class: com.example.wayfindr.dash.video.DashEncoder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashEncoder.renderBitmap$lambda$7(bmp, this, (Canvas) obj);
            }
        });
    }

    static final Unit renderBitmap$lambda$7(Bitmap $bmp, DashEncoder this$0, Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if ($bmp.getWidth() == 526 && $bmp.getHeight() == 300) {
            canvas.drawBitmap($bmp, 0.0f, 0.0f, (Paint) null);
        } else {
            canvas.drawBitmap($bmp, (Rect) null, this$0.dstRect, this$0.scalePaint);
        }
        return Unit.INSTANCE;
    }

    public final void renderFrame(Function1<? super Canvas, Unit> draw) {
        Intrinsics.checkNotNullParameter(draw, "draw");
        Surface surface = this.inputSurface;
        if (surface == null) {
            return;
        }
        try {
            Canvas canvas = surface.lockHardwareCanvas();
            try {
                Intrinsics.checkNotNull(canvas);
                draw.invoke(canvas);
            } finally {
                surface.unlockCanvasAndPost(canvas);
            }
        } catch (Exception e) {
        }
    }

    public final void drain() {
        MediaCodec codec = this.codec;
        if (codec == null) {
            return;
        }
        MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
        boolean gotFrame = false;
        while (true) {
            int idx = codec.dequeueOutputBuffer(info, gotFrame ? 0L : FIRST_FRAME_TIMEOUT_US);
            if (idx != -1) {
                if (idx != -2 && idx >= 0) {
                    ByteBuffer buf = codec.getOutputBuffer(idx);
                    if (buf == null) {
                        codec.releaseOutputBuffer(idx, false);
                    } else {
                        boolean isKey = (info.flags & 1) != 0;
                        if (info.size > 0) {
                            byte[] data = new byte[info.size];
                            buf.get(data);
                            this.onEncodedData.invoke(data, Boolean.valueOf(isKey));
                        }
                        if ((info.flags & 2) == 0) {
                            gotFrame = true;
                        }
                        codec.releaseOutputBuffer(idx, false);
                        if ((info.flags & 4) != 0) {
                            return;
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    public final void requestBitrate(int bps) {
        Object m8392constructorimpl;
        MediaCodec c = this.codec;
        if (c == null) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            DashEncoder dashEncoder = this;
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", bps);
            c.setParameters(bundle);
            m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl != null) {
            Log.w(TAG, "requestBitrate(" + bps + ") failed: " + m8395exceptionOrNullimpl.getMessage());
        }
    }

    public final void requestSyncFrame() {
        Object m8392constructorimpl;
        MediaCodec c = this.codec;
        if (c == null) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            DashEncoder dashEncoder = this;
            Bundle bundle = new Bundle();
            bundle.putInt("request-sync", 0);
            c.setParameters(bundle);
            m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl != null) {
            Log.w(TAG, "requestSyncFrame failed: " + m8395exceptionOrNullimpl.getMessage());
        }
    }

    public final void release() {
        Unit unit;
        Unit unit2;
        try {
            Result.Companion companion = Result.INSTANCE;
            MediaCodec mediaCodec = this.codec;
            if (mediaCodec != null) {
                mediaCodec.stop();
                unit2 = Unit.INSTANCE;
            } else {
                unit2 = null;
            }
            Result.m8392constructorimpl(unit2);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            MediaCodec mediaCodec2 = this.codec;
            if (mediaCodec2 != null) {
                mediaCodec2.release();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m8392constructorimpl(unit);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th2));
        }
        this.codec = null;
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
        }
        this.inputSurface = null;
    }
}
