package com.example.wayfindr.dash.map;

import android.util.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TileProvider.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.map.TileProvider$prefetch$1", f = "TileProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class TileProvider$prefetch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Double $fromLat;
    final /* synthetic */ Double $fromLng;
    final /* synthetic */ double $lat;
    final /* synthetic */ double $lng;
    int label;
    final /* synthetic */ TileProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TileProvider$prefetch$1(TileProvider tileProvider, double d, double d2, Double d3, Double d4, Continuation<? super TileProvider$prefetch$1> continuation) {
        super(2, continuation);
        this.this$0 = tileProvider;
        this.$lat = d;
        this.$lng = d2;
        this.$fromLat = d3;
        this.$fromLng = d4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TileProvider$prefetch$1(this.this$0, this.$lat, this.$lng, this.$fromLat, this.$fromLng, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TileProvider$prefetch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        int prefetchAround;
        int prefetchAround2;
        int prefetchAround3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                int count = 0;
                int z = 11;
                while (z < 21) {
                    boolean z2 = false;
                    int radius = 15 <= z && z < 17 ? 2 : 1;
                    prefetchAround = this.this$0.prefetchAround(this.$lat, this.$lng, z, radius);
                    count += prefetchAround;
                    if (this.$fromLat != null && this.$fromLng != null) {
                        prefetchAround2 = this.this$0.prefetchAround(this.$fromLat.doubleValue(), this.$fromLng.doubleValue(), z, radius);
                        count += prefetchAround2;
                        if (12 <= z && z < 14) {
                            z2 = true;
                        }
                        if (z2) {
                            for (int i = 1; i < 9; i++) {
                                double f = i / 9.0d;
                                prefetchAround3 = this.this$0.prefetchAround(this.$fromLat.doubleValue() + ((this.$lat - this.$fromLat.doubleValue()) * f), this.$fromLng.doubleValue() + ((this.$lng - this.$fromLng.doubleValue()) * f), z, 1);
                                count += prefetchAround3;
                            }
                        }
                    }
                    if (count <= 600) {
                        z++;
                    } else {
                        Log.i("TileProvider", "Prefetch (point) done — ~" + count + " tiles ensured");
                        return Unit.INSTANCE;
                    }
                }
                Log.i("TileProvider", "Prefetch (point) done — ~" + count + " tiles ensured");
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
