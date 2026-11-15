package com.example.wayfindr.viewmodel;

import android.graphics.Bitmap;
import android.location.Location;
import android.util.Log;
import com.example.wayfindr.dash.DashState;
import com.example.wayfindr.dash.map.TileProvider;
import com.example.wayfindr.dash.video.DashEncoder;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.channels.Channel;

/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$startStream$3", f = "DashViewModel.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {1384}, m = "invokeSuspend", n = {"$this$launch", "bitrateEncoder", "lastPrefetch", "failures", "lastChangeMs", "idleBitrate", "liveBitrate", "frameMs", "nextFrameMs", "lastSendMs", "sleepMs"}, s = {"L$0", "L$1", "J$0", "I$0", "J$1", "I$1", "I$2", "J$2", "J$3", "J$4", "J$5"})
/* loaded from: classes4.dex */
final class DashViewModel$startStream$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<byte[], Boolean, Unit> $onEncoded;
    int I$0;
    int I$1;
    int I$2;
    long J$0;
    long J$1;
    long J$2;
    long J$3;
    long J$4;
    long J$5;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DashViewModel$startStream$3(DashViewModel dashViewModel, Function2<? super byte[], ? super Boolean, Unit> function2, Continuation<? super DashViewModel$startStream$3> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
        this.$onEncoded = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashViewModel$startStream$3 dashViewModel$startStream$3 = new DashViewModel$startStream$3(this.this$0, this.$onEncoded, continuation);
        dashViewModel$startStream$3.L$0 = obj;
        return dashViewModel$startStream$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashViewModel$startStream$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:26|(1:27)|28|(1:30)(1:125)|(2:35|36)|119|120|121|122|123|124|36) */
    /* JADX WARN: Can't wrap try/catch for region: R(13:79|80|81|(1:83)(1:100)|84|85|86|87|88|(1:90)(1:96)|91|(1:93)(1:95)|94) */
    /* JADX WARN: Can't wrap try/catch for region: R(20:14|15|16|17|18|(1:20)|21|22|23|(1:132)(12:26|27|28|(1:30)(1:125)|(2:35|36)|119|120|121|122|123|124|36)|37|38|39|(1:41)(1:106)|42|(1:44)|45|(5:47|48|49|(1:51)|52)(1:105)|53|(3:64|65|(1:67)(4:68|(1:8)(1:155)|9|(1:10)))(3:55|(3:61|62|63)(3:57|58|59)|60)) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x028d, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0292, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0293, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        kotlin.Result.m8392constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0222, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:?, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x021c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x021d, code lost:
    
        r4 = r24;
        r8 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0141, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:?, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0138, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0139, code lost:
    
        r4 = r24;
        r7 = r26;
        r8 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0234, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:?, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x022c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x027b, code lost:
    
        r0 = r1.this$0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x027d, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        r0 = r0.encoder;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0285, code lost:
    
        if (r0 != null) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0287, code lost:
    
        r0.release();
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x028e, code lost:
    
        kotlin.Result.m8392constructorimpl(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x029c, code lost:
    
        r2 = r1.this$0;
        r0 = r1.$onEncoded;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x02a0, code lost:
    
        r5 = kotlin.Result.INSTANCE;
        r5 = new com.example.wayfindr.dash.video.DashEncoder(r0);
        r5.prepare();
        r0 = kotlin.Result.m8392constructorimpl(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x02c2, code lost:
    
        r5 = kotlin.Result.m8395exceptionOrNullimpl(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x02c6, code lost:
    
        if (r5 != null) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02c9, code lost:
    
        r24 = r0;
        android.util.Log.e("DashViewModel", "Encoder rebuild failed", r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x02d9, code lost:
    
        if (kotlin.Result.m8398isFailureimpl(r24) != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x02db, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x02df, code lost:
    
        r2.encoder = (com.example.wayfindr.dash.video.DashEncoder) r0;
        r1.this$0.lastSignature = "";
        r7 = 0;
        r42 = r17;
        r4 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x02dd, code lost:
    
        r0 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x02d3, code lost:
    
        r24 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x02b7, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x02b8, code lost:
    
        r5 = kotlin.Result.INSTANCE;
        r0 = kotlin.Result.m8392constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0308 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0359  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0343 -> B:7:0x0357). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        int failures;
        CoroutineScope $this$launch;
        int liveBitrate;
        int i;
        Object obj;
        long lastChangeMs;
        DashEncoder bitrateEncoder;
        long lastChangeMs2;
        Object $result2;
        DashViewModel$startStream$3 dashViewModel$startStream$3;
        long lastSendMs;
        long nextFrameMs;
        Object withTimeoutOrNull;
        CoroutineScope $this$launch2;
        long nextFrameMs2;
        long lastPrefetch;
        long lastPrefetch2;
        boolean changed;
        Bitmap bmp;
        DashEncoder enc;
        long lastPrefetch3;
        long lastChangeMs3;
        double d;
        long sendNow;
        double d2;
        double d3;
        double d4;
        long j;
        TileProvider tileProvider;
        int failures2;
        long j2;
        CoroutineScope $this$launch3 = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                long lastChangeMs4 = System.currentTimeMillis();
                long nextFrameMs3 = System.currentTimeMillis();
                failures = 0;
                $this$launch = $this$launch3;
                long nextFrameMs4 = nextFrameMs3;
                liveBitrate = 200000;
                i = 0;
                obj = coroutine_suspended;
                long lastSendMs2 = System.currentTimeMillis();
                lastChangeMs = 250;
                bitrateEncoder = null;
                lastChangeMs2 = lastChangeMs4;
                long lastPrefetch4 = 0;
                $result2 = $result;
                dashViewModel$startStream$3 = this;
                while (CoroutineScopeKt.isActive($this$launch)) {
                    Object $result3 = $result2;
                    if (dashViewModel$startStream$3.this$0.session.getState().getValue() != DashState.STREAMING) {
                        return Unit.INSTANCE;
                    }
                    try {
                        try {
                            try {
                            } catch (Exception e) {
                                e = e;
                            }
                            changed = dashViewModel$startStream$3.this$0.tick();
                            long nowMs = System.currentTimeMillis();
                            if (changed) {
                                lastChangeMs2 = nowMs;
                            }
                            lastChangeMs = 250;
                            bmp = dashViewModel$startStream$3.this$0.frameBitmap;
                            enc = dashViewModel$startStream$3.this$0.encoder;
                            if (bmp == null || enc == null) {
                                lastPrefetch3 = lastPrefetch4;
                                lastChangeMs3 = lastChangeMs2;
                            } else {
                                lastPrefetch3 = lastPrefetch4;
                                try {
                                } catch (CancellationException e2) {
                                    throw e2;
                                } catch (Exception e3) {
                                    e = e3;
                                    lastPrefetch4 = lastPrefetch3;
                                }
                                int wantBitrate = dashViewModel$startStream$3.this$0.dashConfig.getHighQualityVideo().getValue().booleanValue() ? DashEncoder.BITRATE_HQ : DashEncoder.BITRATE;
                                if (i == 0 && enc == bitrateEncoder && wantBitrate == liveBitrate) {
                                    failures2 = failures;
                                    lastChangeMs3 = lastChangeMs2;
                                    enc.renderBitmap(bmp);
                                    DashViewModel dashViewModel = dashViewModel$startStream$3.this$0;
                                    j2 = dashViewModel$startStream$3.this$0.videoPtsMs;
                                    dashViewModel.videoPtsMs = j2 + 250;
                                    enc.drain();
                                }
                                enc.requestBitrate(wantBitrate);
                                liveBitrate = wantBitrate;
                                bitrateEncoder = enc;
                                i = 0;
                                failures2 = failures;
                                lastChangeMs3 = lastChangeMs2;
                                RideDiagnostics.INSTANCE.log("stream", "video bitrate → " + (wantBitrate / 1000) + " kbps");
                                enc.renderBitmap(bmp);
                                DashViewModel dashViewModel2 = dashViewModel$startStream$3.this$0;
                                j2 = dashViewModel$startStream$3.this$0.videoPtsMs;
                                dashViewModel2.videoPtsMs = j2 + 250;
                                enc.drain();
                            }
                            failures = 0;
                            long sendNow2 = System.currentTimeMillis();
                            long gap = sendNow2 - lastSendMs2;
                            lastSendMs2 = sendNow2;
                            DashViewModel dashViewModel3 = dashViewModel$startStream$3.this$0;
                            d = dashViewModel$startStream$3.this$0.sendPaceMs;
                            if (d <= 0.0d) {
                                sendNow = sendNow2;
                                d4 = gap;
                            } else {
                                sendNow = sendNow2;
                                d2 = dashViewModel$startStream$3.this$0.sendPaceMs;
                                d3 = dashViewModel$startStream$3.this$0.sendPaceMs;
                                d4 = d2 + ((gap - d3) * 0.1d);
                            }
                            dashViewModel3.sendPaceMs = d4;
                            j = dashViewModel$startStream$3.this$0.sendPaceMaxMs;
                            if (gap > j) {
                                dashViewModel$startStream$3.this$0.sendPaceMaxMs = gap;
                            }
                            if (sendNow - lastPrefetch3 > 20000) {
                                lastPrefetch4 = sendNow;
                                try {
                                } catch (CancellationException e4) {
                                    throw e4;
                                } catch (Exception e5) {
                                    e = e5;
                                    lastChangeMs2 = lastChangeMs3;
                                    failures++;
                                    long lastPrefetch5 = lastPrefetch4;
                                    Log.e("DashViewModel", "Frame loop error #" + failures, e);
                                    if (failures >= 3) {
                                        break;
                                    }
                                    lastSendMs = lastSendMs2;
                                    lastPrefetch4 = lastPrefetch5;
                                    long lastSendMs3 = lastChangeMs;
                                    long frameMs = nextFrameMs4 + lastSendMs3;
                                    long nextFrameMs5 = System.currentTimeMillis();
                                    nextFrameMs = frameMs - nextFrameMs5;
                                    if (nextFrameMs > 1) {
                                    }
                                }
                                Location value = dashViewModel$startStream$3.this$0.location.getLocation().getValue();
                                if (value != null) {
                                    tileProvider = dashViewModel$startStream$3.this$0.tiles;
                                    TileProvider.prefetch$default(tileProvider, value.getLatitude(), value.getLongitude(), null, null, 12, null);
                                }
                                lastSendMs = lastSendMs2;
                                lastChangeMs2 = lastChangeMs3;
                            } else {
                                lastSendMs = lastSendMs2;
                                lastPrefetch4 = lastPrefetch3;
                                lastChangeMs2 = lastChangeMs3;
                            }
                            long lastSendMs32 = lastChangeMs;
                            long frameMs2 = nextFrameMs4 + lastSendMs32;
                            long nextFrameMs52 = System.currentTimeMillis();
                            nextFrameMs = frameMs2 - nextFrameMs52;
                            if (nextFrameMs > 1) {
                                dashViewModel$startStream$3.L$0 = $this$launch;
                                dashViewModel$startStream$3.L$1 = bitrateEncoder;
                                dashViewModel$startStream$3.J$0 = lastPrefetch4;
                                dashViewModel$startStream$3.I$0 = failures;
                                dashViewModel$startStream$3.J$1 = lastChangeMs2;
                                dashViewModel$startStream$3.I$1 = i;
                                dashViewModel$startStream$3.I$2 = liveBitrate;
                                CoroutineScope $this$launch4 = $this$launch;
                                long lastPrefetch6 = lastPrefetch4;
                                dashViewModel$startStream$3.J$2 = lastSendMs32;
                                int failures3 = failures;
                                long lastChangeMs5 = lastChangeMs2;
                                dashViewModel$startStream$3.J$3 = frameMs2;
                                long nextFrameMs6 = lastSendMs;
                                dashViewModel$startStream$3.J$4 = nextFrameMs6;
                                dashViewModel$startStream$3.J$5 = nextFrameMs;
                                dashViewModel$startStream$3.label = 1;
                                withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(nextFrameMs, new AnonymousClass5(dashViewModel$startStream$3.this$0, null), dashViewModel$startStream$3);
                                if (withTimeoutOrNull == obj) {
                                    return obj;
                                }
                                lastChangeMs = lastSendMs32;
                                $result2 = $result3;
                                $this$launch2 = $this$launch4;
                                nextFrameMs2 = frameMs2;
                                failures = failures3;
                                lastChangeMs2 = lastChangeMs5;
                                lastPrefetch = lastPrefetch6;
                                lastPrefetch2 = nextFrameMs6;
                                if (withTimeoutOrNull == null) {
                                    long nextFrameMs7 = System.currentTimeMillis();
                                    $this$launch = $this$launch2;
                                    lastPrefetch4 = lastPrefetch;
                                    nextFrameMs4 = nextFrameMs7;
                                    lastSendMs2 = lastPrefetch2;
                                } else {
                                    $this$launch = $this$launch2;
                                    lastPrefetch4 = lastPrefetch;
                                    nextFrameMs4 = nextFrameMs2;
                                    lastSendMs2 = lastPrefetch2;
                                }
                                while (CoroutineScopeKt.isActive($this$launch)) {
                                }
                            } else {
                                CoroutineScope $this$launch5 = $this$launch;
                                long lastPrefetch7 = lastPrefetch4;
                                int failures4 = failures;
                                long frameMs3 = lastChangeMs2;
                                long lastSendMs4 = lastSendMs;
                                DashViewModel$startStream$3 dashViewModel$startStream$32 = dashViewModel$startStream$3;
                                if (nextFrameMs < (-lastSendMs32)) {
                                    nextFrameMs4 = System.currentTimeMillis();
                                    lastSendMs2 = lastSendMs4;
                                    lastChangeMs2 = frameMs3;
                                    dashViewModel$startStream$3 = dashViewModel$startStream$32;
                                    lastChangeMs = lastSendMs32;
                                    failures = failures4;
                                    $this$launch = $this$launch5;
                                    lastPrefetch4 = lastPrefetch7;
                                    $result2 = $result3;
                                } else {
                                    lastSendMs2 = lastSendMs4;
                                    lastChangeMs2 = frameMs3;
                                    dashViewModel$startStream$3 = dashViewModel$startStream$32;
                                    lastChangeMs = lastSendMs32;
                                    failures = failures4;
                                    nextFrameMs4 = frameMs2;
                                    $this$launch = $this$launch5;
                                    lastPrefetch4 = lastPrefetch7;
                                    $result2 = $result3;
                                }
                            }
                        } catch (CancellationException e6) {
                            throw e6;
                        }
                    } catch (CancellationException e7) {
                        throw e7;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                long j3 = this.J$5;
                long lastSendMs5 = this.J$4;
                long nextFrameMs8 = this.J$3;
                long frameMs4 = this.J$2;
                liveBitrate = this.I$2;
                int i2 = this.I$1;
                long lastChangeMs6 = this.J$1;
                int failures5 = this.I$0;
                $this$launch2 = $this$launch3;
                lastPrefetch = this.J$0;
                DashEncoder bitrateEncoder2 = (DashEncoder) this.L$1;
                ResultKt.throwOnFailure($result);
                $result2 = $result;
                lastPrefetch2 = lastSendMs5;
                nextFrameMs2 = nextFrameMs8;
                failures = failures5;
                obj = coroutine_suspended;
                withTimeoutOrNull = $result2;
                dashViewModel$startStream$3 = this;
                bitrateEncoder = bitrateEncoder2;
                i = i2;
                lastChangeMs2 = lastChangeMs6;
                lastChangeMs = frameMs4;
                if (withTimeoutOrNull == null) {
                }
                while (CoroutineScopeKt.isActive($this$launch)) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$startStream$3$5", f = "DashViewModel.kt", i = {}, l = {1384}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$startStream$3$5, reason: invalid class name */
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ DashViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(DashViewModel dashViewModel, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.this$0 = dashViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass5(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Channel channel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    channel = this.this$0.frameWake;
                    this.label = 1;
                    if (channel.receive(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }
}
