package com.example.wayfindr.dash.map;

import android.graphics.Bitmap;
import com.example.wayfindr.dash.map.RouteTilePlan;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.data.RideDiagnostics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

/* compiled from: TileProvider.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.map.TileProvider$prefetchRoute$1", f = "TileProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class TileProvider$prefetchRoute$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<GeoPoint> $route;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TileProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TileProvider$prefetchRoute$1(List<GeoPoint> list, TileProvider tileProvider, Continuation<? super TileProvider$prefetchRoute$1> continuation) {
        super(2, continuation);
        this.$route = list;
        this.this$0 = tileProvider;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TileProvider$prefetchRoute$1 tileProvider$prefetchRoute$1 = new TileProvider$prefetchRoute$1(this.$route, this.this$0, continuation);
        tileProvider$prefetchRoute$1.L$0 = obj;
        return tileProvider$prefetchRoute$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TileProvider$prefetchRoute$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        String keyOf;
        Bitmap fetchKey;
        String keyOf2;
        File diskFile;
        TileProvider$prefetchRoute$1 tileProvider$prefetchRoute$1 = this;
        CoroutineScope $this$launch = (CoroutineScope) tileProvider$prefetchRoute$1.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (tileProvider$prefetchRoute$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                List plan = RouteTilePlan.plan$default(RouteTilePlan.INSTANCE, tileProvider$prefetchRoute$1.$route, 0.0d, 2, null);
                TileProvider tileProvider = tileProvider$prefetchRoute$1.this$0;
                Collection arrayList = new ArrayList();
                for (Object obj : plan) {
                    RouteTilePlan.Tile tile = (RouteTilePlan.Tile) obj;
                    keyOf2 = tileProvider.keyOf(tile.getZ(), tile.getX(), tile.getY());
                    diskFile = tileProvider.diskFile(keyOf2);
                    if (!diskFile.exists()) {
                        arrayList.add(obj);
                    }
                }
                List<RouteTilePlan.Tile> missing = (List) arrayList;
                RideDiagnostics.INSTANCE.log("tiles", "route cache: " + plan.size() + " tiles planned, " + missing.size() + " to download");
                int ok = 0;
                int i = 0;
                for (RouteTilePlan.Tile t : missing) {
                    int i2 = i;
                    i++;
                    if (!CoroutineScopeKt.isActive($this$launch)) {
                        return Unit.INSTANCE;
                    }
                    TileProvider tileProvider2 = tileProvider$prefetchRoute$1.this$0;
                    int z = t.getZ();
                    int x = t.getX();
                    int y = t.getY();
                    CoroutineScope $this$launch2 = $this$launch;
                    List plan2 = plan;
                    List missing2 = missing;
                    keyOf = tileProvider$prefetchRoute$1.this$0.keyOf(t.getZ(), t.getX(), t.getY());
                    fetchKey = tileProvider2.fetchKey(z, x, y, keyOf);
                    if (fetchKey != null) {
                        ok++;
                    }
                    if ((i2 + 1) % 500 == 0) {
                        RideDiagnostics.INSTANCE.log("tiles", "route cache: " + (i2 + 1) + "/" + missing2.size());
                    }
                    tileProvider$prefetchRoute$1 = this;
                    $this$launch = $this$launch2;
                    plan = plan2;
                    missing = missing2;
                }
                RideDiagnostics.INSTANCE.log("tiles", "route cache done: " + ok + "/" + missing.size() + " downloaded");
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
