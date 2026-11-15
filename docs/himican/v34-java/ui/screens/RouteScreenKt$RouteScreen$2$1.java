package com.example.wayfindr.ui.screens;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.viewmodel.RouteState;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.maplibre.android.log.Logger;

/* compiled from: RouteScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.screens.RouteScreenKt$RouteScreen$2$1", f = "RouteScreen.kt", i = {0, 1}, l = {Logger.NONE, 101}, m = "invokeSuspend", n = {SearchIntents.EXTRA_QUERY, SearchIntents.EXTRA_QUERY}, s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
final class RouteScreenKt$RouteScreen$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ State<RouteState> $routeState$delegate;
    final /* synthetic */ MutableState<String> $searchQuery$delegate;
    final /* synthetic */ MutableState<List<PlaceSearch.Hit>> $suggestions$delegate;
    final /* synthetic */ MutableState<Boolean> $suggestionsLoading$delegate;
    final /* synthetic */ MutableState<String> $suggestionsStatus$delegate;
    final /* synthetic */ MutableState<Boolean> $suggestionsSuppressed$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteScreenKt$RouteScreen$2$1(Context context, MutableState<String> mutableState, MutableState<List<PlaceSearch.Hit>> mutableState2, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5, State<RouteState> state, Continuation<? super RouteScreenKt$RouteScreen$2$1> continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$searchQuery$delegate = mutableState;
        this.$suggestions$delegate = mutableState2;
        this.$suggestionsLoading$delegate = mutableState3;
        this.$suggestionsStatus$delegate = mutableState4;
        this.$suggestionsSuppressed$delegate = mutableState5;
        this.$routeState$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteScreenKt$RouteScreen$2$1(this.$ctx, this.$searchQuery$delegate, this.$suggestions$delegate, this.$suggestionsLoading$delegate, this.$suggestionsStatus$delegate, this.$suggestionsSuppressed$delegate, this.$routeState$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RouteScreenKt$RouteScreen$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0099  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        String RouteScreen$lambda$19;
        String query;
        boolean RouteScreen$lambda$34;
        RouteState RouteScreen$lambda$0;
        Object suggestions;
        String query2;
        boolean RouteScreen$lambda$342;
        String RouteScreen$lambda$192;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                RouteScreen$lambda$19 = RouteScreenKt.RouteScreen$lambda$19(this.$searchQuery$delegate);
                query = StringsKt.trim((CharSequence) RouteScreen$lambda$19).toString();
                this.$suggestions$delegate.setValue(CollectionsKt.emptyList());
                RouteScreenKt.RouteScreen$lambda$29(this.$suggestionsLoading$delegate, false);
                this.$suggestionsStatus$delegate.setValue(null);
                RouteScreen$lambda$34 = RouteScreenKt.RouteScreen$lambda$34(this.$suggestionsSuppressed$delegate);
                if (RouteScreen$lambda$34 || query.length() < 2) {
                    return Unit.INSTANCE;
                }
                this.L$0 = query;
                this.label = 1;
                if (DelayKt.delay(350L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                RouteScreenKt.RouteScreen$lambda$29(this.$suggestionsLoading$delegate, true);
                PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                Context context = this.$ctx;
                RouteScreen$lambda$0 = RouteScreenKt.RouteScreen$lambda$0(this.$routeState$delegate);
                this.L$0 = query;
                this.label = 2;
                suggestions = placeSearch.suggestions(context, query, RouteScreen$lambda$0.getCurrentLocation(), this);
                if (suggestions != coroutine_suspended) {
                    return coroutine_suspended;
                }
                query2 = query;
                List found = (List) suggestions;
                RouteScreen$lambda$342 = RouteScreenKt.RouteScreen$lambda$34(this.$suggestionsSuppressed$delegate);
                if (!RouteScreen$lambda$342) {
                    RouteScreen$lambda$192 = RouteScreenKt.RouteScreen$lambda$19(this.$searchQuery$delegate);
                    if (Intrinsics.areEqual(StringsKt.trim((CharSequence) RouteScreen$lambda$192).toString(), query2)) {
                        this.$suggestions$delegate.setValue(found);
                        if (found.isEmpty()) {
                            MutableState<String> mutableState = this.$suggestionsStatus$delegate;
                            if (Intrinsics.areEqual(PlaceSearch.INSTANCE.getLastError(), "no match")) {
                                str = "Eşleşme bulunamadı.";
                            } else {
                                String lastError = PlaceSearch.INSTANCE.getLastError();
                                if (lastError == null) {
                                    lastError = "";
                                }
                                str = StringsKt.contains((CharSequence) lastError, (CharSequence) "no internet", true) ? "İnternet bağlantısı yok; yer önerileri alınamadı." : "Yer arama hizmetine ulaşılamadı. Lütfen daha sonra tekrar deneyin.";
                            }
                            mutableState.setValue(str);
                        }
                    }
                }
                RouteScreenKt.RouteScreen$lambda$29(this.$suggestionsLoading$delegate, false);
                return Unit.INSTANCE;
            case 1:
                query = (String) this.L$0;
                ResultKt.throwOnFailure($result);
                RouteScreenKt.RouteScreen$lambda$29(this.$suggestionsLoading$delegate, true);
                PlaceSearch placeSearch2 = PlaceSearch.INSTANCE;
                Context context2 = this.$ctx;
                RouteScreen$lambda$0 = RouteScreenKt.RouteScreen$lambda$0(this.$routeState$delegate);
                this.L$0 = query;
                this.label = 2;
                suggestions = placeSearch2.suggestions(context2, query, RouteScreen$lambda$0.getCurrentLocation(), this);
                if (suggestions != coroutine_suspended) {
                }
                break;
            case 2:
                query2 = (String) this.L$0;
                ResultKt.throwOnFailure($result);
                suggestions = $result;
                List found2 = (List) suggestions;
                RouteScreen$lambda$342 = RouteScreenKt.RouteScreen$lambda$34(this.$suggestionsSuppressed$delegate);
                if (!RouteScreen$lambda$342) {
                }
                RouteScreenKt.RouteScreen$lambda$29(this.$suggestionsLoading$delegate, false);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
