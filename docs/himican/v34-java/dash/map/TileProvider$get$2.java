package com.example.wayfindr.dash.map;

import android.graphics.Bitmap;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TileProvider.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.map.TileProvider$get$2", f = "TileProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class TileProvider$get$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $key;
    final /* synthetic */ int $xw;
    final /* synthetic */ int $y;
    final /* synthetic */ int $z;
    int label;
    final /* synthetic */ TileProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TileProvider$get$2(TileProvider tileProvider, String str, int i, int i2, int i3, Continuation<? super TileProvider$get$2> continuation) {
        super(2, continuation);
        this.this$0 = tileProvider;
        this.$key = str;
        this.$z = i;
        this.$xw = i2;
        this.$y = i3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TileProvider$get$2(this.this$0, this.$key, this.$z, this.$xw, this.$y, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TileProvider$get$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0060, code lost:
    
        r1 = r6.this$0.fetch(r6.$z, r6.$xw, r6.$y, r6.$key);
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        ConcurrentHashMap.KeySetView keySetView;
        Bitmap raw;
        ConcurrentHashMap.KeySetView keySetView2;
        File diskFile;
        Bitmap fetch;
        TileProvider$memory$1 tileProvider$memory$1;
        TileProvider$memory$1 tileProvider$memory$12;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    raw = this.this$0.loadDisk(this.$key);
                    if (raw == null) {
                        raw = this.this$0.fetch(this.$z, this.$xw, this.$y, this.$key);
                    }
                    if (raw != null) {
                        tileProvider$memory$12 = this.this$0.memory;
                        tileProvider$memory$12.put(this.$key, raw);
                    }
                    if (raw != null && StringsKt.startsWith$default(this.$key, "osm/", false, 2, (Object) null)) {
                        long currentTimeMillis = System.currentTimeMillis();
                        diskFile = this.this$0.diskFile(this.$key);
                        if (currentTimeMillis - diskFile.lastModified() > 604800000 && fetch != null) {
                            TileProvider tileProvider = this.this$0;
                            String str = this.$key;
                            tileProvider$memory$1 = tileProvider.memory;
                            tileProvider$memory$1.put(str, fetch);
                        }
                    }
                    keySetView2 = this.this$0.inflight;
                    keySetView2.remove(this.$key);
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                    keySetView = this.this$0.inflight;
                    keySetView.remove(this.$key);
                    throw th;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
