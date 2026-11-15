package com.example.wayfindr.dash;

import com.example.wayfindr.dash.protocol.DashCommands;
import com.example.wayfindr.data.RideDiagnostics;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: DashSession.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.DashSession$launchMediaInfo$1", f = "DashSession.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {578}, m = "invokeSuspend", n = {"$this$launch", "prevCaller", "prevTitle", "prevPlaying", "loggedNp", "c", "title", "loggedCall", "playing"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "Z$0"})
/* loaded from: classes11.dex */
final class DashSession$launchMediaInfo$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    boolean Z$0;
    int label;
    final /* synthetic */ DashSession this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashSession$launchMediaInfo$1(DashSession dashSession, Continuation<? super DashSession$launchMediaInfo$1> continuation) {
        super(2, continuation);
        this.this$0 = dashSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashSession$launchMediaInfo$1 dashSession$launchMediaInfo$1 = new DashSession$launchMediaInfo$1(this.this$0, continuation);
        dashSession$launchMediaInfo$1.L$0 = obj;
        return dashSession$launchMediaInfo$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashSession$launchMediaInfo$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:79|80|81|(1:83)(1:104)|84|85|86|87|(4:89|(1:91)(1:99)|92|93)(1:100)|94|95|42|(7:44|45|46|(9:48|49|50|51|52|53|54|55|56)(1:74)|57|58|(1:60))(1:78)|61|(1:63)(4:64|7|8|(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(4:89|(1:91)(1:99)|92|93) */
    /* JADX WARN: Can't wrap try/catch for region: R(9:48|(1:49)|50|51|52|53|54|55|56) */
    /* JADX WARN: Can't wrap try/catch for region: R(9:48|49|50|51|52|53|54|55|56) */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x015f, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0160, code lost:
    
        r18 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0206, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x020f, code lost:
    
        r10 = kotlin.Result.INSTANCE;
        kotlin.Result.m8392constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01f1, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01f2, code lost:
    
        r21 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x015d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0162, code lost:
    
        r2 = kotlin.Result.INSTANCE;
        kotlin.Result.m8392constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0266 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0239  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x0267 -> B:7:0x0273). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Ref.BooleanRef loggedNp;
        int i;
        String c;
        Boolean prevPlaying;
        Object obj;
        String title;
        Object $result2;
        CoroutineScope $this$launch;
        DashSession$launchMediaInfo$1 dashSession$launchMediaInfo$1;
        MutableStateFlow mutableStateFlow;
        String c2;
        DashSocket dashSocket;
        Unit unit;
        String title2;
        boolean playing;
        Object $result3;
        String c3;
        String title3;
        CoroutineScope $this$launch2;
        String prevCaller;
        int i2;
        String prevTitle;
        DashSocket dashSocket2;
        Unit unit2;
        String str;
        String title4;
        DashSocket dashSocket3;
        Unit unit3;
        DashSocket dashSocket4;
        Unit unit4;
        boolean z;
        DashSocket dashSocket5;
        Unit unit5;
        DashSocket dashSocket6;
        Unit unit6;
        CoroutineScope $this$launch3 = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Ref.BooleanRef loggedNp2 = new Ref.BooleanRef();
                loggedNp = loggedNp2;
                i = 0;
                c = null;
                prevPlaying = null;
                obj = coroutine_suspended;
                title = null;
                $result2 = $result;
                $this$launch = $this$launch3;
                dashSession$launchMediaInfo$1 = this;
                if (CoroutineScopeKt.isActive($this$launch)) {
                    mutableStateFlow = dashSession$launchMediaInfo$1.this$0._state;
                    if (mutableStateFlow.getValue() == DashState.STREAMING) {
                        c2 = dashSession$launchMediaInfo$1.this$0.caller;
                        if (c2 != null) {
                            DashSession dashSession = dashSession$launchMediaInfo$1.this$0;
                            try {
                            } catch (Throwable th) {
                                Result.Companion companion = Result.INSTANCE;
                                Result.m8392constructorimpl(ResultKt.createFailure(th));
                            }
                            Result.Companion companion2 = Result.INSTANCE;
                            dashSocket6 = dashSession.socket;
                            if (dashSocket6 != null) {
                                dashSocket6.send(DashCommands.INSTANCE.callNotify(c2));
                                unit6 = Unit.INSTANCE;
                            } else {
                                unit6 = null;
                            }
                            Result.m8392constructorimpl(unit6);
                            if (i == 0) {
                                RideDiagnostics.INSTANCE.log("media", "TX call card 05 22: " + c2);
                                i = 1;
                            }
                        } else if (title != null) {
                            DashSession dashSession2 = dashSession$launchMediaInfo$1.this$0;
                            try {
                            } catch (Throwable th2) {
                                Result.Companion companion3 = Result.INSTANCE;
                                Result.m8392constructorimpl(ResultKt.createFailure(th2));
                            }
                            Result.Companion companion4 = Result.INSTANCE;
                            dashSocket = dashSession2.socket;
                            if (dashSocket != null) {
                                dashSocket.send(DashCommands.INSTANCE.callClear());
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            Result.m8392constructorimpl(unit);
                            i = 0;
                        }
                        title2 = dashSession$launchMediaInfo$1.this$0.npTitle;
                        playing = dashSession$launchMediaInfo$1.this$0.npPlaying;
                        if (title2 == null) {
                            $result3 = $result2;
                        } else if (Intrinsics.areEqual(title2, c) && Intrinsics.areEqual(Boxing.boxBoolean(playing), prevPlaying)) {
                            $result3 = $result2;
                        } else {
                            DashSession dashSession3 = dashSession$launchMediaInfo$1.this$0;
                            try {
                            } catch (Throwable th3) {
                                Result.Companion companion5 = Result.INSTANCE;
                                Result.m8392constructorimpl(ResultKt.createFailure(th3));
                            }
                            Result.Companion companion6 = Result.INSTANCE;
                            dashSocket5 = dashSession3.socket;
                            if (dashSocket5 != null) {
                                dashSocket5.send(DashCommands.INSTANCE.musicActive(true));
                                unit5 = Unit.INSTANCE;
                            } else {
                                unit5 = null;
                            }
                            Result.m8392constructorimpl(unit5);
                            DashSession dashSession4 = dashSession$launchMediaInfo$1.this$0;
                            Result.Companion companion7 = Result.INSTANCE;
                            dashSocket4 = dashSession4.socket;
                            if (dashSocket4 != null) {
                                DashCommands dashCommands = DashCommands.INSTANCE;
                                if (playing) {
                                    $result3 = $result2;
                                    z = true;
                                } else {
                                    $result3 = $result2;
                                    z = false;
                                }
                                dashSocket4.send(dashCommands.musicPlaying(z));
                                unit4 = Unit.INSTANCE;
                            } else {
                                $result3 = $result2;
                                unit4 = null;
                            }
                            Result.m8392constructorimpl(unit4);
                            RideDiagnostics.INSTANCE.log("media", "TX music on 05 17 + playing=" + playing + " 05 19");
                            Boolean prevPlaying2 = Boxing.boxBoolean(playing);
                            if (title2 != null) {
                                DashSession dashSession5 = dashSession$launchMediaInfo$1.this$0;
                                try {
                                } catch (Throwable th4) {
                                    th = th4;
                                    c3 = c2;
                                    title3 = title2;
                                    break;
                                }
                                Result.Companion companion8 = Result.INSTANCE;
                                dashSocket2 = dashSession5.socket;
                                if (dashSocket2 != null) {
                                    c3 = c2;
                                    try {
                                    } catch (Throwable th5) {
                                        th = th5;
                                        title3 = title2;
                                        break;
                                    }
                                    DashCommands dashCommands2 = DashCommands.INSTANCE;
                                    str = dashSession5.npAlbum;
                                    title3 = title2;
                                    title4 = dashSession5.npArtist;
                                    dashSocket2.send(dashCommands2.nowPlaying(title2, str, title4));
                                    unit2 = Unit.INSTANCE;
                                } else {
                                    c3 = c2;
                                    title3 = title2;
                                    unit2 = null;
                                }
                                Result.m8392constructorimpl(unit2);
                                if (!loggedNp.element) {
                                    loggedNp.element = true;
                                    RideDiagnostics.INSTANCE.log("media", "TX now-playing 05 0d: " + title2);
                                }
                            } else {
                                c3 = c2;
                                title3 = title2;
                            }
                            dashSession$launchMediaInfo$1.L$0 = $this$launch;
                            dashSession$launchMediaInfo$1.L$1 = c2;
                            dashSession$launchMediaInfo$1.L$2 = title2;
                            dashSession$launchMediaInfo$1.L$3 = prevPlaying2;
                            dashSession$launchMediaInfo$1.L$4 = loggedNp;
                            dashSession$launchMediaInfo$1.L$5 = SpillingKt.nullOutSpilledVariable(c3);
                            dashSession$launchMediaInfo$1.L$6 = SpillingKt.nullOutSpilledVariable(title3);
                            dashSession$launchMediaInfo$1.I$0 = i;
                            dashSession$launchMediaInfo$1.Z$0 = playing;
                            dashSession$launchMediaInfo$1.label = 1;
                            if (DelayKt.delay(1000L, dashSession$launchMediaInfo$1) == obj) {
                                return obj;
                            }
                            $this$launch2 = $this$launch;
                            prevCaller = c2;
                            prevPlaying = prevPlaying2;
                            i2 = i;
                            prevTitle = title2;
                            $result2 = $result3;
                            $this$launch = $this$launch2;
                            c = prevTitle;
                            title = prevCaller;
                            i = i2;
                            if (CoroutineScopeKt.isActive($this$launch)) {
                            }
                        }
                        if (title2 == null && c != null) {
                            DashSession dashSession6 = dashSession$launchMediaInfo$1.this$0;
                            try {
                            } catch (Throwable th6) {
                                Result.Companion companion9 = Result.INSTANCE;
                                Result.m8392constructorimpl(ResultKt.createFailure(th6));
                            }
                            Result.Companion companion10 = Result.INSTANCE;
                            dashSocket3 = dashSession6.socket;
                            if (dashSocket3 != null) {
                                dashSocket3.send(DashCommands.INSTANCE.musicActive(false));
                                unit3 = Unit.INSTANCE;
                            } else {
                                unit3 = null;
                            }
                            Result.m8392constructorimpl(unit3);
                            loggedNp.element = false;
                        }
                        Boolean prevPlaying22 = Boxing.boxBoolean(playing);
                        if (title2 != null) {
                        }
                        dashSession$launchMediaInfo$1.L$0 = $this$launch;
                        dashSession$launchMediaInfo$1.L$1 = c2;
                        dashSession$launchMediaInfo$1.L$2 = title2;
                        dashSession$launchMediaInfo$1.L$3 = prevPlaying22;
                        dashSession$launchMediaInfo$1.L$4 = loggedNp;
                        dashSession$launchMediaInfo$1.L$5 = SpillingKt.nullOutSpilledVariable(c3);
                        dashSession$launchMediaInfo$1.L$6 = SpillingKt.nullOutSpilledVariable(title3);
                        dashSession$launchMediaInfo$1.I$0 = i;
                        dashSession$launchMediaInfo$1.Z$0 = playing;
                        dashSession$launchMediaInfo$1.label = 1;
                        if (DelayKt.delay(1000L, dashSession$launchMediaInfo$1) == obj) {
                        }
                    }
                }
                return Unit.INSTANCE;
            case 1:
                boolean z2 = this.Z$0;
                int i3 = this.I$0;
                Ref.BooleanRef loggedNp3 = (Ref.BooleanRef) this.L$4;
                Boolean prevPlaying3 = (Boolean) this.L$3;
                prevTitle = (String) this.L$2;
                prevCaller = (String) this.L$1;
                ResultKt.throwOnFailure($result);
                dashSession$launchMediaInfo$1 = this;
                $this$launch2 = $this$launch3;
                loggedNp = loggedNp3;
                prevPlaying = prevPlaying3;
                i2 = i3;
                obj = coroutine_suspended;
                $result2 = $result;
                $this$launch = $this$launch2;
                c = prevTitle;
                title = prevCaller;
                i = i2;
                if (CoroutineScopeKt.isActive($this$launch)) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
