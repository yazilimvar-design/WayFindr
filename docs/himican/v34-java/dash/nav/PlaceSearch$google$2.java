package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.data.ApiUsage;
import java.util.List;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PlaceSearch.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
final class PlaceSearch$google$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ String $q;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$google$2(Context context, String str, Continuation continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$q = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        Continuation placeSearch$google$2 = new PlaceSearch$google$2(this.$ctx, this.$q, continuation);
        placeSearch$google$2.L$0 = obj;
        return placeSearch$google$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Address address;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Object enterHook = GPlus.enterHook(this.$ctx, this.$q, null);
                if (enterHook != null) {
                    if (enterHook instanceof PlaceSearch.Hit) {
                        return enterHook;
                    }
                    return null;
                }
                try {
                    if (!Geocoder.isPresent()) {
                        PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                        PlaceSearch.access$setLastError$p("no geocoder on this phone");
                        return null;
                    }
                    ApiUsage.INSTANCE.record("Android Geocoder (search)");
                    List<Address> fromLocationName = new Geocoder(this.$ctx, Locale.getDefault()).getFromLocationName(this.$q, 1);
                    if (fromLocationName != null && (address = (Address) CollectionsKt.firstOrNull(fromLocationName)) != null) {
                        return new PlaceSearch.Hit(this.$q, address.getLatitude(), address.getLongitude(), "Google", (String) null, 16, (DefaultConstructorMarker) null);
                    }
                    PlaceSearch placeSearch2 = PlaceSearch.INSTANCE;
                    PlaceSearch.access$setLastError$p("no match");
                    return null;
                } catch (Exception e) {
                    PlaceSearch placeSearch3 = PlaceSearch.INSTANCE;
                    PlaceSearch.access$setLastError$p("Google search failed (" + e.getMessage() + ")");
                    return null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
