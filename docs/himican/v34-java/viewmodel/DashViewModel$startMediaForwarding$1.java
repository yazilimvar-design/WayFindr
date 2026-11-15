package com.example.wayfindr.viewmodel;

import com.example.wayfindr.dash.DashSession;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.media.CallInfoProvider;
import com.example.wayfindr.media.IncomingCall;
import com.example.wayfindr.media.MediaInfoProvider;
import com.example.wayfindr.media.NowPlaying;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$startMediaForwarding$1", f = "DashViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class DashViewModel$startMediaForwarding$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$startMediaForwarding$1(DashViewModel dashViewModel, Continuation<? super DashViewModel$startMediaForwarding$1> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashViewModel$startMediaForwarding$1 dashViewModel$startMediaForwarding$1 = new DashViewModel$startMediaForwarding$1(this.this$0, continuation);
        dashViewModel$startMediaForwarding$1.L$0 = obj;
        return dashViewModel$startMediaForwarding$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashViewModel$startMediaForwarding$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$startMediaForwarding$1$1", f = "DashViewModel.kt", i = {}, l = {2192}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$startMediaForwarding$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ DashViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DashViewModel dashViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = dashViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            MediaInfoProvider mediaInfoProvider;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    mediaInfoProvider = this.this$0.mediaInfo;
                    StateFlow<NowPlaying> nowPlaying = mediaInfoProvider.getNowPlaying();
                    final DashViewModel dashViewModel = this.this$0;
                    this.label = 1;
                    if (nowPlaying.collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.DashViewModel.startMediaForwarding.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((NowPlaying) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(NowPlaying np, Continuation<? super Unit> continuation) {
                            String str;
                            RideDiagnostics rideDiagnostics = RideDiagnostics.INSTANCE;
                            if (np == null || (str = np.getTitle()) == null) {
                                str = "(none)";
                            }
                            rideDiagnostics.log("media", "now-playing observed: " + str);
                            DashSession dashSession = DashViewModel.this.session;
                            String title = np != null ? np.getTitle() : null;
                            String album = np != null ? np.getAlbum() : null;
                            if (album == null) {
                                album = "";
                            }
                            String artist = np != null ? np.getArtist() : null;
                            dashSession.updateNowPlaying(title, album, artist != null ? artist : "", np != null ? np.getPlaying() : false);
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CoroutineScope $this$launch = (CoroutineScope) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                BuildersKt__Builders_commonKt.launch$default($this$launch, null, null, new AnonymousClass1(this.this$0, null), 3, null);
                BuildersKt__Builders_commonKt.launch$default($this$launch, null, null, new AnonymousClass2(this.this$0, null), 3, null);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$startMediaForwarding$1$2", f = "DashViewModel.kt", i = {}, l = {2198}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$startMediaForwarding$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ DashViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DashViewModel dashViewModel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = dashViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<IncomingCall> incomingCall = CallInfoProvider.INSTANCE.getIncomingCall();
                    final DashViewModel dashViewModel = this.this$0;
                    this.label = 1;
                    if (incomingCall.collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.DashViewModel.startMediaForwarding.1.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((IncomingCall) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(IncomingCall call, Continuation<? super Unit> continuation) {
                            String str;
                            RideDiagnostics rideDiagnostics = RideDiagnostics.INSTANCE;
                            if (call == null || (str = call.getCaller()) == null) {
                                str = "(none)";
                            }
                            rideDiagnostics.log("media", "call observed: " + str);
                            DashViewModel.this.session.updateCall(call != null ? call.getCaller() : null);
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }
}
