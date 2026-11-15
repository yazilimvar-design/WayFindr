package com.example.wayfindr.viewmodel;

import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.ViewModelKt;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.dash.DarkSchedule;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.dash.DashKeepAliveService;
import com.example.wayfindr.dash.DashSession;
import com.example.wayfindr.dash.DashState;
import com.example.wayfindr.dash.DashWifiManager;
import com.example.wayfindr.dash.PhoneStatus;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.WifiConnStatus;
import com.example.wayfindr.dash.WifiState;
import com.example.wayfindr.dash.map.LocationTracker;
import com.example.wayfindr.dash.map.MapRenderer;
import com.example.wayfindr.dash.map.NavigationReliability;
import com.example.wayfindr.dash.map.OffRouteConfirmation;
import com.example.wayfindr.dash.map.TileProvider;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Maneuver;
import com.example.wayfindr.dash.nav.ManeuverType;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.RouteProgressMatch;
import com.example.wayfindr.dash.nav.RouteProgressMatcher;
import com.example.wayfindr.dash.nav.TrafficInterval;
import com.example.wayfindr.dash.nav.VoiceManager;
import com.example.wayfindr.dash.video.DashEncoder;
import com.example.wayfindr.dash.video.NalProcessor;
import com.example.wayfindr.dash.video.RtpPacketizer;
import com.example.wayfindr.data.Ride;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.data.RideRecorder;
import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.data.SyncRepository;
import com.example.wayfindr.media.CallController;
import com.example.wayfindr.media.CallInfoProvider;
import com.example.wayfindr.media.IncomingCall;
import com.example.wayfindr.media.MediaInfoProvider;
import com.example.wayfindr.util.BuildId;
import com.example.wayfindr.util.IdleSleep;
import com.example.wayfindr.viewmodel.DashViewModel;
import com.example.wayfindr.viewmodel.PickerGesture;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.maplibre.turf.TurfConstants;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000à\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0007\u0018\u0000 ¸\u00022\u00020\u0001:\u0006·\u0002¸\u0002¹\u0002B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010?\u001a\u00020@2\u0012\u0010A\u001a\n\u0012\u0006\b\u0001\u0012\u00020=0B\"\u00020=H\u0002¢\u0006\u0002\u0010CJ\t\u0010\u009f\u0001\u001a\u00020@H\u0002J9\u0010¥\u0001\u001a\u000e\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020R0\u007f2\u0007\u0010¦\u0001\u001a\u00020R2\u0007\u0010§\u0001\u001a\u00020R2\u0007\u0010¨\u0001\u001a\u00020R2\u0007\u0010©\u0001\u001a\u00020RH\u0002J\u001b\u0010ª\u0001\u001a\u00020R2\u0007\u0010«\u0001\u001a\u00020i2\u0007\u0010¬\u0001\u001a\u00020RH\u0002J\t\u0010\u00ad\u0001\u001a\u00020@H\u0002J\u0007\u0010®\u0001\u001a\u00020@J\u0007\u0010¯\u0001\u001a\u00020@J\t\u0010°\u0001\u001a\u00020@H\u0002J\u0012\u0010±\u0001\u001a\u00020@2\u0007\u0010²\u0001\u001a\u00020=H\u0002J\t\u0010³\u0001\u001a\u00020@H\u0002J\u0012\u0010´\u0001\u001a\u00020@2\u0007\u0010µ\u0001\u001a\u000206H\u0002J\t\u0010¶\u0001\u001a\u00020@H\u0002J\t\u0010·\u0001\u001a\u00020@H\u0002J\t\u0010¸\u0001\u001a\u00020@H\u0002J\t\u0010¹\u0001\u001a\u00020@H\u0002J\t\u0010º\u0001\u001a\u00020@H\u0002J\u0010\u0010»\u0001\u001a\u00020@2\u0007\u0010¼\u0001\u001a\u00020=J\u0010\u0010½\u0001\u001a\u00020@2\u0007\u0010¾\u0001\u001a\u00020=J\u0007\u0010¿\u0001\u001a\u00020@J\u0019\u0010À\u0001\u001a\u00020@2\u0007\u0010¦\u0001\u001a\u00020R2\u0007\u0010§\u0001\u001a\u00020RJ#\u0010Á\u0001\u001a\u00020@2\r\u0010Â\u0001\u001a\b\u0012\u0004\u0012\u00020c0<2\u000b\b\u0002\u0010Ã\u0001\u001a\u0004\u0018\u00010gJ,\u0010Ä\u0001\u001a\u00020@2\u0007\u0010Å\u0001\u001a\u00020=2\t\u0010¦\u0001\u001a\u0004\u0018\u00010R2\t\u0010§\u0001\u001a\u0004\u0018\u00010R¢\u0006\u0003\u0010Æ\u0001J\u0007\u0010Ç\u0001\u001a\u00020@J*\u0010È\u0001\u001a\u00020@2\u0007\u0010É\u0001\u001a\u00020R2\u0007\u0010Ê\u0001\u001a\u00020R2\r\u0010Ë\u0001\u001a\b\u0012\u0004\u0012\u00020a0<H\u0002J\u0013\u0010Ì\u0001\u001a\u00020W2\b\u0010Í\u0001\u001a\u00030Î\u0001H\u0002J\u0013\u0010Ï\u0001\u001a\u00020W2\b\u0010Í\u0001\u001a\u00030Î\u0001H\u0002J\u0014\u0010Ð\u0001\u001a\u0004\u0018\u00010=2\u0007\u0010Ñ\u0001\u001a\u00020\u0015H\u0002J\t\u0010Ò\u0001\u001a\u00020WH\u0002J\t\u0010Ö\u0001\u001a\u00020@H\u0002J\u0012\u0010×\u0001\u001a\u00020@2\u0007\u0010²\u0001\u001a\u00020=H\u0002J\t\u0010Ø\u0001\u001a\u00020WH\u0002J\u000f\u0010Ù\u0001\u001a\b\u0012\u0004\u0012\u00020c0<H\u0002J\t\u0010Ú\u0001\u001a\u00020WH\u0002J\t\u0010Û\u0001\u001a\u00020\u0015H\u0002J\u0010\u0010Ü\u0001\u001a\t\u0012\u0005\u0012\u00030Ý\u00010<H\u0002J\u001b\u0010Þ\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020=0\u007f0<H\u0002J\"\u0010ß\u0001\u001a\u00020\u00152\u000e\u0010à\u0001\u001a\t\u0012\u0005\u0012\u00030Ý\u00010<2\u0007\u0010á\u0001\u001a\u00020\u0015H\u0002J\t\u0010â\u0001\u001a\u00020=H\u0002J\t\u0010ã\u0001\u001a\u00020\u0015H\u0002J\t\u0010ä\u0001\u001a\u00020\u0015H\u0002J\u0007\u0010å\u0001\u001a\u00020@J\u0007\u0010æ\u0001\u001a\u00020@J\n\u0010ç\u0001\u001a\u00030è\u0001H\u0002J\u0012\u0010é\u0001\u001a\u00020@2\u0007\u0010ê\u0001\u001a\u00020\u0015H\u0002J\u0019\u0010ë\u0001\u001a\u00020@2\u0007\u0010ì\u0001\u001a\u00020i2\u0007\u0010í\u0001\u001a\u00020iJ\u0007\u0010î\u0001\u001a\u00020@J\u0007\u0010ï\u0001\u001a\u00020@J\u0010\u0010ð\u0001\u001a\u00020@2\u0007\u0010ñ\u0001\u001a\u00020WJ\u0010\u0010ò\u0001\u001a\u00020@2\u0007\u0010ó\u0001\u001a\u00020WJ\u001b\u0010ô\u0001\u001a\u00020@2\u0007\u0010ì\u0001\u001a\u00020i2\u0007\u0010í\u0001\u001a\u00020iH\u0002J\t\u0010õ\u0001\u001a\u00020@H\u0002J\t\u0010ö\u0001\u001a\u00020WH\u0002J\u001e\u0010÷\u0001\u001a\u00020@2\u0007\u0010ø\u0001\u001a\u00020W2\n\u0010Í\u0001\u001a\u0005\u0018\u00010Î\u0001H\u0002J\u001d\u0010ù\u0001\u001a\u00020W2\b\u0010Í\u0001\u001a\u00030Î\u00012\b\u0010ú\u0001\u001a\u00030û\u0001H\u0002J\u001c\u0010ü\u0001\u001a\u00020R2\u0007\u0010ý\u0001\u001a\u00020g2\b\u0010ú\u0001\u001a\u00030û\u0001H\u0002J\u0015\u0010þ\u0001\u001a\u00020@2\n\u0010Í\u0001\u001a\u0005\u0018\u00010Î\u0001H\u0002J\u0012\u0010ÿ\u0001\u001a\u00020W2\u0007\u0010\u0080\u0002\u001a\u00020=H\u0002J\u0012\u0010\u0081\u0002\u001a\u00020i2\u0007\u0010\u0082\u0002\u001a\u00020iH\u0002J\t\u0010\u0083\u0002\u001a\u00020\u0015H\u0002J\t\u0010\u0084\u0002\u001a\u00020\u000eH\u0002J>\u0010\u0085\u0002\u001a\u00020@2\u0007\u0010\u0086\u0002\u001a\u00020R2\u0007\u0010\u0087\u0002\u001a\u00020R2\u0007\u0010\u0088\u0002\u001a\u00020i2\u0007\u0010\u0089\u0002\u001a\u00020i2\t\u0010\u008a\u0002\u001a\u0004\u0018\u00010RH\u0002¢\u0006\u0003\u0010\u008b\u0002J%\u0010\u009a\u0002\u001a\u00020@2\b\u0010\u009b\u0002\u001a\u00030\u009c\u00022\u0007\u0010\u009d\u0002\u001a\u00020\u00152\u0007\u0010\u009e\u0002\u001a\u00020\u0015H\u0002J\u001b\u0010\u009f\u0002\u001a\u00020=2\u0007\u0010¼\u0001\u001a\u00020=2\u0007\u0010 \u0002\u001a\u00020\u0015H\u0002J\u0013\u0010¡\u0002\u001a\u00020@2\b\u0010¢\u0002\u001a\u00030£\u0002H\u0002J\u0013\u0010¤\u0002\u001a\u00020@2\b\u0010¢\u0002\u001a\u00030£\u0002H\u0002JA\u0010¦\u0002\u001a\u00030û\u00012\u0007\u0010ý\u0001\u001a\u00020g2\u0007\u0010§\u0002\u001a\u00020a2\t\u0010¨\u0002\u001a\u0004\u0018\u00010i2\u0007\u0010«\u0001\u001a\u00020R2\t\b\u0002\u0010©\u0002\u001a\u00020WH\u0002¢\u0006\u0003\u0010ª\u0002J\t\u0010«\u0002\u001a\u00020@H\u0002J\u001e\u0010¬\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u007f2\u0007\u0010\u00ad\u0002\u001a\u00020RH\u0002J\u0012\u0010®\u0002\u001a\u00020=2\u0007\u0010¯\u0002\u001a\u00020RH\u0002J\u001b\u0010°\u0002\u001a\u00020i2\u0007\u0010±\u0002\u001a\u00020i2\u0007\u0010²\u0002\u001a\u00020iH\u0002J\t\u0010³\u0002\u001a\u00020@H\u0002J\t\u0010´\u0002\u001a\u00020@H\u0002J\t\u0010µ\u0002\u001a\u00020@H\u0002J\t\u0010¶\u0002\u001a\u00020@H\u0014R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00140\n8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\fR\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010;\u001a\n\u0012\u0004\u0012\u00020=\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010D\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010J\u001a\u00020K8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bL\u0010MR\u000e\u0010P\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010T\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010U\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010]\u001a\u0004\u0018\u00010RX\u0082\u000e¢\u0006\u0004\n\u0002\u0010^R\u0012\u0010_\u001a\u0004\u0018\u00010RX\u0082\u000e¢\u0006\u0004\n\u0002\u0010^R\u0014\u0010`\u001a\b\u0012\u0004\u0012\u00020a0<X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010b\u001a\b\u0012\u0004\u0012\u00020c0<X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010f\u001a\u0004\u0018\u00010gX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020iX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020iX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020iX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010w\u001a\u00020iX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020iX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010|\u001a\u00020}X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010~\u001a\u0010\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020R\u0018\u00010\u007fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0080\u0001\u001a\u0004\u0018\u00010RX\u0082\u000e¢\u0006\u0004\n\u0002\u0010^R\u0013\u0010\u0081\u0001\u001a\u0004\u0018\u00010RX\u0082\u000e¢\u0006\u0004\n\u0002\u0010^R\u000f\u0010\u0082\u0001\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0083\u0001\u001a\u0004\u0018\u00010gX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0084\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0085\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0005\n\u0003\u0010\u0087\u0001R\u000f\u0010\u0088\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u008b\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u008c\u0001\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u008d\u0001\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u008e\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u008f\u0001\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0090\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0091\u0001\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0092\u0001\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0093\u0001\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0094\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0095\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0096\u0001\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0097\u0001\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0098\u0001\u001a\u0004\u0018\u00010RX\u0082\u000e¢\u0006\u0004\n\u0002\u0010^R\u0013\u0010\u0099\u0001\u001a\u0004\u0018\u00010RX\u0082\u000e¢\u0006\u0004\n\u0002\u0010^R\u0011\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u009b\u0001\u001a\u00020=X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u009c\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u009d\u0001\u001a\t\u0012\u0004\u0012\u00020@0\u009e\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010 \u0001\u001a\u00030¡\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010¢\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010£\u0001\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010¤\u0001\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010Ó\u0001\u001a\u00020WX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010Ô\u0001\u001a\u0005\u0018\u00010Õ\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u008c\u0002\u001a\u00030\u008d\u00028BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0090\u0002\u0010O\u001a\u0006\b\u008e\u0002\u0010\u008f\u0002R \u0010\u0091\u0002\u001a\u00030\u008d\u00028BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0093\u0002\u0010O\u001a\u0006\b\u0092\u0002\u0010\u008f\u0002R \u0010\u0094\u0002\u001a\u00030\u008d\u00028BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0096\u0002\u0010O\u001a\u0006\b\u0095\u0002\u0010\u008f\u0002R \u0010\u0097\u0002\u001a\u00030\u008d\u00028BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0099\u0002\u0010O\u001a\u0006\b\u0098\u0002\u0010\u008f\u0002R\u000f\u0010¥\u0002\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"Lcom/example/wayfindr/viewmodel/DashViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "_ui", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/wayfindr/viewmodel/DashUiState;", "ui", "Lkotlinx/coroutines/flow/StateFlow;", "getUi", "()Lkotlinx/coroutines/flow/StateFlow;", "_previewFrame", "Landroid/graphics/Bitmap;", "previewFrame", "getPreviewFrame", "session", "Lcom/example/wayfindr/dash/DashSession;", "dashFields", "", "", "Lcom/example/wayfindr/dash/DashSession$DashField;", "getDashFields", "wifiManager", "Lcom/example/wayfindr/dash/DashWifiManager;", "dashConfig", "Lcom/example/wayfindr/dash/DashConfig;", "voice", "Lcom/example/wayfindr/dash/nav/VoiceManager;", "repo", "Lcom/example/wayfindr/data/SyncRepository;", "recorder", "Lcom/example/wayfindr/data/RideRecorder;", "recordJob", "Lkotlinx/coroutines/Job;", "tiles", "Lcom/example/wayfindr/dash/map/TileProvider;", "location", "Lcom/example/wayfindr/dash/map/LocationTracker;", "mapRenderer", "Lcom/example/wayfindr/dash/map/MapRenderer;", "mediaInfo", "Lcom/example/wayfindr/media/MediaInfoProvider;", "callController", "Lcom/example/wayfindr/media/CallController;", "mediaObserveJob", "powerManager", "Landroid/os/PowerManager;", "encoder", "Lcom/example/wayfindr/dash/video/DashEncoder;", "streamJob", "tickerJob", "videoPtsMs", "", "zoomPressedAtMs", "pickerInputAtMs", "lastLoneDownMs", "downHintUntilMs", "notice", "", "", "noticeUntilMs", "showNotice", "", "lines", "", "([Ljava/lang/String;)V", "pickerItems", "Lcom/example/wayfindr/data/SavedLocation;", "pickerIndex", "menuPage", "Lcom/example/wayfindr/viewmodel/DashViewModel$MenuPage;", "pickerLastInputMs", "pickerGesture", "Lcom/example/wayfindr/viewmodel/PickerGesture;", "getPickerGesture", "()Lcom/example/wayfindr/viewmodel/PickerGesture;", "pickerGesture$delegate", "Lkotlin/Lazy;", "zoomBeforeRightTap", "sendPaceMs", "", "sendPaceMaxMs", "giveupJob", "connLostAlertJob", "connLostAlerted", "", "dashWifiWasConnected", "userWantsConnection", "hasStreamedThisConnection", "waitingForFreshRouteOrigin", "authAttempts", "destLat", "Ljava/lang/Double;", "destLng", "routeWaypoints", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "routeStops", "Lcom/example/wayfindr/data/SharedLocation;", "activeStopIndex", "routeLegOffset", "route", "Lcom/example/wayfindr/dash/nav/Route;", "panX", "", "panY", "zoom", "headingUp", "mapDark", "followMode", "lastManualPanAt", "camLat", "camLng", "camHdg", "camInit", "lastTickNs", "fixLat", "fixLng", "fixBearing", "fixSpeed", "fixWallMs", "lastFixTime", "gpsFixIntervalMs", "gpsStatus", "Lcom/example/wayfindr/viewmodel/GpsStatus;", "heldRider", "Lkotlin/Pair;", "frameRiderLat", "frameRiderLng", "smoothEtaSec", "etaRoute", "etaArrivalMs", "lastArrivalCalcMs", "displayedEtaMin", "Ljava/lang/Integer;", "offRouteSince", "offRouteConfirmation", "Lcom/example/wayfindr/dash/map/OffRouteConfirmation;", "lastRerouteAt", "rerouting", "trafficRefreshInFlight", "routeRequestGeneration", "routeRequestJob", "lastTrafficRefreshAt", "rerouteStreak", "autoRouteCalls", "autoRouteCapLogged", "offRouteStreakStart", "recalcHoldUntil", "lastSnapDist", "smoothSpeedMps", "matchedLat", "matchedLng", "frameBitmap", "lastSignature", "zoomBadgeUntilMs", "frameWake", "Lkotlinx/coroutines/channels/Channel;", "wakeFrameLoop", "joyTrail", "Lcom/example/wayfindr/viewmodel/JoystickTrail;", "lastRedrawAt", "loggedFirstFrame", "lastDiagMs", "project", "lat", "lng", "bearingDeg", "distM", "predictedDistance", "speedMps", "elapsedSec", "refreshStage", "connect", "disconnect", "releaseBackgroundResources", "giveUp", "reason", "sleepNow", "armReconnectGiveup", "timeoutMs", "cancelReconnectGiveup", "armConnLostAlert", "cancelConnLostAlert", "startRecording", "stopRecording", "setSsid", "s", "setWifiPassword", "p", "forgetDash", "prefetchTiles", "setDestinations", "stops", "selectedRoute", "setDestination", HintConstants.AUTOFILL_HINT_NAME, "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;)V", "exitNavigation", "fetchRoute", "destLatV", "destLngV", "waypoints", "isPreciseRouteOrigin", "loc", "Landroid/location/Location;", "isFallbackRouteOrigin", "handlePickerInput", "code", "canOpenPicker", "debugInjecting", "debugButtonRx", "Landroid/content/BroadcastReceiver;", "openPicker", "closePicker", "hasDestination", "stopsAhead", "canSkipStop", "rootFixedRows", "menuRows", "Lcom/example/wayfindr/dash/map/MapRenderer$MenuRow;", "cancelRows", "nextMenuIndex", "rows", "from", "selectPickerItem", "zoomInCode", "zoomOutCode", "zoomIn", "zoomOut", "zoomRange", "Lkotlin/ranges/IntRange;", "setZoom", "z", "panBy", "dx", "dy", "recenter", "toggleHeadingUp", "setMapDark", "dark", "setMapDarkAuto", "auto", "manualPan", "startStream", "tick", "maybeReroute", "offRoute", "advanceWaypointIfReached", "nav", "Lcom/example/wayfindr/viewmodel/DashViewModel$NavState;", "remainingToActiveStop", "r", "maybeRefreshTraffic", "takeAutoRouteCall", "kind", "normalizeHeading", TurfConstants.UNIT_DEGREES, "renderScale", "newFrameBitmap", "redrawFrame", "centerLat", "centerLng", "cameraHeading", "travelHeading", "remainingM", "(DDFFLjava/lang/Double;)V", "ovBg", "Landroid/graphics/Paint;", "getOvBg", "()Landroid/graphics/Paint;", "ovBg$delegate", "ovTitle", "getOvTitle", "ovTitle$delegate", "ovSub", "getOvSub", "ovSub$delegate", "ovAccent", "getOvAccent", "ovAccent$delegate", "drawDashOverlays", "canvas", "Landroid/graphics/Canvas;", "w", "h", "ellipsizeOverlay", "max", "answerCall", "c", "Lcom/example/wayfindr/media/IncomingCall;", "endCall", "progressM", "trackProgress", "pos", "gpsBearing", "advanceProgress", "(Lcom/example/wayfindr/dash/nav/Route;Lcom/example/wayfindr/dash/nav/GeoPoint;Ljava/lang/Float;DZ)Lcom/example/wayfindr/viewmodel/DashViewModel$NavState;", "updateThermal", "toDashDistance", TurfConstants.UNIT_METERS, "fmtDist", "m", "angleDelta", "a", "b", "teardown", "startMediaForwarding", "stopMediaForwarding", "onCleared", "MenuPage", "Companion", "NavState"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class DashViewModel extends AndroidViewModel {
    private static final float BEARING_MIN_SPEED = 1.1f;
    private static final int BIG_FRAME_BYTES = 30000;
    private static final int BTN_ANSWER = 6;
    private static final int BTN_JOY_DOWN = 21;
    private static final int BTN_JOY_LEFT = 20;
    private static final int BTN_JOY_RIGHT = 19;
    private static final int BTN_MAP_ZOOM_IN = 20;
    private static final int BTN_MAP_ZOOM_OUT = 19;
    private static final int BTN_MEDIA_NEXT = 9;
    private static final int BTN_MEDIA_PREV = 10;
    private static final int BTN_REJECT = 7;
    private static final long CONN_LOST_ALERT_MS = 12000;
    private static final int DASH_MAX_ZOOM = 20;
    private static final int DASH_MIN_ZOOM = 11;
    private static final double DISPLAY_LEAD_S = 0.4d;
    private static final long DOUBLE_DOWN_MS = 3000;
    private static final long DOUBLE_TAP_MS = 500;
    private static final long FORCE_REDRAW_MS = 2000;
    private static final long FRAME_MS_ACTIVE = 250;
    private static final long FRAME_MS_IDLE = 250;
    private static final long GPS_LOST_AGE_MS = 12000;
    private static final long GPS_WEAK_AGE_MS = 4000;
    private static final long INITIAL_CONNECT_GIVEUP_MS = 120000;
    private static final long MANUAL_IDLE_MS = 8000;
    private static final int MAX_AUTH_ATTEMPTS = 4;
    private static final int MAX_AUTO_ROUTE_CALLS = 40;
    private static final double MAX_PREDICT_S = 3.0d;
    private static final List<Pair<RoutingSource, String>> MENU_ROUTING_SOURCES;
    private static final long MOTION_HOLD_MS = 1500;
    private static final long NOTICE_MS = 4000;
    private static final long PICKER_TIMEOUT_MS = 30000;
    private static final long RECONNECT_GIVEUP_MS = 21600000;
    private static final long REROUTE_BASE_MS = 6000;
    private static final long REROUTE_MAX_MS = 120000;
    private static final long REROUTE_STREAK_RESET_MS = 60000;
    private static final float ROUTE_ORIGIN_FALLBACK_M = 100.0f;
    private static final float ROUTE_ORIGIN_PRECISE_M = 60.0f;
    private static final long ROUTE_ORIGIN_WAIT_MS = 12000;
    private static final double SECONDARY_TURN_MAX_GAP_M = 500.0d;
    private static final double SMOOTH_TAU = 0.2d;
    private static final long TRAFFIC_REFRESH_MS = 120000;
    private static final double WAYPOINT_ARRIVE_M = 35.0d;
    private static final double WAYPOINT_PROGRESS_TOLERANCE_M = 25.0d;
    private static final long ZOOM_BADGE_MS = 3000;
    private final MutableStateFlow<Bitmap> _previewFrame;
    private final MutableStateFlow<DashUiState> _ui;
    private volatile int activeStopIndex;
    private int authAttempts;
    private volatile int autoRouteCalls;
    private volatile boolean autoRouteCapLogged;
    private final CallController callController;
    private float camHdg;
    private boolean camInit;
    private double camLat;
    private double camLng;
    private Job connLostAlertJob;
    private boolean connLostAlerted;
    private final DashConfig dashConfig;
    private boolean dashWifiWasConnected;
    private final BroadcastReceiver debugButtonRx;
    private volatile boolean debugInjecting;
    private volatile Double destLat;
    private volatile Double destLng;
    private volatile Integer displayedEtaMin;
    private volatile long downHintUntilMs;
    private DashEncoder encoder;
    private volatile long etaArrivalMs;
    private Route etaRoute;
    private float fixBearing;
    private double fixLat;
    private double fixLng;
    private float fixSpeed;
    private long fixWallMs;
    private volatile boolean followMode;
    private Bitmap frameBitmap;
    private volatile Double frameRiderLat;
    private volatile Double frameRiderLng;
    private final Channel<Unit> frameWake;
    private Job giveupJob;
    private double gpsFixIntervalMs;
    private volatile GpsStatus gpsStatus;
    private volatile boolean hasStreamedThisConnection;
    private volatile boolean headingUp;
    private Pair<Double, Double> heldRider;
    private final JoystickTrail joyTrail;
    private long lastArrivalCalcMs;
    private long lastDiagMs;
    private long lastFixTime;
    private long lastLoneDownMs;
    private volatile long lastManualPanAt;
    private long lastRedrawAt;
    private volatile long lastRerouteAt;
    private String lastSignature;
    private volatile double lastSnapDist;
    private long lastTickNs;
    private volatile long lastTrafficRefreshAt;
    private final LocationTracker location;
    private volatile boolean loggedFirstFrame;
    private volatile boolean mapDark;
    private final MapRenderer mapRenderer;
    private volatile Double matchedLat;
    private volatile Double matchedLng;
    private final MediaInfoProvider mediaInfo;
    private Job mediaObserveJob;
    private volatile MenuPage menuPage;
    private volatile List<String> notice;
    private volatile long noticeUntilMs;
    private final OffRouteConfirmation offRouteConfirmation;
    private volatile long offRouteSince;
    private volatile long offRouteStreakStart;

    /* renamed from: ovAccent$delegate, reason: from kotlin metadata */
    private final Lazy ovAccent;

    /* renamed from: ovBg$delegate, reason: from kotlin metadata */
    private final Lazy ovBg;

    /* renamed from: ovSub$delegate, reason: from kotlin metadata */
    private final Lazy ovSub;

    /* renamed from: ovTitle$delegate, reason: from kotlin metadata */
    private final Lazy ovTitle;
    private volatile float panX;
    private volatile float panY;

    /* renamed from: pickerGesture$delegate, reason: from kotlin metadata */
    private final Lazy pickerGesture;
    private volatile int pickerIndex;
    private volatile long pickerInputAtMs;
    private volatile List<SavedLocation> pickerItems;
    private volatile long pickerLastInputMs;
    private final PowerManager powerManager;
    private final StateFlow<Bitmap> previewFrame;
    private volatile double progressM;
    private volatile long recalcHoldUntil;
    private Job recordJob;
    private final RideRecorder recorder;
    private final SyncRepository repo;
    private volatile int rerouteStreak;
    private volatile boolean rerouting;
    private volatile Route route;
    private volatile int routeLegOffset;
    private volatile long routeRequestGeneration;
    private Job routeRequestJob;
    private volatile List<SharedLocation> routeStops;
    private volatile List<GeoPoint> routeWaypoints;
    private long sendPaceMaxMs;
    private double sendPaceMs;
    private final DashSession session;
    private double smoothEtaSec;
    private double smoothSpeedMps;
    private Job streamJob;
    private Job tickerJob;
    private final TileProvider tiles;
    private volatile boolean trafficRefreshInFlight;
    private final StateFlow<DashUiState> ui;
    private boolean userWantsConnection;
    private volatile long videoPtsMs;
    private final VoiceManager voice;
    private volatile boolean waitingForFreshRouteOrigin;
    private final DashWifiManager wifiManager;
    private volatile int zoom;
    private volatile long zoomBadgeUntilMs;
    private int zoomBeforeRightTap;
    private volatile long zoomPressedAtMs;
    public static final int $stable = 8;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/example/wayfindr/viewmodel/DashViewModel$MenuPage;", "", "<init>", "(Ljava/lang/String;I)V", "ROOT", "SOURCE", "CANCEL", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    enum MenuPage {
        ROOT,
        SOURCE,
        CANCEL;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<MenuPage> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: DashViewModel.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MenuPage.values().length];
            try {
                iArr[MenuPage.ROOT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MenuPage.CANCEL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MenuPage.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashViewModel(Application app) {
        super(app);
        DashUiState value;
        Intrinsics.checkNotNullParameter(app, "app");
        this._ui = StateFlowKt.MutableStateFlow(new DashUiState(null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -1, null));
        this.ui = FlowKt.asStateFlow(this._ui);
        this._previewFrame = StateFlowKt.MutableStateFlow(null);
        this.previewFrame = FlowKt.asStateFlow(this._previewFrame);
        DashSession dashSession = new DashSession(ViewModelKt.getViewModelScope(this));
        dashSession.setPhoneStatusProvider(new Function0() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                PhoneStatus read;
                read = PhoneStatus.INSTANCE.read(DashViewModel.this.getApplication());
                return read;
            }
        });
        this.session = dashSession;
        this.wifiManager = new DashWifiManager(app, ViewModelKt.getViewModelScope(this));
        this.dashConfig = DashConfig.INSTANCE.get(app);
        this.voice = VoiceManager.INSTANCE.get(app);
        this.repo = SyncRepository.INSTANCE.get(app);
        this.recorder = new RideRecorder();
        this.tiles = new TileProvider(app, ViewModelKt.getViewModelScope(this));
        this.location = new LocationTracker(app);
        this.mapRenderer = new MapRenderer(this.tiles);
        this.mediaInfo = new MediaInfoProvider(app);
        this.callController = new CallController(app);
        Object systemService = app.getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        this.powerManager = (PowerManager) systemService;
        this.menuPage = MenuPage.ROOT;
        this.pickerGesture = LazyKt.lazy(new Function0() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DashViewModel.pickerGesture_delegate$lambda$2();
            }
        });
        this.routeWaypoints = CollectionsKt.emptyList();
        this.routeStops = CollectionsKt.emptyList();
        this.zoom = 19;
        this.headingUp = true;
        this.followMode = true;
        this.gpsStatus = GpsStatus.LOST;
        this.offRouteConfirmation = new OffRouteConfirmation(0, 0L, 3, null);
        this.lastSnapDist = -1.0d;
        this.lastSignature = "";
        this.frameWake = ChannelKt.Channel$default(-1, null, null, 6, null);
        this.joyTrail = new JoystickTrail(0L, 0, 3, null);
        this.mapDark = DarkSchedule.effective$default(DarkSchedule.INSTANCE, this.dashConfig.getMapDarkAuto().getValue().booleanValue(), this.dashConfig.getMapDarkMode().getValue().booleanValue(), false, 4, null);
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, this.dashConfig.getSsid(), this.dashConfig.getPassword(), null, null, 0, 0, null, null, null, false, null, false, false, false, false, this.mapDark, this.dashConfig.getMapDarkAuto().getValue().booleanValue(), false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -786457, null)));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass2(null), 3, null);
        this.wifiManager.setOnSsidResolved(new Function1() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashViewModel._init_$lambda$5(DashViewModel.this, (String) obj);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass4(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass5(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass6(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass7(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass8(null), 3, null);
        this.session.setOnError(new Function1() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashViewModel._init_$lambda$7(DashViewModel.this, (String) obj);
            }
        });
        this.session.setOnButton(new Function1() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashViewModel._init_$lambda$10(DashViewModel.this, ((Byte) obj).byteValue());
            }
        });
        this.debugButtonRx = BuildConfig.DEBUG ? new BroadcastReceiver() { // from class: com.example.wayfindr.viewmodel.DashViewModel$debugButtonRx$1
            /* JADX WARN: Code restructure failed: missing block: B:73:0x0216, code lost:
            
                if ((r22 < r24) != false) goto L76;
             */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onReceive(Context c, Intent intent) {
                Iterable iterable;
                int i;
                String str;
                String str2;
                String str3;
                List list;
                DashViewModel.MenuPage menuPage;
                int i2;
                String str4;
                List list2;
                int i3;
                IntRange zoomRange;
                long j;
                List<GeoPoint> geometry;
                GeoPoint geoPoint;
                List<GeoPoint> geometry2;
                Bitmap bitmap;
                Bitmap bitmap2;
                Bitmap newFrameBitmap;
                Intrinsics.checkNotNullParameter(c, "c");
                Intrinsics.checkNotNullParameter(intent, "intent");
                int code = intent.getIntExtra("code", -1);
                if (code >= 0) {
                    DashViewModel.this.debugInjecting = true;
                    try {
                        Function1<Byte, Unit> onButton = DashViewModel.this.session.getOnButton();
                        if (onButton != null) {
                            onButton.invoke(Byte.valueOf((byte) code));
                        }
                    } finally {
                        DashViewModel.this.debugInjecting = false;
                    }
                }
                if (intent.getBooleanExtra("snap", false)) {
                    Location loc = DashViewModel.this.location.lastKnown();
                    bitmap = DashViewModel.this.frameBitmap;
                    if (bitmap == null) {
                        DashViewModel dashViewModel = DashViewModel.this;
                        newFrameBitmap = DashViewModel.this.newFrameBitmap();
                        dashViewModel.frameBitmap = newFrameBitmap;
                    }
                    if (loc != null) {
                        DashViewModel.this.redrawFrame(loc.getLatitude(), loc.getLongitude(), 0.0f, 0.0f, null);
                    }
                    File dir = DashViewModel.this.getApplication().getExternalFilesDir("diag");
                    bitmap2 = DashViewModel.this.frameBitmap;
                    if (bitmap2 != null) {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(dir, "snap.png"));
                        try {
                            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                            CloseableKt.closeFinally(fileOutputStream, null);
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(fileOutputStream, th);
                                throw th2;
                            }
                        }
                    }
                    Log.i("DashViewModel", "DEBUG snap saved (fix=" + (loc != null) + ")");
                }
                if (intent.getBooleanExtra("dump", false)) {
                    Route r = DashViewModel.this.route;
                    iterable = DashViewModel.this.routeStops;
                    Iterable iterable2 = iterable;
                    Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                    Iterator it = iterable2.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((SharedLocation) it.next()).getName());
                    }
                    Collection collection = (List) arrayList;
                    i = DashViewModel.this.activeStopIndex;
                    Double d = DashViewModel.this.destLat;
                    if (d != null) {
                        str = String.format("%.5f", Arrays.copyOf(new Object[]{Double.valueOf(d.doubleValue())}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    } else {
                        str = null;
                    }
                    Double d2 = DashViewModel.this.destLng;
                    if (d2 != null) {
                        str2 = String.format("%.5f", Arrays.copyOf(new Object[]{Double.valueOf(d2.doubleValue())}, 1));
                        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                    } else {
                        str2 = null;
                    }
                    int size = (r == null || (geometry2 = r.getGeometry()) == null) ? 0 : geometry2.size();
                    int totalMeters = r != null ? (int) r.getTotalMeters() : 0;
                    if (r == null || (geometry = r.getGeometry()) == null || (geoPoint = (GeoPoint) CollectionsKt.lastOrNull((List) geometry)) == null) {
                        str3 = null;
                    } else {
                        str3 = String.format("%.5f,%.5f", Arrays.copyOf(new Object[]{Double.valueOf(geoPoint.getLat()), Double.valueOf(geoPoint.getLng())}, 2));
                        Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                    }
                    list = DashViewModel.this.pickerItems;
                    if (list == null) {
                        str4 = "closed";
                    } else {
                        menuPage = DashViewModel.this.menuPage;
                        i2 = DashViewModel.this.pickerIndex;
                        str4 = menuPage + "@" + i2;
                    }
                    list2 = DashViewModel.this.notice;
                    if (list2 != null) {
                        DashViewModel dashViewModel2 = DashViewModel.this;
                        long currentTimeMillis = System.currentTimeMillis();
                        j = dashViewModel2.noticeUntilMs;
                    }
                    list2 = null;
                    i3 = DashViewModel.this.zoom;
                    zoomRange = DashViewModel.this.zoomRange();
                    String msg = "stops=" + collection + " active=" + i + " dest=" + str + "," + str2 + " route=" + size + " pts " + totalMeters + " m end=" + str3 + " menu=" + str4 + " notice=" + list2 + " zoom=" + i3 + " range=" + zoomRange + " map=OSM";
                    Log.i("DashViewModel", "DEBUG " + msg);
                    RideDiagnostics.INSTANCE.log(BuildConfig.BUILD_TYPE, msg);
                }
            }
        } : null;
        BroadcastReceiver broadcastReceiver = this.debugButtonRx;
        if (broadcastReceiver != null) {
            IntentFilter intentFilter = new IntentFilter("com.wayfindr.DASH_BUTTON");
            Application application = getApplication();
            if (Build.VERSION.SDK_INT >= 33) {
                application.registerReceiver(broadcastReceiver, intentFilter, 2);
            } else {
                application.registerReceiver(broadcastReceiver, intentFilter);
            }
        }
        this.ovBg = LazyKt.lazy(new Function0() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DashViewModel.ovBg_delegate$lambda$65();
            }
        });
        this.ovTitle = LazyKt.lazy(new Function0() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DashViewModel.ovTitle_delegate$lambda$67();
            }
        });
        this.ovSub = LazyKt.lazy(new Function0() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DashViewModel.ovSub_delegate$lambda$69();
            }
        });
        this.ovAccent = LazyKt.lazy(new Function0() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DashViewModel.ovAccent_delegate$lambda$71();
            }
        });
    }

    public final StateFlow<DashUiState> getUi() {
        return this.ui;
    }

    public final StateFlow<Bitmap> getPreviewFrame() {
        return this.previewFrame;
    }

    public final StateFlow<Map<Integer, DashSession.DashField>> getDashFields() {
        return this.session.getDashFields();
    }

    private final void showNotice(String... lines) {
        this.notice = ArraysKt.toList(lines);
        this.noticeUntilMs = System.currentTimeMillis() + 4000;
        this.lastSignature = "";
        wakeFrameLoop();
    }

    private final PickerGesture getPickerGesture() {
        return (PickerGesture) this.pickerGesture.getValue();
    }

    static final PickerGesture pickerGesture_delegate$lambda$2() {
        return new PickerGesture(19, 20, 21, 500L, 3000L);
    }

    private final void wakeFrameLoop() {
        this.frameWake.mo9882trySendJP2dKIU(Unit.INSTANCE);
    }

    static {
        Iterable listOf = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(RoutingSource.GOOGLE, "Google Maps"), TuplesKt.to(RoutingSource.OSM, "OSM"), TuplesKt.to(RoutingSource.BROUTER, "Offline (BRouter)")});
        Collection arrayList = new ArrayList();
        for (Object obj : listOf) {
            if (RoutingSource.INSTANCE.getAvailable().contains(((Pair) obj).getFirst())) {
                arrayList.add(obj);
            }
        }
        MENU_ROUTING_SOURCES = (List) arrayList;
    }

    private final Pair<Double, Double> project(double lat, double lng, double bearingDeg, double distM) {
        double br = Math.toRadians(bearingDeg);
        double dr = distM / 6371000.0d;
        double lat1 = Math.toRadians(lat);
        double lng1 = Math.toRadians(lng);
        double lat2 = Math.asin((Math.sin(lat1) * Math.cos(dr)) + (Math.cos(lat1) * Math.sin(dr) * Math.cos(br)));
        double lng2 = Math.atan2(Math.sin(br) * Math.sin(dr) * Math.cos(lat1), Math.cos(dr) - (Math.sin(lat1) * Math.sin(lat2))) + lng1;
        return TuplesKt.to(Double.valueOf(Math.toDegrees(lat2)), Double.valueOf(Math.toDegrees(lng2)));
    }

    private final double predictedDistance(float speedMps, double elapsedSec) {
        return speedMps * RangesKt.coerceIn(elapsedSec, 0.0d, MAX_PREDICT_S);
    }

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$2", f = "DashViewModel.kt", i = {0}, l = {423}, m = "invokeSuspend", n = {"clock"}, s = {"L$0"})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DashViewModel.this.new AnonymousClass2(continuation);
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
                    Flow clock = FlowKt.flow(new DashViewModel$2$clock$1(null));
                    Flow distinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(DashViewModel.this.dashConfig.getMapDarkAuto(), DashViewModel.this.dashConfig.getMapDarkMode(), clock, new AnonymousClass1(null)));
                    final DashViewModel dashViewModel = DashViewModel.this;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(clock);
                    this.label = 1;
                    if (distinctUntilChanged.collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.DashViewModel.2.2
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((Pair<Boolean, Boolean>) value, (Continuation<? super Unit>) $completion);
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        public final Object emit(Pair<Boolean, Boolean> pair, Continuation<? super Unit> continuation) {
                            boolean auto = pair.component1().booleanValue();
                            boolean dark = pair.component2().booleanValue();
                            if (dark != DashViewModel.this.mapDark) {
                                RideDiagnostics.INSTANCE.log("map", "dark map " + (dark ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF) + " (" + (auto ? "auto 7 pm–5 am" : "manual") + ")");
                            }
                            DashViewModel.this.mapDark = dark;
                            DashViewModel.this.lastSignature = "";
                            MutableStateFlow mutableStateFlow = DashViewModel.this._ui;
                            while (true) {
                                Object value = mutableStateFlow.getValue();
                                MutableStateFlow mutableStateFlow2 = mutableStateFlow;
                                boolean dark2 = dark;
                                if (mutableStateFlow2.compareAndSet(value, DashUiState.copy$default((DashUiState) value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, dark2, auto, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -786433, null))) {
                                    return Unit.INSTANCE;
                                }
                                mutableStateFlow = mutableStateFlow2;
                                dark = dark2;
                            }
                        }
                    }, this) != coroutine_suspended) {
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        /* compiled from: DashViewModel.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "auto", "manual", "<unused var>", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$2$1", f = "DashViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function4<Boolean, Boolean, Unit, Continuation<? super Pair<? extends Boolean, ? extends Boolean>>, Object> {
            /* synthetic */ boolean Z$0;
            /* synthetic */ boolean Z$1;
            int label;

            AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
                super(4, continuation);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Boolean bool2, Unit unit, Continuation<? super Pair<? extends Boolean, ? extends Boolean>> continuation) {
                return invoke(bool.booleanValue(), bool2.booleanValue(), unit, (Continuation<? super Pair<Boolean, Boolean>>) continuation);
            }

            public final Object invoke(boolean z, boolean z2, Unit unit, Continuation<? super Pair<Boolean, Boolean>> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
                anonymousClass1.Z$0 = z;
                anonymousClass1.Z$1 = z2;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                boolean auto = this.Z$0;
                boolean manual = this.Z$1;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        return TuplesKt.to(Boxing.boxBoolean(auto), Boxing.boxBoolean(DarkSchedule.effective$default(DarkSchedule.INSTANCE, auto, manual, false, 4, null)));
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    static final Unit _init_$lambda$5(DashViewModel this$0, String learned) {
        String learned2 = learned;
        Intrinsics.checkNotNullParameter(learned2, "learned");
        this$0.dashConfig.setSsid(learned2);
        MutableStateFlow mutableStateFlow = this$0._ui;
        while (true) {
            DashUiState value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, learned2, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -9, null))) {
                return Unit.INSTANCE;
            }
            learned2 = learned;
            mutableStateFlow = mutableStateFlow2;
        }
    }

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$4", f = "DashViewModel.kt", i = {}, l = {444}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DashViewModel.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: DashViewModel.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "ws", "Lcom/example/wayfindr/dash/WifiState;", "st", "Lcom/example/wayfindr/dash/DashState;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$4$1", f = "DashViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$4$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<WifiState, DashState, Continuation<? super Boolean>, Object> {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            int label;

            AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(WifiState wifiState, DashState dashState, Continuation<? super Boolean> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
                anonymousClass1.L$0 = wifiState;
                anonymousClass1.L$1 = dashState;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                WifiState ws = (WifiState) this.L$0;
                DashState st = (DashState) this.L$1;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        return Boxing.boxBoolean(ws.getStatus() == WifiConnStatus.CONNECTED || st == DashState.STREAMING);
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (FlowKt.distinctUntilChanged(FlowKt.combine(DashViewModel.this.wifiManager.getState(), DashViewModel.this.session.getState(), new AnonymousClass1(null))).collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.DashViewModel.4.2
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit(((Boolean) value).booleanValue(), (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(boolean it, Continuation<? super Unit> continuation) {
                            IdleSleep.INSTANCE.setDashLinked(it);
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
            return Unit.INSTANCE;
        }
    }

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$5", f = "DashViewModel.kt", i = {}, l = {447}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$5, reason: invalid class name */
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass5(Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DashViewModel.this.new AnonymousClass5(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<Boolean> asleep = IdleSleep.INSTANCE.getAsleep();
                    final DashViewModel dashViewModel = DashViewModel.this;
                    this.label = 1;
                    if (asleep.collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.DashViewModel.5.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit(((Boolean) value).booleanValue(), (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(boolean asleep2, Continuation<? super Unit> continuation) {
                            if (asleep2 && DashViewModel.this.session.getState().getValue() != DashState.STREAMING) {
                                DashViewModel.this.sleepNow();
                            }
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

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$6", f = "DashViewModel.kt", i = {}, l = {452}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$6, reason: invalid class name */
    static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DashViewModel.this.new AnonymousClass6(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<Location> location = DashViewModel.this.location.getLocation();
                    final DashViewModel dashViewModel = DashViewModel.this;
                    this.label = 1;
                    if (location.collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.DashViewModel.6.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((Location) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(Location fix, Continuation<? super Unit> continuation) {
                            if (DashViewModel.this.waitingForFreshRouteOrigin && fix != null && DashViewModel.this.isFallbackRouteOrigin(fix)) {
                                Double d = DashViewModel.this.destLat;
                                if (d != null) {
                                    double lat = d.doubleValue();
                                    Double d2 = DashViewModel.this.destLng;
                                    if (d2 != null) {
                                        double lng = d2.doubleValue();
                                        DashViewModel.this.waitingForFreshRouteOrigin = false;
                                        DashViewModel.this.fetchRoute(lat, lng, DashViewModel.this.routeWaypoints);
                                        return Unit.INSTANCE;
                                    }
                                    return Unit.INSTANCE;
                                }
                                return Unit.INSTANCE;
                            }
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

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$7", f = "DashViewModel.kt", i = {}, l = {461}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$7, reason: invalid class name */
    static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass7(Continuation<? super AnonymousClass7> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DashViewModel.this.new AnonymousClass7(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: DashViewModel.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$7$1, reason: invalid class name */
        static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ DashViewModel this$0;

            /* compiled from: DashViewModel.kt */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$7$1$WhenMappings */
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[WifiConnStatus.values().length];
                    try {
                        iArr[WifiConnStatus.CONNECTED.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[WifiConnStatus.ERROR.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            AnonymousClass1(DashViewModel dashViewModel) {
                this.this$0 = dashViewModel;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
            /* JADX WARN: Removed duplicated region for block: B:24:0x003b  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(WifiState ws, Continuation<? super Unit> continuation) {
                DashViewModel$7$1$emit$1 dashViewModel$7$1$emit$1;
                String str;
                Object value;
                Object value2;
                String str2;
                Double lat;
                Double lng;
                if (continuation instanceof DashViewModel$7$1$emit$1) {
                    dashViewModel$7$1$emit$1 = (DashViewModel$7$1$emit$1) continuation;
                    if ((dashViewModel$7$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        dashViewModel$7$1$emit$1.label -= Integer.MIN_VALUE;
                        Object $result = dashViewModel$7$1$emit$1.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (dashViewModel$7$1$emit$1.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                RideDiagnostics rideDiagnostics = RideDiagnostics.INSTANCE;
                                WifiConnStatus status = ws.getStatus();
                                String ssid = ws.getSsid();
                                if (StringsKt.isBlank(ssid)) {
                                    ssid = null;
                                }
                                String str3 = "";
                                if (ssid == null || (str = " ssid=" + ssid) == null) {
                                    str = "";
                                }
                                String error = ws.getError();
                                if (error != null && (str2 = " err=" + error) != null) {
                                    str3 = str2;
                                }
                                rideDiagnostics.log("wifi", status + str + str3);
                                int i = ws.getStatus() == WifiConnStatus.CONNECTED ? 1 : 0;
                                if (this.this$0.dashWifiWasConnected && i == 0 && this.this$0.userWantsConnection) {
                                    RideDiagnostics.INSTANCE.log("connect", "dash Wi-Fi lost — closing old network-bound session; keeping itinerary for reconnect");
                                    this.this$0.session.disconnect();
                                    this.this$0.teardown();
                                    this.this$0.location.stop();
                                    DashKeepAliveService.INSTANCE.pauseForReconnect(this.this$0.getApplication());
                                    this.this$0.routeRequestGeneration++;
                                    Job job = this.this$0.routeRequestJob;
                                    if (job != null) {
                                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                                    }
                                    this.this$0.routeRequestJob = null;
                                    this.this$0.rerouting = false;
                                    this.this$0.trafficRefreshInFlight = false;
                                    this.this$0.offRouteSince = 0L;
                                    this.this$0.offRouteConfirmation.reset();
                                    MutableStateFlow mutableStateFlow = this.this$0._ui;
                                    do {
                                        value2 = mutableStateFlow.getValue();
                                    } while (!mutableStateFlow.compareAndSet(value2, DashUiState.copy$default((DashUiState) value2, null, 0, null, null, null, null, "Gösterge bağlantısı kesildi; otomatik yeniden bağlanılıyor…", 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -65, null)));
                                }
                                this.this$0.dashWifiWasConnected = i != 0;
                                switch (WhenMappings.$EnumSwitchMapping$0[ws.getStatus().ordinal()]) {
                                    case 1:
                                        this.this$0.refreshStage();
                                        if (this.this$0.userWantsConnection && CollectionsKt.listOf((Object[]) new DashState[]{DashState.IDLE, DashState.ERROR}).contains(this.this$0.session.getState().getValue())) {
                                            dashViewModel$7$1$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(ws);
                                            dashViewModel$7$1$emit$1.I$0 = i;
                                            dashViewModel$7$1$emit$1.label = 1;
                                            if (DelayKt.delay(1200L, dashViewModel$7$1$emit$1) != coroutine_suspended) {
                                                if (this.this$0.userWantsConnection && this.this$0.wifiManager.getState().getValue().getStatus() == WifiConnStatus.CONNECTED) {
                                                    DashKeepAliveService.INSTANCE.start(this.this$0.getApplication());
                                                    this.this$0.location.start();
                                                    lat = this.this$0.destLat;
                                                    lng = this.this$0.destLng;
                                                    if (this.this$0.route == null && lat != null && lng != null) {
                                                        this.this$0.fetchRoute(lat.doubleValue(), lng.doubleValue(), this.this$0.routeWaypoints);
                                                    }
                                                    this.this$0.session.connect(((DashUiState) this.this$0._ui.getValue()).getSsid(), this.this$0.wifiManager.getNetwork());
                                                }
                                                return Unit.INSTANCE;
                                            }
                                            return coroutine_suspended;
                                        }
                                        return Unit.INSTANCE;
                                    case 2:
                                        this.this$0.stopRecording();
                                        this.this$0.userWantsConnection = false;
                                        this.this$0.releaseBackgroundResources();
                                        this.this$0.session.disconnect();
                                        MutableStateFlow mutableStateFlow2 = this.this$0._ui;
                                        do {
                                            value = mutableStateFlow2.getValue();
                                        } while (!mutableStateFlow2.compareAndSet(value, DashUiState.copy$default((DashUiState) value, null, 0, null, null, null, null, ws.getError(), 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -65, null)));
                                        this.this$0.refreshStage();
                                        RideDiagnostics.INSTANCE.stop("wifi error / dash gone");
                                        return Unit.INSTANCE;
                                    default:
                                        this.this$0.refreshStage();
                                        return Unit.INSTANCE;
                                }
                            case 1:
                                int i2 = dashViewModel$7$1$emit$1.I$0;
                                ResultKt.throwOnFailure($result);
                                if (this.this$0.userWantsConnection) {
                                    DashKeepAliveService.INSTANCE.start(this.this$0.getApplication());
                                    this.this$0.location.start();
                                    lat = this.this$0.destLat;
                                    lng = this.this$0.destLng;
                                    if (this.this$0.route == null) {
                                        this.this$0.fetchRoute(lat.doubleValue(), lng.doubleValue(), this.this$0.routeWaypoints);
                                    }
                                    this.this$0.session.connect(((DashUiState) this.this$0._ui.getValue()).getSsid(), this.this$0.wifiManager.getNetwork());
                                    break;
                                }
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }
                dashViewModel$7$1$emit$1 = new DashViewModel$7$1$emit$1(this, continuation);
                Object $result2 = dashViewModel$7$1$emit$1.result;
                Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (dashViewModel$7$1$emit$1.label) {
                }
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((WifiState) value, (Continuation<? super Unit>) $completion);
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DashViewModel.this.wifiManager.getState().collect(new AnonymousClass1(DashViewModel.this), this) == coroutine_suspended) {
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

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$8", f = "DashViewModel.kt", i = {}, l = {528}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$8, reason: invalid class name */
    static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass8(Continuation<? super AnonymousClass8> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DashViewModel.this.new AnonymousClass8(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: DashViewModel.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$8$1, reason: invalid class name */
        static final class AnonymousClass1<T> implements FlowCollector {
            final /* synthetic */ DashViewModel this$0;

            /* compiled from: DashViewModel.kt */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$8$1$WhenMappings */
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[DashState.values().length];
                    try {
                        iArr[DashState.READY.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[DashState.STREAMING.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[DashState.ERROR.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            AnonymousClass1(DashViewModel dashViewModel) {
                this.this$0 = dashViewModel;
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
            /* JADX WARN: Removed duplicated region for block: B:27:0x0046  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object emit(DashState state, Continuation<? super Unit> continuation) {
                DashViewModel$8$1$emit$1 dashViewModel$8$1$emit$1;
                Object value;
                Object value2;
                Object value3;
                Object value4;
                if (continuation instanceof DashViewModel$8$1$emit$1) {
                    dashViewModel$8$1$emit$1 = (DashViewModel$8$1$emit$1) continuation;
                    if ((dashViewModel$8$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        dashViewModel$8$1$emit$1.label -= Integer.MIN_VALUE;
                        Object $result = dashViewModel$8$1$emit$1.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (dashViewModel$8$1$emit$1.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                RideDiagnostics.INSTANCE.log("session", "→ " + state);
                                this.this$0.refreshStage();
                                DashState dashState = DashState.STREAMING;
                                DashViewModel dashViewModel = this.this$0;
                                if (state == dashState) {
                                    dashViewModel.hasStreamedThisConnection = true;
                                    this.this$0.cancelReconnectGiveup();
                                    this.this$0.cancelConnLostAlert();
                                } else if (dashViewModel.userWantsConnection) {
                                    this.this$0.armReconnectGiveup(this.this$0.hasStreamedThisConnection ? DashViewModel.RECONNECT_GIVEUP_MS : 120000L);
                                    this.this$0.armConnLostAlert();
                                }
                                switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                                    case 1:
                                        this.this$0.authAttempts = 0;
                                        MutableStateFlow mutableStateFlow = this.this$0._ui;
                                        do {
                                            value = mutableStateFlow.getValue();
                                        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -129, null)));
                                        this.this$0.startStream();
                                        return Unit.INSTANCE;
                                    case 2:
                                        this.this$0.authAttempts = 0;
                                        MutableStateFlow mutableStateFlow2 = this.this$0._ui;
                                        do {
                                            value2 = mutableStateFlow2.getValue();
                                        } while (!mutableStateFlow2.compareAndSet(value2, DashUiState.copy$default((DashUiState) value2, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -193, null)));
                                        return Unit.INSTANCE;
                                    case 3:
                                        if (!this.this$0.userWantsConnection || this.this$0.wifiManager.getState().getValue().getStatus() != WifiConnStatus.CONNECTED || this.this$0.authAttempts >= 4) {
                                            MutableStateFlow mutableStateFlow3 = this.this$0._ui;
                                            do {
                                                value3 = mutableStateFlow3.getValue();
                                            } while (!mutableStateFlow3.compareAndSet(value3, DashUiState.copy$default((DashUiState) value3, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -129, null)));
                                            if (this.this$0.userWantsConnection && this.this$0.authAttempts >= 4 && this.this$0.wifiManager.getState().getValue().getStatus() == WifiConnStatus.CONNECTED) {
                                                boolean z = this.this$0.hasStreamedThisConnection;
                                                DashViewModel dashViewModel2 = this.this$0;
                                                if (z) {
                                                    dashViewModel2.authAttempts = 0;
                                                    dashViewModel$8$1$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(state);
                                                    dashViewModel$8$1$emit$1.label = 2;
                                                    if (DelayKt.delay(3000L, dashViewModel$8$1$emit$1) == coroutine_suspended) {
                                                        return coroutine_suspended;
                                                    }
                                                    if (this.this$0.userWantsConnection && this.this$0.wifiManager.getState().getValue().getStatus() == WifiConnStatus.CONNECTED && this.this$0.session.getState().getValue() == DashState.ERROR) {
                                                        this.this$0.session.connect(((DashUiState) this.this$0._ui.getValue()).getSsid(), this.this$0.wifiManager.getNetwork());
                                                    }
                                                } else {
                                                    dashViewModel2.giveUp("auth exhausted after " + this.this$0.authAttempts + " attempts");
                                                }
                                            }
                                            return Unit.INSTANCE;
                                        }
                                        this.this$0.authAttempts++;
                                        MutableStateFlow mutableStateFlow4 = this.this$0._ui;
                                        DashViewModel dashViewModel3 = this.this$0;
                                        do {
                                            value4 = mutableStateFlow4.getValue();
                                        } while (!mutableStateFlow4.compareAndSet(value4, DashUiState.copy$default((DashUiState) value4, null, 0, null, null, null, null, null, dashViewModel3.authAttempts, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -193, null)));
                                        dashViewModel$8$1$emit$1.L$0 = SpillingKt.nullOutSpilledVariable(state);
                                        dashViewModel$8$1$emit$1.label = 1;
                                        if (DelayKt.delay(1500L, dashViewModel$8$1$emit$1) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        if (this.this$0.userWantsConnection && this.this$0.wifiManager.getState().getValue().getStatus() == WifiConnStatus.CONNECTED) {
                                            this.this$0.session.connect(((DashUiState) this.this$0._ui.getValue()).getSsid(), this.this$0.wifiManager.getNetwork());
                                        }
                                        return Unit.INSTANCE;
                                    default:
                                        return Unit.INSTANCE;
                                }
                            case 1:
                                ResultKt.throwOnFailure($result);
                                if (this.this$0.userWantsConnection) {
                                    this.this$0.session.connect(((DashUiState) this.this$0._ui.getValue()).getSsid(), this.this$0.wifiManager.getNetwork());
                                    break;
                                }
                                return Unit.INSTANCE;
                            case 2:
                                ResultKt.throwOnFailure($result);
                                if (this.this$0.userWantsConnection) {
                                    this.this$0.session.connect(((DashUiState) this.this$0._ui.getValue()).getSsid(), this.this$0.wifiManager.getNetwork());
                                    break;
                                }
                                return Unit.INSTANCE;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }
                dashViewModel$8$1$emit$1 = new DashViewModel$8$1$emit$1(this, continuation);
                Object $result2 = dashViewModel$8$1$emit$1.result;
                Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (dashViewModel$8$1$emit$1.label) {
                }
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((DashState) value, (Continuation<? super Unit>) $completion);
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DashViewModel.this.session.getState().collect(new AnonymousClass1(DashViewModel.this), this) == coroutine_suspended) {
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

    static final Unit _init_$lambda$7(DashViewModel this$0, String msg) {
        String msg2 = msg;
        Intrinsics.checkNotNullParameter(msg2, "msg");
        RideDiagnostics.INSTANCE.log("error", msg2);
        MutableStateFlow mutableStateFlow = this$0._ui;
        while (true) {
            DashUiState value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, null, msg2, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -65, null))) {
                this$0.refreshStage();
                return Unit.INSTANCE;
            }
            msg2 = msg;
            mutableStateFlow = mutableStateFlow2;
        }
    }

    static final Unit _init_$lambda$10(DashViewModel this$0, byte btn) {
        char c;
        Character valueOf;
        String str;
        DashUiState value;
        int code = btn & UByte.MAX_VALUE;
        switch (code) {
            case 19:
                c = 'R';
                valueOf = Character.valueOf(c);
                break;
            case 20:
                c = 'L';
                valueOf = Character.valueOf(c);
                break;
            case 21:
                c = 'D';
                valueOf = Character.valueOf(c);
                break;
            default:
                valueOf = null;
                break;
        }
        if (valueOf != null) {
            char charValue = valueOf.charValue();
            if (this$0.dashConfig.getShowJoystickTrail().getValue().booleanValue()) {
                this$0.joyTrail.add(charValue, System.currentTimeMillis());
            }
        }
        IncomingCall call = CallInfoProvider.INSTANCE.getIncomingCall().getValue();
        boolean ringing = call != null && call.getIncoming();
        boolean active = (call == null || call.getIncoming()) ? false : true;
        String pickerLabel = ringing ? null : this$0.handlePickerInput(code);
        if (pickerLabel != null) {
            str = pickerLabel;
        } else if (ringing && code == 6) {
            Intrinsics.checkNotNull(call);
            this$0.answerCall(call);
            str = "Call answered";
        } else if (ringing && code == 7) {
            Intrinsics.checkNotNull(call);
            this$0.endCall(call);
            str = "Call rejected";
        } else if (active && code == 7) {
            Intrinsics.checkNotNull(call);
            this$0.endCall(call);
            str = "Call ended";
        } else if (!ringing && code == this$0.zoomInCode()) {
            this$0.zoomIn();
            str = "Zoom in (map)";
        } else if (!ringing && code == this$0.zoomOutCode()) {
            this$0.zoomOut();
            str = "Zoom out (map)";
        } else if (!ringing && code == 9) {
            this$0.mediaInfo.skipNext();
            str = "Next track";
        } else if (!ringing && code == 10) {
            this$0.mediaInfo.skipPrevious();
            str = "Previous track";
        } else if (code == 34) {
            str = "Compass (dash)";
        } else {
            String num = Integer.toString(code, CharsKt.checkRadix(16));
            Intrinsics.checkNotNullExpressionValue(num, "toString(...)");
            String upperCase = num.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            str = "code 0x" + upperCase;
        }
        String label = str;
        RideDiagnostics rideDiagnostics = RideDiagnostics.INSTANCE;
        String format = String.format("0x%02X (ringing=%b active=%b) → %s", Arrays.copyOf(new Object[]{Integer.valueOf(code), Boolean.valueOf(ringing), Boolean.valueOf(active), label}, 4));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        rideDiagnostics.log("button", format);
        MutableStateFlow mutableStateFlow = this$0._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, label, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -5, null)));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshStage() {
        ConnStage connStage;
        DashUiState value;
        WifiConnStatus wifi = this.wifiManager.getState().getValue().getStatus();
        DashState dash = this.session.getState().getValue();
        if (dash == DashState.STREAMING) {
            connStage = ConnStage.STREAMING;
        } else if (dash == DashState.ERROR || wifi == WifiConnStatus.ERROR) {
            connStage = ConnStage.ERROR;
        } else if (dash == DashState.AUTHENTICATING || dash == DashState.CONNECTING || dash == DashState.READY) {
            connStage = ConnStage.AUTH;
        } else if (wifi == WifiConnStatus.REQUESTING || wifi == WifiConnStatus.CONNECTED) {
            connStage = ConnStage.WIFI;
        } else {
            connStage = ConnStage.OFFLINE;
        }
        ConnStage stage = connStage;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, stage, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -2, null)));
    }

    public final void connect() {
        DashUiState value;
        Job launch$default;
        String findDashSsid;
        DashUiState value2;
        this.userWantsConnection = true;
        this.hasStreamedThisConnection = false;
        this.authAttempts = 0;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -4194497, null)));
        if (this.wifiManager.isWifiEnabled()) {
            RideDiagnostics.INSTANCE.init(getApplication());
            RideDiagnostics.INSTANCE.start("connect");
            RideDiagnostics.INSTANCE.log("build", "apk=" + BuildId.INSTANCE.sha12(getApplication()) + " vc=14 name=1.3.2-OSS-preview");
            RideDiagnostics.INSTANCE.log("connect", "ssid='" + this.dashConfig.getSsid() + "' needsDiscovery=" + this.dashConfig.getNeedsDiscovery() + " dest=" + this._ui.getValue().getDestinationName());
            this.loggedFirstFrame = false;
            DashKeepAliveService.INSTANCE.start(getApplication());
            this.location.start();
            startRecording();
            Job job = this.tickerJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getDefault(), null, new DashViewModel$connect$3(this, null), 2, null);
            this.tickerJob = launch$default;
            if (this.dashConfig.getNeedsDiscovery() && (findDashSsid = this.wifiManager.findDashSsid(this.dashConfig.getSsidPrefix())) != null) {
                int i = 0;
                this.dashConfig.setSsid(findDashSsid);
                MutableStateFlow mutableStateFlow2 = this._ui;
                while (true) {
                    DashUiState value3 = mutableStateFlow2.getValue();
                    MutableStateFlow mutableStateFlow3 = mutableStateFlow2;
                    int i2 = i;
                    if (mutableStateFlow3.compareAndSet(value3, DashUiState.copy$default(value3, null, 0, null, findDashSsid, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -9, null))) {
                        break;
                    }
                    mutableStateFlow2 = mutableStateFlow3;
                    i = i2;
                }
            }
            if (this.wifiManager.getState().getValue().getStatus() == WifiConnStatus.CONNECTED) {
                this.session.connect(this._ui.getValue().getSsid(), this.wifiManager.getNetwork());
                return;
            }
            boolean isBlank = StringsKt.isBlank(this.dashConfig.getSsid());
            DashWifiManager dashWifiManager = this.wifiManager;
            if (!isBlank) {
                DashWifiManager.connect$default(dashWifiManager, this.dashConfig.getSsid(), this.dashConfig.getPassword(), false, 4, null);
                return;
            } else {
                dashWifiManager.connect(this.dashConfig.getSsidPrefix(), this.dashConfig.getPassword(), true);
                return;
            }
        }
        this.userWantsConnection = false;
        MutableStateFlow mutableStateFlow4 = this._ui;
        do {
            value2 = mutableStateFlow4.getValue();
        } while (!mutableStateFlow4.compareAndSet(value2, DashUiState.copy$default(value2, ConnStage.ERROR, 0, null, null, null, null, "Göstergeye bağlanmak için Wi‑Fi'yi açın", 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, true, null, null, 0.0f, null, null, 0.0f, null, null, null, -4194370, null)));
    }

    public final void disconnect() {
        this.userWantsConnection = false;
        cancelReconnectGiveup();
        RideDiagnostics.INSTANCE.log("connect", "user disconnect");
        stopRecording();
        this.session.disconnect();
        this.wifiManager.disconnect();
        releaseBackgroundResources();
        refreshStage();
        RideDiagnostics.INSTANCE.stop("disconnect");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseBackgroundResources() {
        teardown();
        this.location.stop();
        DashKeepAliveService.INSTANCE.stop(getApplication());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void giveUp(String reason) {
        cancelReconnectGiveup();
        this.userWantsConnection = false;
        this.session.disconnect();
        this.wifiManager.disconnect();
        releaseBackgroundResources();
        refreshStage();
        RideDiagnostics.INSTANCE.log("error", "gave up: " + reason + " — background released");
        RideDiagnostics.INSTANCE.stop(reason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sleepNow() {
        RideDiagnostics.INSTANCE.log("sleep", "app asleep — stopping background work");
        if (!this.userWantsConnection) {
            this.wifiManager.disconnect();
            releaseBackgroundResources();
        } else {
            giveUp("app asleep (idle 10 min)");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void armReconnectGiveup(long timeoutMs) {
        Job launch$default;
        Job job = this.giveupJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (z) {
            return;
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashViewModel$armReconnectGiveup$1(timeoutMs, this, null), 3, null);
        this.giveupJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelReconnectGiveup() {
        Job job = this.giveupJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.giveupJob = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void armConnLostAlert() {
        Job launch$default;
        Job job = this.connLostAlertJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (!z && !this.connLostAlerted) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashViewModel$armConnLostAlert$1(this, null), 3, null);
            this.connLostAlertJob = launch$default;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelConnLostAlert() {
        Job job = this.connLostAlertJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.connLostAlertJob = null;
        if (this.connLostAlerted) {
            this.connLostAlerted = false;
            this.voice.alert("Gösterge bağlantısı yeniden kuruldu");
        }
    }

    private final void startRecording() {
        Job launch$default;
        if (this.recorder.getRecording()) {
            return;
        }
        this.recorder.start();
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashViewModel$startRecording$1(this, null), 3, null);
        this.recordJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopRecording() {
        Ride ride;
        Job job = this.recordJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.recordJob = null;
        if (this.recorder.getRecording() && (ride = this.recorder.stop()) != null) {
            this.repo.addRide(ride);
            String format = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(ride.getDistanceKm())}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Log.i("DashViewModel", "Ride saved: " + format + " km, " + ride.getDurationSec() + "s");
        }
    }

    public final void setSsid(String s) {
        DashUiState value;
        Intrinsics.checkNotNullParameter(s, "s");
        this.dashConfig.setSsid(StringsKt.trim((CharSequence) s).toString());
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, StringsKt.trim((CharSequence) s).toString(), null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -9, null)));
    }

    public final void setWifiPassword(String p) {
        String p2 = p;
        Intrinsics.checkNotNullParameter(p2, "p");
        this.dashConfig.setPassword(p2);
        MutableStateFlow mutableStateFlow = this._ui;
        while (true) {
            DashUiState value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, p2, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -17, null))) {
                return;
            }
            p2 = p;
            mutableStateFlow = mutableStateFlow2;
        }
    }

    public final void forgetDash() {
        DashUiState value;
        this.dashConfig.forgetDash();
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, "", null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -9, null)));
    }

    public final void prefetchTiles(double lat, double lng) {
        Location loc = this.location.getLocation().getValue();
        this.tiles.prefetch(lat, lng, loc != null ? Double.valueOf(loc.getLatitude()) : null, loc != null ? Double.valueOf(loc.getLongitude()) : null);
    }

    public static /* synthetic */ void setDestinations$default(DashViewModel dashViewModel, List list, Route route, int i, Object obj) {
        if ((i & 2) != 0) {
            route = null;
        }
        dashViewModel.setDestinations(list, route);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0130 A[LOOP:1: B:49:0x012a->B:51:0x0130, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02c9 A[LOOP:2: B:54:0x0172->B:71:0x02c9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x022d A[EDGE_INSN: B:72:0x022d->B:73:0x022d BREAK  A[LOOP:2: B:54:0x0172->B:71:0x02c9], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0183  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setDestinations(List<SharedLocation> stops, Route selectedRoute) {
        String str;
        double d;
        Route selectedForTrip;
        MutableStateFlow mutableStateFlow;
        DashUiState value;
        DashUiState dashUiState;
        boolean z;
        Pair pair;
        List<GeoPoint> geometry;
        List<GeoPoint> list;
        List<TrafficInterval> traffic;
        double d2;
        Double lng;
        SharedLocation finalDestination;
        String name;
        boolean z2;
        DashViewModel dashViewModel = this;
        Intrinsics.checkNotNullParameter(stops, "stops");
        dashViewModel.routeRequestGeneration++;
        Job job = dashViewModel.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        dashViewModel.routeRequestJob = null;
        dashViewModel.rerouting = false;
        dashViewModel.trafficRefreshInFlight = false;
        dashViewModel.waitingForFreshRouteOrigin = false;
        dashViewModel.offRouteSince = 0L;
        dashViewModel.offRouteConfirmation.reset();
        dashViewModel.offRouteStreakStart = 0L;
        Collection arrayList = new ArrayList();
        for (Object obj : stops) {
            SharedLocation sharedLocation = (SharedLocation) obj;
            if (((sharedLocation.getLat() == null || sharedLocation.getLng() == null) ? null : 1) != null) {
                arrayList.add(obj);
            }
        }
        List validStops = (List) arrayList;
        SharedLocation finalDestination2 = (SharedLocation) CollectionsKt.lastOrNull(validStops);
        if (finalDestination2 == null || (str = finalDestination2.getName()) == null) {
            str = "WayfindR";
        }
        String name2 = str;
        Double lat = finalDestination2 != null ? finalDestination2.getLat() : null;
        Double lng2 = finalDestination2 != null ? finalDestination2.getLng() : null;
        if (selectedRoute != null) {
            GeoPoint geoPoint = (GeoPoint) CollectionsKt.lastOrNull((List) selectedRoute.getGeometry());
            d = 0.0d;
            if (selectedRoute.getSource() == dashViewModel.dashConfig.getRoutingSource().getValue() && selectedRoute.getGeometry().size() >= 2) {
                if ((Math.abs(selectedRoute.getTotalMeters()) <= Double.MAX_VALUE) && selectedRoute.getTotalMeters() >= 0.0d && geoPoint != null && lat != null && lng2 != null && GeoPoint.INSTANCE.distMeters(geoPoint, new GeoPoint(lat.doubleValue(), lng2.doubleValue())) <= 750.0d) {
                    z2 = true;
                    if (z2) {
                        selectedForTrip = selectedRoute;
                        dashViewModel.routeStops = validStops;
                        dashViewModel.autoRouteCalls = 0;
                        dashViewModel.autoRouteCapLogged = false;
                        dashViewModel.rerouteStreak = 0;
                        dashViewModel.lastRerouteAt = 0L;
                        dashViewModel.activeStopIndex = 0;
                        dashViewModel.routeLegOffset = 0;
                        Iterable<SharedLocation> dropLast = CollectionsKt.dropLast(validStops, 1);
                        int i = 0;
                        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(dropLast, 10));
                        for (SharedLocation sharedLocation2 : dropLast) {
                            Double lat2 = sharedLocation2.getLat();
                            Intrinsics.checkNotNull(lat2);
                            double doubleValue = lat2.doubleValue();
                            Double lng3 = sharedLocation2.getLng();
                            Intrinsics.checkNotNull(lng3);
                            arrayList2.add(new GeoPoint(doubleValue, lng3.doubleValue()));
                            dropLast = dropLast;
                            i = i;
                        }
                        dashViewModel.routeWaypoints = (List) arrayList2;
                        mutableStateFlow = dashViewModel._ui;
                        while (true) {
                            value = mutableStateFlow.getValue();
                            dashUiState = value;
                            z = selectedForTrip == null;
                            pair = (lat != null || lng2 == null) ? null : TuplesKt.to(lat, lng2);
                            geometry = selectedForTrip == null ? selectedForTrip.getGeometry() : null;
                            if (geometry == null) {
                                geometry = CollectionsKt.emptyList();
                            }
                            list = geometry;
                            traffic = selectedForTrip == null ? selectedForTrip.getTraffic() : null;
                            if (traffic == null) {
                                traffic = CollectionsKt.emptyList();
                            }
                            d2 = d;
                            lng = lng2;
                            finalDestination = finalDestination2;
                            name = name2;
                            if (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, null, 0, null, null, null, name2, null, 0, 0, null, null, null, false, null, z, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, pair, list, traffic, 536854495, null))) {
                                break;
                            }
                            dashViewModel = this;
                            d = d2;
                            finalDestination2 = finalDestination;
                            name2 = name;
                            lng2 = lng;
                        }
                        dashViewModel.destLat = lat;
                        dashViewModel.destLng = lng;
                        dashViewModel.route = selectedForTrip;
                        if (dashViewModel.frameBitmap == null) {
                            dashViewModel.frameBitmap = dashViewModel.newFrameBitmap();
                        }
                        dashViewModel.lastSignature = "";
                        dashViewModel.progressM = d2;
                        dashViewModel.smoothEtaSec = d2;
                        dashViewModel.etaArrivalMs = 0L;
                        dashViewModel.displayedEtaMin = null;
                        dashViewModel.voice.resetTrip();
                        dashViewModel.session.updateRouteCard(name);
                        if (lat != null || lng == null) {
                        }
                        Location loc = dashViewModel.location.lastKnown();
                        dashViewModel.tiles.prefetch(lat.doubleValue(), lng.doubleValue(), loc != null ? Double.valueOf(loc.getLatitude()) : null, loc != null ? Double.valueOf(loc.getLongitude()) : null);
                        if (selectedForTrip != null) {
                            dashViewModel.lastTrafficRefreshAt = System.currentTimeMillis();
                            dashViewModel.tiles.prefetchRoute(selectedForTrip.getGeometry());
                            return;
                        }
                        Intrinsics.checkNotNull(finalDestination);
                        Double lat3 = finalDestination.getLat();
                        Intrinsics.checkNotNull(lat3);
                        double doubleValue2 = lat3.doubleValue();
                        Double lng4 = finalDestination.getLng();
                        Intrinsics.checkNotNull(lng4);
                        dashViewModel.fetchRoute(doubleValue2, lng4.doubleValue(), dashViewModel.routeWaypoints);
                        return;
                    }
                }
            }
            z2 = false;
            if (z2) {
            }
        } else {
            d = 0.0d;
        }
        selectedForTrip = null;
        dashViewModel.routeStops = validStops;
        dashViewModel.autoRouteCalls = 0;
        dashViewModel.autoRouteCapLogged = false;
        dashViewModel.rerouteStreak = 0;
        dashViewModel.lastRerouteAt = 0L;
        dashViewModel.activeStopIndex = 0;
        dashViewModel.routeLegOffset = 0;
        Iterable<SharedLocation> dropLast2 = CollectionsKt.dropLast(validStops, 1);
        int i2 = 0;
        Collection arrayList22 = new ArrayList(CollectionsKt.collectionSizeOrDefault(dropLast2, 10));
        while (r14.hasNext()) {
        }
        dashViewModel.routeWaypoints = (List) arrayList22;
        mutableStateFlow = dashViewModel._ui;
        while (true) {
            value = mutableStateFlow.getValue();
            dashUiState = value;
            if (selectedForTrip == null) {
            }
            if (lat != null) {
            }
            if (selectedForTrip == null) {
            }
            if (geometry == null) {
            }
            list = geometry;
            if (selectedForTrip == null) {
            }
            if (traffic == null) {
            }
            d2 = d;
            lng = lng2;
            finalDestination = finalDestination2;
            name = name2;
            if (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, null, 0, null, null, null, name2, null, 0, 0, null, null, null, false, null, z, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, pair, list, traffic, 536854495, null))) {
            }
            dashViewModel = this;
            d = d2;
            finalDestination2 = finalDestination;
            name2 = name;
            lng2 = lng;
        }
        dashViewModel.destLat = lat;
        dashViewModel.destLng = lng;
        dashViewModel.route = selectedForTrip;
        if (dashViewModel.frameBitmap == null) {
        }
        dashViewModel.lastSignature = "";
        dashViewModel.progressM = d2;
        dashViewModel.smoothEtaSec = d2;
        dashViewModel.etaArrivalMs = 0L;
        dashViewModel.displayedEtaMin = null;
        dashViewModel.voice.resetTrip();
        dashViewModel.session.updateRouteCard(name);
        if (lat != null) {
        }
    }

    public final void setDestination(String name, Double lat, Double lng) {
        Intrinsics.checkNotNullParameter(name, "name");
        setDestinations$default(this, CollectionsKt.listOf(new SharedLocation(name, lat, lng, null, false, 24, null)), null, 2, null);
    }

    public final void exitNavigation() {
        DashUiState value;
        this.routeRequestGeneration++;
        Job job = this.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.routeRequestJob = null;
        this.rerouting = false;
        this.trafficRefreshInFlight = false;
        this.waitingForFreshRouteOrigin = false;
        this.destLat = null;
        this.destLng = null;
        this.routeWaypoints = CollectionsKt.emptyList();
        this.routeStops = CollectionsKt.emptyList();
        this.activeStopIndex = 0;
        this.routeLegOffset = 0;
        this.route = null;
        this.progressM = 0.0d;
        this.offRouteSince = 0L;
        this.offRouteConfirmation.reset();
        this.panX = 0.0f;
        this.panY = 0.0f;
        this.followMode = true;
        this.smoothEtaSec = 0.0d;
        this.etaArrivalMs = 0L;
        this.displayedEtaMin = null;
        this.voice.resetTrip();
        this.lastSignature = "";
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, true, null, false, null, null, 0.0f, null, null, 0.0f, null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), 535769567, null)));
        this.session.updateRouteCard("WayfindR");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchRoute(double destLatV, double destLngV, List<GeoPoint> waypoints) {
        Job launch$default;
        this.routeRequestGeneration++;
        long requestId = this.routeRequestGeneration;
        Job job = this.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.waitingForFreshRouteOrigin = false;
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashViewModel$fetchRoute$1(this, requestId, destLatV, destLngV, waypoints, null), 3, null);
        this.routeRequestJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPreciseRouteOrigin(Location loc) {
        return LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.INSTANCE, loc, 0L, 0L, 6, null) && loc.hasAccuracy() && loc.getAccuracy() <= 60.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFallbackRouteOrigin(Location loc) {
        return LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.INSTANCE, loc, 0L, 0L, 6, null) && (!loc.hasAccuracy() || loc.getAccuracy() <= ROUTE_ORIGIN_FALLBACK_M);
    }

    private final String handlePickerInput(int code) {
        long now = System.currentTimeMillis();
        boolean doubleTapMode = this.dashConfig.getPickerDoubleTap().getValue().booleanValue();
        PickerGesture.Result g = getPickerGesture().onCode(code, now, doubleTapMode);
        if (code == 21 && this.pickerItems == null && !g.getActivate()) {
            String gap = this.lastLoneDownMs > 0 ? (now - this.lastLoneDownMs) + " ms after the previous lone DOWN" : "first";
            RideDiagnostics.INSTANCE.log("picker", "single DOWN (" + gap + ") — waiting up to 3000 ms for a second press");
            this.lastLoneDownMs = now;
            if (canOpenPicker()) {
                this.downHintUntilMs = 3000 + now;
                wakeFrameLoop();
            }
        }
        if (code == 21 && g.getActivate()) {
            this.downHintUntilMs = 0L;
        }
        if (g.getPlainTap()) {
            this.zoomBeforeRightTap = this.zoom;
        }
        boolean activate = g.getActivate();
        boolean undoPrevTap = g.getUndoPrevTap();
        String gesture = code == 21 ? "Double DOWN" : "Double-tap";
        if (this.pickerItems == null) {
            if (!activate) {
                return null;
            }
            if (!canOpenPicker()) {
                RideDiagnostics.INSTANCE.log("picker", gesture + " ignored — not streaming to the dash");
                return null;
            }
            if (undoPrevTap && this.zoom != this.zoomBeforeRightTap) {
                setZoom(this.zoomBeforeRightTap);
            }
            openPicker();
            wakeFrameLoop();
            return gesture + " → open destination picker";
        }
        this.pickerLastInputMs = now;
        this.pickerInputAtMs = now;
        wakeFrameLoop();
        switch (code) {
            case 7:
            case 21:
                this.pickerIndex = nextMenuIndex(menuRows(), this.pickerIndex);
                return "Picker down → " + this.pickerIndex;
            case 19:
                return "Picker RIGHT → " + selectPickerItem();
            case 20:
                if (this.menuPage != MenuPage.ROOT) {
                    this.pickerIndex = (this.menuPage == MenuPage.CANCEL && hasDestination()) ? 1 : 0;
                    this.menuPage = MenuPage.ROOT;
                    return "Picker back → menu";
                }
                closePicker("cancelled (LEFT)");
                return "Picker cancelled";
            default:
                String format = String.format("Picker: ignored 0x%02X", Arrays.copyOf(new Object[]{Integer.valueOf(code)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                return format;
        }
    }

    private final boolean canOpenPicker() {
        return this.session.getState().getValue() == DashState.STREAMING || this.debugInjecting;
    }

    private final void openPicker() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashViewModel$openPicker$1(this, null), 3, null);
    }

    private final void closePicker(String reason) {
        if (this.pickerItems == null) {
            return;
        }
        this.pickerItems = null;
        this.menuPage = MenuPage.ROOT;
        RideDiagnostics.INSTANCE.log("picker", "closed: " + reason);
    }

    private final boolean hasDestination() {
        return (this.destLat == null || this.destLng == null) ? false : true;
    }

    private final List<SharedLocation> stopsAhead() {
        return CollectionsKt.drop(this.routeStops, this.activeStopIndex);
    }

    private final boolean canSkipStop() {
        return hasDestination() && stopsAhead().size() >= 2;
    }

    private final int rootFixedRows() {
        return hasDestination() ? 2 : 1;
    }

    private final List<MapRenderer.MenuRow> menuRows() {
        switch (WhenMappings.$EnumSwitchMapping$0[this.menuPage.ordinal()]) {
            case 1:
                List plus = CollectionsKt.plus((Collection) CollectionsKt.listOf(new MapRenderer.MenuRow("Rota kaynağı", false, false, true, 6, null)), (Iterable) (hasDestination() ? CollectionsKt.listOf(new MapRenderer.MenuRow("Navigasyon", false, false, true, 6, null)) : CollectionsKt.emptyList()));
                Iterable iterable = this.pickerItems;
                if (iterable == null) {
                    iterable = CollectionsKt.emptyList();
                }
                Iterable iterable2 = iterable;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                Iterator it = iterable2.iterator();
                while (it.hasNext()) {
                    arrayList.add(new MapRenderer.MenuRow(((SavedLocation) it.next()).getName(), false, false, false, 14, null));
                }
                return CollectionsKt.plus((Collection) plus, arrayList);
            case 2:
                Iterable cancelRows = cancelRows();
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(cancelRows, 10));
                Iterator it2 = cancelRows.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new MapRenderer.MenuRow((String) ((Pair) it2.next()).getFirst(), false, false, false, 14, null));
                }
                return (List) arrayList2;
            case 3:
                RoutingSource routing = this.dashConfig.getRoutingSource().getValue();
                List listOf = CollectionsKt.listOf(new MapRenderer.MenuRow("Rota kaynağı", true, false, false, 12, null));
                Iterable<Pair> iterable3 = MENU_ROUTING_SOURCES;
                Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                for (Pair pair : iterable3) {
                    arrayList3.add(new MapRenderer.MenuRow((String) pair.component2(), false, ((RoutingSource) pair.component1()) == routing, false, 10, null));
                }
                return CollectionsKt.plus((Collection) listOf, arrayList3);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final List<Pair<String, String>> cancelRows() {
        List createListBuilder = CollectionsKt.createListBuilder();
        if (canSkipStop()) {
            createListBuilder.add(TuplesKt.to("Sonraki durağı atla: " + ((SharedLocation) CollectionsKt.first((List) stopsAhead())).getName(), "skip"));
        }
        if (hasDestination()) {
            createListBuilder.add(TuplesKt.to("Navigasyonu durdur", "end"));
        }
        return CollectionsKt.build(createListBuilder);
    }

    private final int nextMenuIndex(List<MapRenderer.MenuRow> rows, int from) {
        int i = from;
        int size = rows.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = (i + 1) % rows.size();
            if (!rows.get(i).getHeader()) {
                return i;
            }
        }
        return from;
    }

    private final String selectPickerItem() {
        List items = this.pickerItems;
        if (items == null) {
            return "Picker: nothing to select";
        }
        if (this.menuPage == MenuPage.SOURCE) {
            int routeAt = this.pickerIndex - 1;
            Pair pair = (Pair) CollectionsKt.getOrNull(MENU_ROUTING_SOURCES, routeAt);
            if (pair == null) {
                return "Picker: nothing to select";
            }
            RoutingSource source = (RoutingSource) pair.component1();
            String label = (String) pair.component2();
            this.dashConfig.setRoutingSource(source);
            RideDiagnostics.INSTANCE.log("picker", "routing source → " + label + " (from the dash; used from the next route/reroute)");
            return "Picker → routing source " + label;
        }
        if (this.menuPage == MenuPage.CANCEL) {
            Pair pair2 = (Pair) CollectionsKt.getOrNull(cancelRows(), this.pickerIndex);
            String str = pair2 != null ? (String) pair2.getSecond() : null;
            if (Intrinsics.areEqual(str, "skip")) {
                List ahead = stopsAhead();
                closePicker("skipped stop " + ((SharedLocation) CollectionsKt.first(ahead)).getName());
                RideDiagnostics.INSTANCE.log("picker", "cancel next stop " + ((SharedLocation) CollectionsKt.first(ahead)).getName() + " → routing to " + ((SharedLocation) CollectionsKt.first(CollectionsKt.drop(ahead, 1))).getName() + " (from the dash)");
                setDestinations$default(this, CollectionsKt.drop(ahead, 1), null, 2, null);
                showNotice("Atlandı: " + ((SharedLocation) CollectionsKt.first(ahead)).getName(), "→ " + ((SharedLocation) CollectionsKt.first(CollectionsKt.drop(ahead, 1))).getName());
                return "Picker → skipped " + ((SharedLocation) CollectionsKt.first(ahead)).getName();
            }
            if (Intrinsics.areEqual(str, "end")) {
                closePicker("navigation cancelled");
                RideDiagnostics.INSTANCE.log("picker", "navigation cancelled (from the dash)");
                exitNavigation();
                showNotice("Navigasyon durduruldu.");
                return "Picker → navigation cancelled";
            }
            this.menuPage = MenuPage.ROOT;
            this.pickerIndex = 0;
            return "Picker: nothing to cancel";
        }
        if (this.pickerIndex == 0) {
            this.menuPage = MenuPage.SOURCE;
            this.pickerIndex = 1;
            return "Picker → Source page";
        }
        if (this.pickerIndex != 1 || !hasDestination()) {
            SavedLocation loc = (SavedLocation) CollectionsKt.getOrNull(items, this.pickerIndex - rootFixedRows());
            if (loc == null) {
                closePicker("cancel selected");
                return "Picker cancelled";
            }
            closePicker("selected " + loc.getName());
            List<SharedLocation> waypoints = loc.getWaypoints();
            if (waypoints.isEmpty()) {
                waypoints = CollectionsKt.listOf(new SharedLocation(loc.getName(), Double.valueOf(loc.getLat()), Double.valueOf(loc.getLng()), null, false, 24, null));
            }
            setDestinations$default(this, waypoints, null, 2, null);
            return "Picker → navigate to " + loc.getName();
        }
        this.menuPage = MenuPage.CANCEL;
        this.pickerIndex = 0;
        return "Picker → Cancel page";
    }

    private final int zoomInCode() {
        return this.dashConfig.getInvertZoom().getValue().booleanValue() ? 19 : 20;
    }

    private final int zoomOutCode() {
        return this.dashConfig.getInvertZoom().getValue().booleanValue() ? 20 : 19;
    }

    public final void zoomIn() {
        setZoom(this.zoom + 1);
    }

    public final void zoomOut() {
        setZoom(this.zoom - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IntRange zoomRange() {
        return new IntRange(11, 20);
    }

    private final void setZoom(int z) {
        DashUiState value;
        int clamped = RangesKt.coerceIn(z, (ClosedRange<Integer>) zoomRange());
        if (clamped == this.zoom) {
            return;
        }
        this.zoom = clamped;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, null, null, 0, this.zoom, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -257, null)));
        this.lastSignature = "";
        this.zoomBadgeUntilMs = System.currentTimeMillis() + 3000;
        wakeFrameLoop();
        this.zoomPressedAtMs = System.currentTimeMillis();
        if (this.camInit) {
            TileProvider.prefetchZoom$default(this.tiles, this.camLat, this.camLng, this.zoom, 0, 8, null);
        }
    }

    public final void panBy(float dx, float dy) {
        manualPan(dx, dy);
    }

    public final void recenter() {
        DashUiState value;
        this.panX = 0.0f;
        this.panY = 0.0f;
        this.followMode = true;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, true, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -1048577, null)));
    }

    public final void toggleHeadingUp() {
        DashUiState value;
        this.headingUp = !this.headingUp;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, this.headingUp, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -131073, null)));
    }

    public final void setMapDark(boolean dark) {
        this.dashConfig.setMapDarkMode(dark);
        this.dashConfig.setMapDarkAuto(false);
    }

    public final void setMapDarkAuto(boolean auto) {
        this.dashConfig.setMapDarkAuto(auto);
    }

    private final void manualPan(float dx, float dy) {
        DashUiState value;
        this.panX += dx;
        this.panY += dy;
        this.followMode = false;
        this.lastManualPanAt = System.currentTimeMillis();
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -1048577, null)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startStream() {
        Job launch$default;
        RideDiagnostics.INSTANCE.log("stream", "startStream — encoder up, RTP→dash beginning");
        this.videoPtsMs = 0L;
        final RtpPacketizer packetizer = new RtpPacketizer(new Function1() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashViewModel.startStream$lambda$35(DashViewModel.this, (byte[]) obj);
            }
        });
        final NalProcessor nalProc = new NalProcessor(new Function2() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return DashViewModel.startStream$lambda$36(RtpPacketizer.this, this, (byte[]) obj, ((Boolean) obj2).booleanValue());
            }
        });
        Function2 onEncoded = new Function2() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return DashViewModel.startStream$lambda$38(NalProcessor.this, this, (byte[]) obj, ((Boolean) obj2).booleanValue());
            }
        };
        DashEncoder dashEncoder = this.encoder;
        if (dashEncoder != null) {
            dashEncoder.release();
        }
        DashEncoder dashEncoder2 = new DashEncoder(onEncoded);
        dashEncoder2.prepare();
        this.encoder = dashEncoder2;
        Job job = this.tickerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.tickerJob = null;
        this.frameBitmap = newFrameBitmap();
        this.lastSignature = "";
        this.camInit = false;
        this.lastTickNs = 0L;
        this.lastFixTime = 0L;
        this.heldRider = null;
        this.session.startStreaming();
        startMediaForwarding();
        Location value = this.location.getLocation().getValue();
        if (value != null) {
            TileProvider.prefetch$default(this.tiles, value.getLatitude(), value.getLongitude(), null, null, 12, null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getDefault(), null, new DashViewModel$startStream$3(this, onEncoded, null), 2, null);
        this.streamJob = launch$default;
    }

    static final Unit startStream$lambda$35(DashViewModel this$0, byte[] rtpPkt) {
        Intrinsics.checkNotNullParameter(rtpPkt, "rtpPkt");
        this$0.session.sendRtp(rtpPkt);
        return Unit.INSTANCE;
    }

    static final Unit startStream$lambda$36(RtpPacketizer $packetizer, DashViewModel this$0, byte[] nal, boolean z) {
        Intrinsics.checkNotNullParameter(nal, "nal");
        $packetizer.packetize(nal, true, this$0.videoPtsMs);
        return Unit.INSTANCE;
    }

    static final Unit startStream$lambda$38(NalProcessor $nalProc, DashViewModel this$0, byte[] annexB, boolean isKey) {
        long j;
        DashUiState value;
        DashUiState dashUiState;
        Intrinsics.checkNotNullParameter(annexB, "annexB");
        $nalProc.process(annexB);
        if (!this$0.loggedFirstFrame) {
            this$0.loggedFirstFrame = true;
            RideDiagnostics.INSTANCE.log("stream", "first video frame sent (key=" + isKey + ", " + annexB.length + "B)");
        }
        if (annexB.length > BIG_FRAME_BYTES) {
            RideDiagnostics.INSTANCE.log("stream", "big frame: " + (annexB.length / 1024) + " KB (key=" + isKey + ") — budget is ~6 KB/frame");
        }
        long pickAt = this$0.pickerInputAtMs;
        if (pickAt <= 0) {
            j = 0;
        } else {
            this$0.pickerInputAtMs = 0L;
            j = 0;
            RideDiagnostics.INSTANCE.log("picker", "list change → frame sent in " + (System.currentTimeMillis() - pickAt) + " ms (key=" + isKey + ", " + annexB.length + "B)");
        }
        long zoomAt = this$0.zoomPressedAtMs;
        if (zoomAt > j) {
            this$0.zoomPressedAtMs = j;
            RideDiagnostics.INSTANCE.log("button", "zoom → first frame sent in " + (System.currentTimeMillis() - zoomAt) + " ms (key=" + isKey + ", " + annexB.length + "B)");
        }
        MutableStateFlow mutableStateFlow = this$0._ui;
        do {
            value = mutableStateFlow.getValue();
            dashUiState = value;
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, null, dashUiState.getFrameCount() + 1, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -3, null)));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0575, code lost:
    
        if (r0 == null) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0617, code lost:
    
        if (r8 == null) goto L206;
     */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x06a2  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x06be  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x06df  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x06ee  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x06f3  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x06e4  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x070f  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0722  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0755  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x082f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0847 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x085f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x086e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0885  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x08a5  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x08b2  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x08ca  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x08de  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0925  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0931  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x093f  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0948  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0959  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x09b7  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x09cd  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x09ed  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0a01  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0a1e  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0a33  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0a6e  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0ab8  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0acf  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0ad8  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0aef  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0b53 A[LOOP:1: B:273:0x0aab->B:286:0x0b53, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0b4f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0af6  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0aeb  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0ad2  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0abb  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0a8f  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0a20  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0a06  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x09f6  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x09e7  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x09bb  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x095c  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x094f  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0942  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0938  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x092c  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x08ee  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x08d6  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x08aa  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x088b  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x084a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0832 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x075c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0717  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0694  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0469 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:348:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0240  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean tick() {
        Double d;
        boolean z;
        Object obj;
        float normalizeHeading;
        Location location;
        String str;
        double d2;
        Route route;
        Location location2;
        DashViewModel dashViewModel;
        float f;
        boolean z2;
        boolean z3;
        long currentTimeMillis;
        Double d3;
        Double d4;
        Double d5;
        String str2;
        Location location3;
        String str3;
        Pair<Double, Double> pair;
        float f2;
        DashViewModel dashViewModel2;
        float accuracy;
        long j;
        double d6;
        float normalizeHeading2;
        float normalizeHeading3;
        IncomingCall value;
        String sb;
        long j2;
        double d7;
        double d8;
        MutableStateFlow<DashUiState> mutableStateFlow;
        DashUiState value2;
        DashUiState dashUiState;
        boolean z4;
        GpsStatus gpsStatus;
        Double d9;
        Double d10;
        float f3;
        Double valueOf;
        int i;
        Integer num;
        boolean z5;
        String caller;
        String str4;
        String str5;
        String str6;
        long j3;
        long j4;
        boolean z6;
        boolean z7;
        boolean z8;
        String str7;
        String str8;
        Maneuver secondManeuver;
        Triple triple;
        Object m8392constructorimpl;
        double d11;
        double d12;
        long j5;
        DashUiState value3;
        if (!this.followMode && System.currentTimeMillis() - this.lastManualPanAt > MANUAL_IDLE_MS) {
            this.panX = 0.0f;
            this.panY = 0.0f;
            this.followMode = true;
            MutableStateFlow<DashUiState> mutableStateFlow2 = this._ui;
            do {
                value3 = mutableStateFlow2.getValue();
            } while (!mutableStateFlow2.compareAndSet(value3, DashUiState.copy$default(value3, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, true, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -1048577, null)));
        }
        Location value4 = this.location.getLocation().getValue();
        Route route2 = this.route;
        Double d13 = this.destLat;
        Double d14 = this.destLng;
        if (value4 != null) {
            if ((value4.hasAccuracy() ? value4 : null) != null) {
                d = Double.valueOf(r0.getAccuracy());
                Double d15 = d;
                z = value4 == null && value4.hasBearing() && value4.getSpeed() >= BEARING_MIN_SPEED && (d15 == null || d15.doubleValue() <= WAYPOINT_ARRIVE_M);
                this.matchedLat = value4 == null ? Double.valueOf(value4.getLatitude()) : null;
                this.matchedLng = value4 == null ? Double.valueOf(value4.getLongitude()) : null;
                Double d16 = null;
                obj = null;
                if (z) {
                    normalizeHeading = this.camInit ? normalizeHeading(this.camHdg) : 0.0f;
                } else {
                    Intrinsics.checkNotNull(value4);
                    normalizeHeading = normalizeHeading(value4.getBearing());
                }
                float f4 = normalizeHeading;
                boolean z9 = !this.userWantsConnection && this.session.getState().getValue() == DashState.STREAMING;
                if (route2 != null || value4 == null) {
                    location = value4;
                    str = "format(...)";
                    d2 = 0.0d;
                    route = route2;
                } else {
                    location = value4;
                    if (LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.INSTANCE, value4, 0L, 0L, 6, null)) {
                        NavState trackProgress = trackProgress(route2, new GeoPoint(location.getLatitude(), location.getLongitude()), z ? Float.valueOf(location.getBearing()) : null, location.hasSpeed() ? location.getSpeed() : 0.0d, z9);
                        if (!z) {
                            f4 = normalizeHeading(trackProgress.getHeading());
                        }
                        if (z9 && advanceWaypointIfReached(location, trackProgress)) {
                            this.smoothEtaSec = 0.0d;
                            j3 = 0;
                            this.etaArrivalMs = 0L;
                            this.displayedEtaMin = null;
                        } else {
                            j3 = 0;
                        }
                        Double valueOf2 = Double.valueOf(trackProgress.getRemainingM());
                        if (trackProgress.getSnapDist() <= WAYPOINT_ARRIVE_M) {
                            this.matchedLat = Double.valueOf(trackProgress.getSnapped().getLat());
                            this.matchedLng = Double.valueOf(trackProgress.getSnapped().getLng());
                        }
                        this.lastSnapDist = trackProgress.getSnapDist();
                        boolean z10 = z && angleDelta(location.getBearing(), trackProgress.getHeading()) > 50.0f;
                        if (z9) {
                            d2 = 0.0d;
                            j4 = j3;
                            if (NavigationReliability.INSTANCE.isConfidentlyOffRoute(trackProgress.getSnapDist(), d15, z, z10)) {
                                z6 = true;
                                z7 = z6;
                                long currentTimeMillis2 = System.currentTimeMillis();
                                if (z7) {
                                    this.offRouteStreakStart = j4;
                                } else if (this.offRouteStreakStart == j4) {
                                    this.offRouteStreakStart = currentTimeMillis2;
                                }
                                z8 = !z9 && (this.rerouting || (this.offRouteStreakStart != j4 && currentTimeMillis2 - this.offRouteStreakStart > 800));
                                if (z8) {
                                    this.recalcHoldUntil = 1500 + currentTimeMillis2;
                                }
                                boolean z11 = !z9 && (z8 || currentTimeMillis2 < this.recalcHoldUntil);
                                if (!z9 && !z11) {
                                    if (location.getSpeed() > 0.5f) {
                                        this.smoothSpeedMps = this.smoothSpeedMps <= d2 ? location.getSpeed() : this.smoothSpeedMps + ((location.getSpeed() - this.smoothSpeedMps) * 0.05d);
                                    }
                                    double remainingM = (route2.getTotalMeters() <= d2 || route2.getTotalSeconds() <= d2) ? trackProgress.getRemainingM() / RangesKt.coerceAtLeast(this.smoothSpeedMps, 8.0d) : route2.getTotalSeconds() * RangesKt.coerceIn(trackProgress.getRemainingM() / route2.getTotalMeters(), 0.0d, 1.0d);
                                    if (this.smoothEtaSec <= d2) {
                                        d11 = remainingM;
                                    } else if (route2 != this.etaRoute) {
                                        d11 = remainingM;
                                    } else {
                                        d12 = this.smoothEtaSec + ((remainingM - this.smoothEtaSec) * 0.08d);
                                        this.smoothEtaSec = d12;
                                        if (route2 == this.etaRoute) {
                                            this.etaRoute = route2;
                                            j5 = 0;
                                            this.etaArrivalMs = 0L;
                                        } else {
                                            j5 = 0;
                                        }
                                        if (this.etaArrivalMs != j5 || currentTimeMillis2 - this.lastArrivalCalcMs > CoroutineLiveDataKt.DEFAULT_TIMEOUT) {
                                            this.etaArrivalMs = ((long) (this.smoothEtaSec * 1000.0d)) + currentTimeMillis2;
                                            this.displayedEtaMin = Integer.valueOf((int) Math.round(this.smoothEtaSec / 60.0d));
                                            this.lastArrivalCalcMs = currentTimeMillis2;
                                        }
                                    }
                                    d12 = d11;
                                    this.smoothEtaSec = d12;
                                    if (route2 == this.etaRoute) {
                                    }
                                    if (this.etaArrivalMs != j5) {
                                    }
                                    this.etaArrivalMs = ((long) (this.smoothEtaSec * 1000.0d)) + currentTimeMillis2;
                                    this.displayedEtaMin = Integer.valueOf((int) Math.round(this.smoothEtaSec / 60.0d));
                                    this.lastArrivalCalcMs = currentTimeMillis2;
                                }
                                obj = this.smoothEtaSec <= d2 ? Double.valueOf(this.smoothEtaSec) : null;
                                Pair<Integer, Integer> dashDistance = toDashDistance(trackProgress.getNextTurnM());
                                int intValue = dashDistance.component1().intValue();
                                int intValue2 = dashDistance.component2().intValue();
                                Pair<Integer, Integer> dashDistance2 = toDashDistance(trackProgress.getRemainingM());
                                int intValue3 = dashDistance2.component1().intValue();
                                int intValue4 = dashDistance2.component2().intValue();
                                if (obj == null) {
                                    double doubleValue = ((Number) obj).doubleValue();
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(13, (int) doubleValue);
                                    String format = String.format("%02d%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12))}, 2));
                                    str7 = "format(...)";
                                    Intrinsics.checkNotNullExpressionValue(format, str7);
                                    str8 = format;
                                } else {
                                    str7 = "format(...)";
                                    str8 = null;
                                }
                                Maneuver nextManeuver = trackProgress.getNextManeuver();
                                int dashCode = nextManeuver == null ? nextManeuver.getDashCode() : 0;
                                Maneuver nextManeuver2 = trackProgress.getNextManeuver();
                                secondManeuver = trackProgress.getSecondManeuver();
                                Double valueOf3 = (nextManeuver2 != null || secondManeuver == null) ? null : Double.valueOf(secondManeuver.getCumulativeMeters() - nextManeuver2.getCumulativeMeters());
                                if (secondManeuver != null || valueOf3 == null || valueOf3.doubleValue() > 500.0d) {
                                    route = route2;
                                    str = str7;
                                    triple = new Triple(255, 0, 48);
                                } else {
                                    str = str7;
                                    Pair<Integer, Integer> dashDistance3 = toDashDistance(valueOf3.doubleValue());
                                    route = route2;
                                    triple = new Triple(Integer.valueOf(secondManeuver.getDashCode()), Integer.valueOf(dashDistance3.component1().intValue()), Integer.valueOf(dashDistance3.component2().intValue()));
                                }
                                int intValue5 = ((Number) triple.component1()).intValue();
                                int intValue6 = ((Number) triple.component2()).intValue();
                                int intValue7 = ((Number) triple.component3()).intValue();
                                if (z9) {
                                    this.session.updateNavInfo(dashCode, intValue, intValue2, intValue3, intValue4, str8, intValue5, intValue6, intValue7);
                                }
                                if (z9) {
                                    try {
                                        Result.Companion companion = Result.INSTANCE;
                                        this.voice.maybeAnnounce(trackProgress.getNextManeuver(), trackProgress.getNextTurnM(), trackProgress.getRemainingM());
                                        m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
                                    } catch (Throwable th) {
                                        Result.Companion companion2 = Result.INSTANCE;
                                        m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                                    }
                                    Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
                                    if (m8395exceptionOrNullimpl != null) {
                                        Log.w("DashViewModel", "Voice guidance failed: " + m8395exceptionOrNullimpl.getMessage());
                                    }
                                }
                                location2 = location;
                                f = f4;
                                z2 = z7;
                                z3 = z11;
                                d16 = valueOf2;
                                dashViewModel = this;
                                dashViewModel.maybeReroute(z2, location2);
                                dashViewModel.maybeRefreshTraffic(location2);
                                currentTimeMillis = System.currentTimeMillis();
                                String str9 = "";
                                if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
                                    dashViewModel.lastDiagMs = currentTimeMillis;
                                    RideDiagnostics rideDiagnostics = RideDiagnostics.INSTANCE;
                                    if (location2 != null) {
                                        Location location4 = location2;
                                        d3 = d16;
                                        str4 = String.format("acc=%.0fm spd=%.1fm/s (%.0fkm/h)", Arrays.copyOf(new Object[]{Float.valueOf(location4.getAccuracy()), Float.valueOf(location4.getSpeed()), Double.valueOf(location4.getSpeed() * 3.6d)}, 3));
                                        str2 = str;
                                        Intrinsics.checkNotNullExpressionValue(str4, str2);
                                    } else {
                                        d3 = d16;
                                        str2 = str;
                                    }
                                    str4 = "none";
                                    String format2 = String.format(" ridden=%.2fkm", Arrays.copyOf(new Object[]{Double.valueOf(dashViewModel.recorder.getDistanceM() / 1000.0d)}, 1));
                                    Intrinsics.checkNotNullExpressionValue(format2, str2);
                                    String format3 = String.format(" fixGap=%.0fms", Arrays.copyOf(new Object[]{Double.valueOf(dashViewModel.gpsFixIntervalMs)}, 1));
                                    Intrinsics.checkNotNullExpressionValue(format3, str2);
                                    int frameCount = dashViewModel._ui.getValue().getFrameCount();
                                    String thermal = dashViewModel._ui.getValue().getThermal();
                                    d4 = d13;
                                    d5 = d14;
                                    String format4 = String.format(" pace=%.0fms/max=%dms", Arrays.copyOf(new Object[]{Double.valueOf(dashViewModel.sendPaceMs), Long.valueOf(dashViewModel.sendPaceMaxMs)}, 2));
                                    Intrinsics.checkNotNullExpressionValue(format4, str2);
                                    if (d3 != null) {
                                        location3 = location2;
                                        str5 = String.format("%.1fkm", Arrays.copyOf(new Object[]{Double.valueOf(d3.doubleValue() / 1000.0d)}, 1));
                                        Intrinsics.checkNotNullExpressionValue(str5, str2);
                                    } else {
                                        location3 = location2;
                                    }
                                    str5 = "-";
                                    if (dashViewModel.lastSnapDist >= d2) {
                                        str6 = String.format(" snap=%.0fm", Arrays.copyOf(new Object[]{Double.valueOf(dashViewModel.lastSnapDist)}, 1));
                                        Intrinsics.checkNotNullExpressionValue(str6, str2);
                                    } else {
                                        str6 = "";
                                    }
                                    rideDiagnostics.log("ride", "gps=" + str4 + format2 + format3 + " frames=" + frameCount + " thermal=" + thermal + format4 + " remaining=" + str5 + " offRoute=" + z2 + str6);
                                    dashViewModel.sendPaceMaxMs = 0L;
                                } else {
                                    d3 = d16;
                                    d4 = d13;
                                    d5 = d14;
                                    str2 = str;
                                    location3 = location2;
                                }
                                if (location3 != null && location3.getTime() != dashViewModel.lastFixTime) {
                                    dashViewModel.lastFixTime = location3.getTime();
                                    long currentTimeMillis3 = System.currentTimeMillis();
                                    if (dashViewModel.fixWallMs > 0) {
                                        double d17 = currentTimeMillis3 - dashViewModel.fixWallMs;
                                        dashViewModel.gpsFixIntervalMs = dashViewModel.gpsFixIntervalMs <= d2 ? d17 : dashViewModel.gpsFixIntervalMs + ((d17 - dashViewModel.gpsFixIntervalMs) * 0.3d);
                                    }
                                    Double d18 = dashViewModel.matchedLat;
                                    dashViewModel.fixLat = d18 == null ? d18.doubleValue() : location3.getLatitude();
                                    Double d19 = dashViewModel.matchedLng;
                                    dashViewModel.fixLng = d19 == null ? d19.doubleValue() : location3.getLongitude();
                                    dashViewModel.fixBearing = location3.getBearing();
                                    dashViewModel.fixSpeed = location3.getSpeed();
                                    dashViewModel.fixWallMs = currentTimeMillis3;
                                }
                                long currentTimeMillis4 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
                                dashViewModel.gpsStatus = (location3 != null || currentTimeMillis4 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis4 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
                                double d20 = DISPLAY_LEAD_S;
                                if (location3 == null) {
                                    f2 = 25.0f;
                                    str3 = str2;
                                    dashViewModel2 = dashViewModel;
                                    pair = null;
                                } else if (dashViewModel.fixSpeed > 0.5f) {
                                    double predictedDistance = dashViewModel.predictedDistance(dashViewModel.fixSpeed, ((System.currentTimeMillis() - dashViewModel.fixWallMs) / 1000.0d) + DISPLAY_LEAD_S) * RangesKt.coerceIn((50.0f - location3.getAccuracy()) / 25.0f, 0.0d, 1.0d);
                                    dashViewModel.heldRider = TuplesKt.to(Double.valueOf(dashViewModel.fixLat), Double.valueOf(dashViewModel.fixLng));
                                    String str10 = str2;
                                    dashViewModel2 = dashViewModel;
                                    str3 = str10;
                                    pair = dashViewModel2.project(dashViewModel.fixLat, dashViewModel.fixLng, dashViewModel.fixBearing, predictedDistance);
                                    f2 = 25.0f;
                                } else {
                                    str3 = str2;
                                    DashViewModel dashViewModel3 = dashViewModel;
                                    Double d21 = dashViewModel3.matchedLat;
                                    Intrinsics.checkNotNull(d21);
                                    Double d22 = dashViewModel3.matchedLng;
                                    Intrinsics.checkNotNull(d22);
                                    pair = TuplesKt.to(d21, d22);
                                    Pair<Double, Double> pair2 = dashViewModel3.heldRider;
                                    double coerceIn = RangesKt.coerceIn(location3.getAccuracy(), 8.0f, 30.0f);
                                    if (pair2 != null) {
                                        f2 = 25.0f;
                                        if (GeoPoint.INSTANCE.distMeters(new GeoPoint(pair2.getFirst().doubleValue(), pair2.getSecond().doubleValue()), new GeoPoint(pair.getFirst().doubleValue(), pair.getSecond().doubleValue())) <= coerceIn) {
                                            dashViewModel2 = this;
                                            pair = pair2;
                                        }
                                    } else {
                                        f2 = 25.0f;
                                    }
                                    dashViewModel2 = this;
                                    dashViewModel2.heldRider = pair;
                                }
                                Pair<Double, Double> pair3 = pair;
                                double doubleValue2 = (!dashViewModel2.followMode ? pair3 != null : pair3 != null) ? dashViewModel2.camLat : pair3.getFirst().doubleValue();
                                double doubleValue3 = (!dashViewModel2.followMode ? pair3 != null : pair3 != null) ? dashViewModel2.camLng : pair3.getSecond().doubleValue();
                                double doubleValue4 = (dashViewModel2.camInit && pair3 == null && d4 != null) ? d4.doubleValue() : doubleValue2;
                                double doubleValue5 = (dashViewModel2.camInit && pair3 == null && d5 != null) ? d5.doubleValue() : doubleValue3;
                                long nanoTime = System.nanoTime();
                                double coerceIn2 = dashViewModel2.lastTickNs == 0 ? 0.042d : RangesKt.coerceIn((nanoTime - dashViewModel2.lastTickNs) / 1.0E9d, 0.0d, 0.5d);
                                dashViewModel2.lastTickNs = nanoTime;
                                accuracy = location3 != null ? location3.getAccuracy() : 12.0f;
                                if (accuracy <= f2) {
                                    d20 = accuracy > 12.0f ? 0.30000000000000004d : SMOOTH_TAU;
                                }
                                double d23 = 1.0d;
                                if (dashViewModel2.camInit) {
                                    j = nanoTime;
                                    d23 = 1.0d - Math.exp((-coerceIn2) / d20);
                                } else {
                                    j = nanoTime;
                                }
                                double d24 = d23;
                                if (dashViewModel2.camInit) {
                                    dashViewModel2.camLat += (doubleValue4 - dashViewModel2.camLat) * d24;
                                    dashViewModel2.camLng += (doubleValue5 - dashViewModel2.camLng) * d24;
                                    dashViewModel2.camHdg = dashViewModel2.normalizeHeading(dashViewModel2.camHdg + (((float) d24) * (((((f - dashViewModel2.camHdg) % 360.0f) + 540.0f) % 360.0f) - 180.0f)));
                                } else {
                                    dashViewModel2.camLat = doubleValue4;
                                    dashViewModel2.camLng = doubleValue5;
                                    dashViewModel2.camHdg = dashViewModel2.normalizeHeading(f);
                                    dashViewModel2.camInit = true;
                                }
                                dashViewModel2.frameRiderLat = pair3 != null ? Double.valueOf(dashViewModel2.camLat) : null;
                                dashViewModel2.frameRiderLng = pair3 != null ? Double.valueOf(dashViewModel2.camLng) : null;
                                double d25 = dashViewModel2.camInit ? dashViewModel2.camLat : d2;
                                if (dashViewModel2.camInit) {
                                    d6 = d25;
                                    d2 = dashViewModel2.camLng;
                                } else {
                                    d6 = d25;
                                }
                                normalizeHeading2 = dashViewModel2.normalizeHeading(f);
                                normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
                                StringBuilder sb2 = new StringBuilder();
                                String format5 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d6)}, 1));
                                Intrinsics.checkNotNullExpressionValue(format5, str3);
                                sb2.append(format5);
                                String format6 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d2)}, 1));
                                Intrinsics.checkNotNullExpressionValue(format6, str3);
                                sb2.append(format6);
                                sb2.append(dashViewModel2.zoom);
                                sb2.append((int) dashViewModel2.panX);
                                sb2.append((int) dashViewModel2.panY);
                                sb2.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
                                sb2.append(dashViewModel2.mapDark ? 1 : 0);
                                sb2.append((int) (10.0f * normalizeHeading2));
                                sb2.append(d3 != null ? Integer.valueOf((int) (d3.doubleValue() / 100.0d)).intValue() : -1);
                                sb2.append(route != null ? route.getGeometry().size() : 0);
                                Integer num2 = this.displayedEtaMin;
                                sb2.append(num2 != null ? num2.intValue() : -1);
                                sb2.append(this.etaArrivalMs / 1000);
                                sb2.append(System.currentTimeMillis() / 1000);
                                sb2.append(z3 ? 1 : 0);
                                value = CallInfoProvider.INSTANCE.getIncomingCall().getValue();
                                if (value != null) {
                                    if (!Boolean.valueOf(value.getIncoming()).booleanValue()) {
                                        value = null;
                                    }
                                    if (value != null && (caller = value.getCaller()) != null) {
                                        str9 = caller;
                                    }
                                }
                                sb2.append(str9);
                                sb = sb2.toString();
                                long currentTimeMillis5 = System.currentTimeMillis();
                                boolean z12 = !Intrinsics.areEqual(sb, this.lastSignature);
                                this.lastSignature = sb;
                                this.lastRedrawAt = currentTimeMillis5;
                                if (this.pickerItems != null) {
                                    j2 = currentTimeMillis5;
                                    if (System.currentTimeMillis() - this.pickerLastInputMs > PICKER_TIMEOUT_MS) {
                                        closePicker("timeout");
                                    } else if (!canOpenPicker()) {
                                        closePicker("destination set / not streaming");
                                    }
                                } else {
                                    j2 = currentTimeMillis5;
                                }
                                d7 = d2;
                                d8 = d6;
                                redrawFrame(d8, d7, normalizeHeading3, normalizeHeading2, d3);
                                mutableStateFlow = this._ui;
                                while (true) {
                                    value2 = mutableStateFlow.getValue();
                                    dashUiState = value2;
                                    z4 = location3 != null;
                                    String str11 = sb;
                                    gpsStatus = this.gpsStatus;
                                    d9 = this.matchedLat;
                                    d10 = this.matchedLng;
                                    f3 = this.headingUp ? normalizeHeading3 : 0.0f;
                                    int i2 = this.zoom;
                                    valueOf = d3 != null ? Double.valueOf(d3.doubleValue() / 1000.0d) : null;
                                    if (obj != null) {
                                        i = i2;
                                        num = this.displayedEtaMin;
                                    } else {
                                        i = i2;
                                        num = null;
                                    }
                                    z5 = z2;
                                    if (mutableStateFlow.compareAndSet(value2, DashUiState.copy$default(dashUiState, null, 0, null, null, null, null, null, 0, i, valueOf, num, null, z4, gpsStatus, false, z5, z3, false, false, false, false, null, false, d9, d10, normalizeHeading2, Double.valueOf(d8), Double.valueOf(d7), f3, null, null, null, -528596737, null))) {
                                        updateThermal();
                                        return z12;
                                    }
                                    sb = str11;
                                    z2 = z5;
                                }
                            }
                        } else {
                            d2 = 0.0d;
                            j4 = j3;
                        }
                        z6 = false;
                        z7 = z6;
                        long currentTimeMillis22 = System.currentTimeMillis();
                        if (z7) {
                        }
                        z8 = !z9 && (this.rerouting || (this.offRouteStreakStart != j4 && currentTimeMillis22 - this.offRouteStreakStart > 800));
                        if (z8) {
                        }
                        boolean z112 = !z9 && (z8 || currentTimeMillis22 < this.recalcHoldUntil);
                        if (!z9) {
                        }
                        obj = this.smoothEtaSec <= d2 ? Double.valueOf(this.smoothEtaSec) : null;
                        Pair<Integer, Integer> dashDistance4 = toDashDistance(trackProgress.getNextTurnM());
                        int intValue8 = dashDistance4.component1().intValue();
                        int intValue22 = dashDistance4.component2().intValue();
                        Pair<Integer, Integer> dashDistance22 = toDashDistance(trackProgress.getRemainingM());
                        int intValue32 = dashDistance22.component1().intValue();
                        int intValue42 = dashDistance22.component2().intValue();
                        if (obj == null) {
                        }
                        Maneuver nextManeuver3 = trackProgress.getNextManeuver();
                        if (nextManeuver3 == null) {
                        }
                        Maneuver nextManeuver22 = trackProgress.getNextManeuver();
                        secondManeuver = trackProgress.getSecondManeuver();
                        Double valueOf32 = (nextManeuver22 != null || secondManeuver == null) ? null : Double.valueOf(secondManeuver.getCumulativeMeters() - nextManeuver22.getCumulativeMeters());
                        if (secondManeuver != null) {
                        }
                        route = route2;
                        str = str7;
                        triple = new Triple(255, 0, 48);
                        int intValue52 = ((Number) triple.component1()).intValue();
                        int intValue62 = ((Number) triple.component2()).intValue();
                        int intValue72 = ((Number) triple.component3()).intValue();
                        if (z9) {
                        }
                        if (z9) {
                        }
                        location2 = location;
                        f = f4;
                        z2 = z7;
                        z3 = z112;
                        d16 = valueOf2;
                        dashViewModel = this;
                        dashViewModel.maybeReroute(z2, location2);
                        dashViewModel.maybeRefreshTraffic(location2);
                        currentTimeMillis = System.currentTimeMillis();
                        String str92 = "";
                        if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
                        }
                        if (location3 != null) {
                            dashViewModel.lastFixTime = location3.getTime();
                            long currentTimeMillis32 = System.currentTimeMillis();
                            if (dashViewModel.fixWallMs > 0) {
                            }
                            Double d182 = dashViewModel.matchedLat;
                            dashViewModel.fixLat = d182 == null ? d182.doubleValue() : location3.getLatitude();
                            Double d192 = dashViewModel.matchedLng;
                            dashViewModel.fixLng = d192 == null ? d192.doubleValue() : location3.getLongitude();
                            dashViewModel.fixBearing = location3.getBearing();
                            dashViewModel.fixSpeed = location3.getSpeed();
                            dashViewModel.fixWallMs = currentTimeMillis32;
                        }
                        long currentTimeMillis42 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
                        dashViewModel.gpsStatus = (location3 != null || currentTimeMillis42 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis42 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
                        double d202 = DISPLAY_LEAD_S;
                        if (location3 == null) {
                        }
                        Pair<Double, Double> pair32 = pair;
                        double doubleValue22 = (!dashViewModel2.followMode ? pair32 != null : pair32 != null) ? dashViewModel2.camLat : pair32.getFirst().doubleValue();
                        double doubleValue32 = (!dashViewModel2.followMode ? pair32 != null : pair32 != null) ? dashViewModel2.camLng : pair32.getSecond().doubleValue();
                        if (dashViewModel2.camInit) {
                        }
                        if (dashViewModel2.camInit) {
                        }
                        long nanoTime2 = System.nanoTime();
                        if (dashViewModel2.lastTickNs == 0) {
                        }
                        dashViewModel2.lastTickNs = nanoTime2;
                        if (location3 != null) {
                        }
                        if (accuracy <= f2) {
                        }
                        double d232 = 1.0d;
                        if (dashViewModel2.camInit) {
                        }
                        double d242 = d232;
                        if (dashViewModel2.camInit) {
                        }
                        dashViewModel2.frameRiderLat = pair32 != null ? Double.valueOf(dashViewModel2.camLat) : null;
                        dashViewModel2.frameRiderLng = pair32 != null ? Double.valueOf(dashViewModel2.camLng) : null;
                        if (dashViewModel2.camInit) {
                        }
                        if (dashViewModel2.camInit) {
                        }
                        normalizeHeading2 = dashViewModel2.normalizeHeading(f);
                        normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
                        StringBuilder sb22 = new StringBuilder();
                        String format52 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d6)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format52, str3);
                        sb22.append(format52);
                        String format62 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d2)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format62, str3);
                        sb22.append(format62);
                        sb22.append(dashViewModel2.zoom);
                        sb22.append((int) dashViewModel2.panX);
                        sb22.append((int) dashViewModel2.panY);
                        sb22.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
                        sb22.append(dashViewModel2.mapDark ? 1 : 0);
                        sb22.append((int) (10.0f * normalizeHeading2));
                        sb22.append(d3 != null ? Integer.valueOf((int) (d3.doubleValue() / 100.0d)).intValue() : -1);
                        sb22.append(route != null ? route.getGeometry().size() : 0);
                        Integer num22 = this.displayedEtaMin;
                        sb22.append(num22 != null ? num22.intValue() : -1);
                        sb22.append(this.etaArrivalMs / 1000);
                        sb22.append(System.currentTimeMillis() / 1000);
                        sb22.append(z3 ? 1 : 0);
                        value = CallInfoProvider.INSTANCE.getIncomingCall().getValue();
                        if (value != null) {
                        }
                        sb22.append(str92);
                        sb = sb22.toString();
                        long currentTimeMillis52 = System.currentTimeMillis();
                        boolean z122 = !Intrinsics.areEqual(sb, this.lastSignature);
                        this.lastSignature = sb;
                        this.lastRedrawAt = currentTimeMillis52;
                        if (this.pickerItems != null) {
                        }
                        d7 = d2;
                        d8 = d6;
                        redrawFrame(d8, d7, normalizeHeading3, normalizeHeading2, d3);
                        mutableStateFlow = this._ui;
                        while (true) {
                            value2 = mutableStateFlow.getValue();
                            dashUiState = value2;
                            if (location3 != null) {
                            }
                            String str112 = sb;
                            gpsStatus = this.gpsStatus;
                            d9 = this.matchedLat;
                            d10 = this.matchedLng;
                            if (this.headingUp) {
                            }
                            int i22 = this.zoom;
                            if (d3 != null) {
                            }
                            if (obj != null) {
                            }
                            z5 = z2;
                            if (mutableStateFlow.compareAndSet(value2, DashUiState.copy$default(dashUiState, null, 0, null, null, null, null, null, 0, i, valueOf, num, null, z4, gpsStatus, false, z5, z3, false, false, false, false, null, false, d9, d10, normalizeHeading2, Double.valueOf(d8), Double.valueOf(d7), f3, null, null, null, -528596737, null))) {
                            }
                            sb = str112;
                            z2 = z5;
                        }
                    } else {
                        d2 = 0.0d;
                        route = route2;
                        str = "format(...)";
                    }
                }
                if (location != null || d13 == null || d14 == null) {
                    location2 = location;
                    dashViewModel = this;
                } else {
                    Location location5 = location;
                    dashViewModel = this;
                    location2 = location5;
                    if (LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.INSTANCE, location5, 0L, 0L, 6, null)) {
                        d16 = Double.valueOf(GeoPoint.INSTANCE.distMeters(new GeoPoint(location2.getLatitude(), location2.getLongitude()), new GeoPoint(d13.doubleValue(), d14.doubleValue())));
                        f = f4;
                        z2 = false;
                        z3 = false;
                        dashViewModel.maybeReroute(z2, location2);
                        dashViewModel.maybeRefreshTraffic(location2);
                        currentTimeMillis = System.currentTimeMillis();
                        String str922 = "";
                        if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
                        }
                        if (location3 != null) {
                        }
                        long currentTimeMillis422 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
                        dashViewModel.gpsStatus = (location3 != null || currentTimeMillis422 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis422 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
                        double d2022 = DISPLAY_LEAD_S;
                        if (location3 == null) {
                        }
                        Pair<Double, Double> pair322 = pair;
                        double doubleValue222 = (!dashViewModel2.followMode ? pair322 != null : pair322 != null) ? dashViewModel2.camLat : pair322.getFirst().doubleValue();
                        double doubleValue322 = (!dashViewModel2.followMode ? pair322 != null : pair322 != null) ? dashViewModel2.camLng : pair322.getSecond().doubleValue();
                        if (dashViewModel2.camInit) {
                        }
                        if (dashViewModel2.camInit) {
                        }
                        long nanoTime22 = System.nanoTime();
                        if (dashViewModel2.lastTickNs == 0) {
                        }
                        dashViewModel2.lastTickNs = nanoTime22;
                        if (location3 != null) {
                        }
                        if (accuracy <= f2) {
                        }
                        double d2322 = 1.0d;
                        if (dashViewModel2.camInit) {
                        }
                        double d2422 = d2322;
                        if (dashViewModel2.camInit) {
                        }
                        dashViewModel2.frameRiderLat = pair322 != null ? Double.valueOf(dashViewModel2.camLat) : null;
                        dashViewModel2.frameRiderLng = pair322 != null ? Double.valueOf(dashViewModel2.camLng) : null;
                        if (dashViewModel2.camInit) {
                        }
                        if (dashViewModel2.camInit) {
                        }
                        normalizeHeading2 = dashViewModel2.normalizeHeading(f);
                        normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
                        StringBuilder sb222 = new StringBuilder();
                        String format522 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d6)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format522, str3);
                        sb222.append(format522);
                        String format622 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d2)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format622, str3);
                        sb222.append(format622);
                        sb222.append(dashViewModel2.zoom);
                        sb222.append((int) dashViewModel2.panX);
                        sb222.append((int) dashViewModel2.panY);
                        sb222.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
                        sb222.append(dashViewModel2.mapDark ? 1 : 0);
                        sb222.append((int) (10.0f * normalizeHeading2));
                        sb222.append(d3 != null ? Integer.valueOf((int) (d3.doubleValue() / 100.0d)).intValue() : -1);
                        sb222.append(route != null ? route.getGeometry().size() : 0);
                        Integer num222 = this.displayedEtaMin;
                        sb222.append(num222 != null ? num222.intValue() : -1);
                        sb222.append(this.etaArrivalMs / 1000);
                        sb222.append(System.currentTimeMillis() / 1000);
                        sb222.append(z3 ? 1 : 0);
                        value = CallInfoProvider.INSTANCE.getIncomingCall().getValue();
                        if (value != null) {
                        }
                        sb222.append(str922);
                        sb = sb222.toString();
                        long currentTimeMillis522 = System.currentTimeMillis();
                        boolean z1222 = !Intrinsics.areEqual(sb, this.lastSignature);
                        this.lastSignature = sb;
                        this.lastRedrawAt = currentTimeMillis522;
                        if (this.pickerItems != null) {
                        }
                        d7 = d2;
                        d8 = d6;
                        redrawFrame(d8, d7, normalizeHeading3, normalizeHeading2, d3);
                        mutableStateFlow = this._ui;
                        while (true) {
                            value2 = mutableStateFlow.getValue();
                            dashUiState = value2;
                            if (location3 != null) {
                            }
                            String str1122 = sb;
                            gpsStatus = this.gpsStatus;
                            d9 = this.matchedLat;
                            d10 = this.matchedLng;
                            if (this.headingUp) {
                            }
                            int i222 = this.zoom;
                            if (d3 != null) {
                            }
                            if (obj != null) {
                            }
                            z5 = z2;
                            if (mutableStateFlow.compareAndSet(value2, DashUiState.copy$default(dashUiState, null, 0, null, null, null, null, null, 0, i, valueOf, num, null, z4, gpsStatus, false, z5, z3, false, false, false, false, null, false, d9, d10, normalizeHeading2, Double.valueOf(d8), Double.valueOf(d7), f3, null, null, null, -528596737, null))) {
                            }
                            sb = str1122;
                            z2 = z5;
                        }
                    }
                }
                f = f4;
                z2 = false;
                z3 = false;
                dashViewModel.maybeReroute(z2, location2);
                dashViewModel.maybeRefreshTraffic(location2);
                currentTimeMillis = System.currentTimeMillis();
                String str9222 = "";
                if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
                }
                if (location3 != null) {
                }
                long currentTimeMillis4222 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
                dashViewModel.gpsStatus = (location3 != null || currentTimeMillis4222 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis4222 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
                double d20222 = DISPLAY_LEAD_S;
                if (location3 == null) {
                }
                Pair<Double, Double> pair3222 = pair;
                double doubleValue2222 = (!dashViewModel2.followMode ? pair3222 != null : pair3222 != null) ? dashViewModel2.camLat : pair3222.getFirst().doubleValue();
                double doubleValue3222 = (!dashViewModel2.followMode ? pair3222 != null : pair3222 != null) ? dashViewModel2.camLng : pair3222.getSecond().doubleValue();
                if (dashViewModel2.camInit) {
                }
                if (dashViewModel2.camInit) {
                }
                long nanoTime222 = System.nanoTime();
                if (dashViewModel2.lastTickNs == 0) {
                }
                dashViewModel2.lastTickNs = nanoTime222;
                if (location3 != null) {
                }
                if (accuracy <= f2) {
                }
                double d23222 = 1.0d;
                if (dashViewModel2.camInit) {
                }
                double d24222 = d23222;
                if (dashViewModel2.camInit) {
                }
                dashViewModel2.frameRiderLat = pair3222 != null ? Double.valueOf(dashViewModel2.camLat) : null;
                dashViewModel2.frameRiderLng = pair3222 != null ? Double.valueOf(dashViewModel2.camLng) : null;
                if (dashViewModel2.camInit) {
                }
                if (dashViewModel2.camInit) {
                }
                normalizeHeading2 = dashViewModel2.normalizeHeading(f);
                normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
                StringBuilder sb2222 = new StringBuilder();
                String format5222 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d6)}, 1));
                Intrinsics.checkNotNullExpressionValue(format5222, str3);
                sb2222.append(format5222);
                String format6222 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d2)}, 1));
                Intrinsics.checkNotNullExpressionValue(format6222, str3);
                sb2222.append(format6222);
                sb2222.append(dashViewModel2.zoom);
                sb2222.append((int) dashViewModel2.panX);
                sb2222.append((int) dashViewModel2.panY);
                sb2222.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
                sb2222.append(dashViewModel2.mapDark ? 1 : 0);
                sb2222.append((int) (10.0f * normalizeHeading2));
                sb2222.append(d3 != null ? Integer.valueOf((int) (d3.doubleValue() / 100.0d)).intValue() : -1);
                sb2222.append(route != null ? route.getGeometry().size() : 0);
                Integer num2222 = this.displayedEtaMin;
                sb2222.append(num2222 != null ? num2222.intValue() : -1);
                sb2222.append(this.etaArrivalMs / 1000);
                sb2222.append(System.currentTimeMillis() / 1000);
                sb2222.append(z3 ? 1 : 0);
                value = CallInfoProvider.INSTANCE.getIncomingCall().getValue();
                if (value != null) {
                }
                sb2222.append(str9222);
                sb = sb2222.toString();
                long currentTimeMillis5222 = System.currentTimeMillis();
                boolean z12222 = !Intrinsics.areEqual(sb, this.lastSignature);
                this.lastSignature = sb;
                this.lastRedrawAt = currentTimeMillis5222;
                if (this.pickerItems != null) {
                }
                d7 = d2;
                d8 = d6;
                redrawFrame(d8, d7, normalizeHeading3, normalizeHeading2, d3);
                mutableStateFlow = this._ui;
                while (true) {
                    value2 = mutableStateFlow.getValue();
                    dashUiState = value2;
                    if (location3 != null) {
                    }
                    String str11222 = sb;
                    gpsStatus = this.gpsStatus;
                    d9 = this.matchedLat;
                    d10 = this.matchedLng;
                    if (this.headingUp) {
                    }
                    int i2222 = this.zoom;
                    if (d3 != null) {
                    }
                    if (obj != null) {
                    }
                    z5 = z2;
                    if (mutableStateFlow.compareAndSet(value2, DashUiState.copy$default(dashUiState, null, 0, null, null, null, null, null, 0, i, valueOf, num, null, z4, gpsStatus, false, z5, z3, false, false, false, false, null, false, d9, d10, normalizeHeading2, Double.valueOf(d8), Double.valueOf(d7), f3, null, null, null, -528596737, null))) {
                    }
                    sb = str11222;
                    z2 = z5;
                }
            }
        }
        d = null;
        Double d152 = d;
        z = value4 == null && value4.hasBearing() && value4.getSpeed() >= BEARING_MIN_SPEED && (d152 == null || d152.doubleValue() <= WAYPOINT_ARRIVE_M);
        this.matchedLat = value4 == null ? Double.valueOf(value4.getLatitude()) : null;
        this.matchedLng = value4 == null ? Double.valueOf(value4.getLongitude()) : null;
        Double d162 = null;
        obj = null;
        if (z) {
        }
        float f42 = normalizeHeading;
        boolean z92 = !this.userWantsConnection && this.session.getState().getValue() == DashState.STREAMING;
        if (route2 != null) {
        }
        location = value4;
        str = "format(...)";
        d2 = 0.0d;
        route = route2;
        if (location != null) {
        }
        location2 = location;
        dashViewModel = this;
        f = f42;
        z2 = false;
        z3 = false;
        dashViewModel.maybeReroute(z2, location2);
        dashViewModel.maybeRefreshTraffic(location2);
        currentTimeMillis = System.currentTimeMillis();
        String str92222 = "";
        if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
        }
        if (location3 != null) {
        }
        long currentTimeMillis42222 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
        dashViewModel.gpsStatus = (location3 != null || currentTimeMillis42222 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis42222 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
        double d202222 = DISPLAY_LEAD_S;
        if (location3 == null) {
        }
        Pair<Double, Double> pair32222 = pair;
        double doubleValue22222 = (!dashViewModel2.followMode ? pair32222 != null : pair32222 != null) ? dashViewModel2.camLat : pair32222.getFirst().doubleValue();
        double doubleValue32222 = (!dashViewModel2.followMode ? pair32222 != null : pair32222 != null) ? dashViewModel2.camLng : pair32222.getSecond().doubleValue();
        if (dashViewModel2.camInit) {
        }
        if (dashViewModel2.camInit) {
        }
        long nanoTime2222 = System.nanoTime();
        if (dashViewModel2.lastTickNs == 0) {
        }
        dashViewModel2.lastTickNs = nanoTime2222;
        if (location3 != null) {
        }
        if (accuracy <= f2) {
        }
        double d232222 = 1.0d;
        if (dashViewModel2.camInit) {
        }
        double d242222 = d232222;
        if (dashViewModel2.camInit) {
        }
        dashViewModel2.frameRiderLat = pair32222 != null ? Double.valueOf(dashViewModel2.camLat) : null;
        dashViewModel2.frameRiderLng = pair32222 != null ? Double.valueOf(dashViewModel2.camLng) : null;
        if (dashViewModel2.camInit) {
        }
        if (dashViewModel2.camInit) {
        }
        normalizeHeading2 = dashViewModel2.normalizeHeading(f);
        normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
        StringBuilder sb22222 = new StringBuilder();
        String format52222 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d6)}, 1));
        Intrinsics.checkNotNullExpressionValue(format52222, str3);
        sb22222.append(format52222);
        String format62222 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d2)}, 1));
        Intrinsics.checkNotNullExpressionValue(format62222, str3);
        sb22222.append(format62222);
        sb22222.append(dashViewModel2.zoom);
        sb22222.append((int) dashViewModel2.panX);
        sb22222.append((int) dashViewModel2.panY);
        sb22222.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
        sb22222.append(dashViewModel2.mapDark ? 1 : 0);
        sb22222.append((int) (10.0f * normalizeHeading2));
        sb22222.append(d3 != null ? Integer.valueOf((int) (d3.doubleValue() / 100.0d)).intValue() : -1);
        sb22222.append(route != null ? route.getGeometry().size() : 0);
        Integer num22222 = this.displayedEtaMin;
        sb22222.append(num22222 != null ? num22222.intValue() : -1);
        sb22222.append(this.etaArrivalMs / 1000);
        sb22222.append(System.currentTimeMillis() / 1000);
        sb22222.append(z3 ? 1 : 0);
        value = CallInfoProvider.INSTANCE.getIncomingCall().getValue();
        if (value != null) {
        }
        sb22222.append(str92222);
        sb = sb22222.toString();
        long currentTimeMillis52222 = System.currentTimeMillis();
        boolean z122222 = !Intrinsics.areEqual(sb, this.lastSignature);
        this.lastSignature = sb;
        this.lastRedrawAt = currentTimeMillis52222;
        if (this.pickerItems != null) {
        }
        d7 = d2;
        d8 = d6;
        redrawFrame(d8, d7, normalizeHeading3, normalizeHeading2, d3);
        mutableStateFlow = this._ui;
        while (true) {
            value2 = mutableStateFlow.getValue();
            dashUiState = value2;
            if (location3 != null) {
            }
            String str112222 = sb;
            gpsStatus = this.gpsStatus;
            d9 = this.matchedLat;
            d10 = this.matchedLng;
            if (this.headingUp) {
            }
            int i22222 = this.zoom;
            if (d3 != null) {
            }
            if (obj != null) {
            }
            z5 = z2;
            if (mutableStateFlow.compareAndSet(value2, DashUiState.copy$default(dashUiState, null, 0, null, null, null, null, null, 0, i, valueOf, num, null, z4, gpsStatus, false, z5, z3, false, false, false, false, null, false, d9, d10, normalizeHeading2, Double.valueOf(d8), Double.valueOf(d7), f3, null, null, null, -528596737, null))) {
            }
            sb = str112222;
            z2 = z5;
        }
    }

    private final void maybeReroute(boolean offRoute, Location loc) {
        Double dLat;
        Double dLng;
        Job launch$default;
        if (!this.userWantsConnection || this.session.getState().getValue() != DashState.STREAMING) {
            this.offRouteSince = 0L;
            this.offRouteConfirmation.reset();
            return;
        }
        SharedLocation finalStop = (SharedLocation) CollectionsKt.lastOrNull((List) this.routeStops);
        if (finalStop == null || (dLat = finalStop.getLat()) == null) {
            dLat = this.destLat;
        }
        if (finalStop == null || (dLng = finalStop.getLng()) == null) {
            dLng = this.destLng;
        }
        long now = System.currentTimeMillis();
        if (offRoute && loc != null && LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.INSTANCE, loc, 0L, 0L, 6, null) && dLat != null) {
            if (dLng != null) {
                if (this.offRouteSince == 0) {
                    this.offRouteSince = now;
                }
                Long valueOf = Long.valueOf(loc.getElapsedRealtimeNanos());
                if (!(valueOf.longValue() > 0)) {
                    valueOf = null;
                }
                long fixToken = valueOf != null ? valueOf.longValue() : loc.getTime();
                if (this.offRouteConfirmation.record(fixToken, now)) {
                    long cooldown = RangesKt.coerceAtMost(REROUTE_BASE_MS << RangesKt.coerceAtMost(this.rerouteStreak, 5), 120000L);
                    if (now - this.lastRerouteAt < cooldown || this.rerouting) {
                        return;
                    }
                    if (!this.trafficRefreshInFlight && takeAutoRouteCall("reroute")) {
                        this.rerouteStreak++;
                        this.lastRerouteAt = now;
                        this.rerouting = true;
                        this.routeRequestGeneration++;
                        long requestId = this.routeRequestGeneration;
                        List waypointSnapshot = CollectionsKt.toList(this.routeWaypoints);
                        int stopIndexSnapshot = this.activeStopIndex;
                        long offSec = (now - this.offRouteSince) / 1000;
                        Log.i("DashViewModel", "Off-route " + offSec + "s → rerouting");
                        Double dLat2 = dLat;
                        RideDiagnostics.INSTANCE.log("reroute", "off-route " + offSec + "s → requesting new route");
                        long startMs = System.currentTimeMillis();
                        Job job = this.routeRequestJob;
                        if (job != null) {
                            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                        }
                        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashViewModel$maybeReroute$1(this, loc, dLat2, dLng, waypointSnapshot, requestId, stopIndexSnapshot, startMs, null), 3, null);
                        this.routeRequestJob = launch$default;
                        return;
                    }
                    return;
                }
                return;
            }
        }
        this.offRouteSince = 0L;
        this.offRouteConfirmation.reset();
        if (this.rerouteStreak > 0 && now - this.lastRerouteAt > REROUTE_STREAK_RESET_MS) {
            this.rerouteStreak = 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0158 A[LOOP:0: B:51:0x0152->B:53:0x0158, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean advanceWaypointIfReached(Location loc, NavState nav) {
        SharedLocation stop;
        Double lat;
        Double accuracy;
        boolean accuracyTrusted;
        boolean z;
        double d;
        boolean closeToStop;
        int index;
        boolean closeToStop2;
        boolean routeMatchTrusted;
        boolean reachedLegEnd;
        Job job;
        Job job2;
        SharedLocation next;
        MutableStateFlow mutableStateFlow;
        DashUiState value;
        String name;
        Double lat2;
        Double lng;
        List<Double> legEndCumulative;
        Double d2;
        List stops = this.routeStops;
        int index2 = this.activeStopIndex;
        if (index2 < CollectionsKt.getLastIndex(stops) && (lat = (stop = stops.get(index2)).getLat()) != null) {
            double lat3 = lat.doubleValue();
            Double lng2 = stop.getLng();
            if (lng2 == null) {
                return false;
            }
            double lng3 = lng2.doubleValue();
            if (loc.hasAccuracy()) {
                if (Math.abs(loc.getAccuracy()) <= Float.MAX_VALUE) {
                    accuracy = Double.valueOf(loc.getAccuracy());
                    accuracyTrusted = accuracy != null || accuracy.doubleValue() <= 60.0d;
                    if (accuracyTrusted) {
                        z = false;
                        d = WAYPOINT_ARRIVE_M;
                    } else {
                        GeoPoint.Companion companion = GeoPoint.INSTANCE;
                        z = false;
                        double latitude = loc.getLatitude();
                        d = WAYPOINT_ARRIVE_M;
                        if (companion.distMeters(new GeoPoint(latitude, loc.getLongitude()), new GeoPoint(lat3, lng3)) <= WAYPOINT_ARRIVE_M) {
                            closeToStop = true;
                            int legIndex = index2 - this.routeLegOffset;
                            if (accuracyTrusted) {
                                index = index2;
                                closeToStop2 = closeToStop;
                                if (nav.getSnapDist() <= Math.max(d, (accuracy != null ? accuracy.doubleValue() : 0.0d) * 1.5d)) {
                                    routeMatchTrusted = true;
                                    if (routeMatchTrusted) {
                                        Route route = this.route;
                                        if ((route == null || (legEndCumulative = route.getLegEndCumulative()) == null || (d2 = (Double) CollectionsKt.getOrNull(legEndCumulative, legIndex)) == null) ? z : nav.getProgressM() >= d2.doubleValue() - WAYPOINT_PROGRESS_TOLERANCE_M ? true : z) {
                                            reachedLegEnd = true;
                                            if (closeToStop2 && !reachedLegEnd) {
                                                return z;
                                            }
                                            this.routeRequestGeneration++;
                                            job = this.routeRequestJob;
                                            if (job != null) {
                                                job2 = null;
                                                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                                            } else {
                                                job2 = null;
                                            }
                                            this.routeRequestJob = job2;
                                            boolean z2 = z;
                                            this.rerouting = z2;
                                            this.trafficRefreshInFlight = z2;
                                            this.activeStopIndex = index + 1;
                                            SharedLocation next2 = stops.get(this.activeStopIndex);
                                            this.destLat = next2.getLat();
                                            this.destLng = next2.getLng();
                                            Iterable<SharedLocation> subList = stops.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(stops));
                                            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList, 10));
                                            for (SharedLocation sharedLocation : subList) {
                                                Double lat4 = sharedLocation.getLat();
                                                Intrinsics.checkNotNull(lat4);
                                                boolean reachedLegEnd2 = reachedLegEnd;
                                                double doubleValue = lat4.doubleValue();
                                                Double lng4 = sharedLocation.getLng();
                                                Intrinsics.checkNotNull(lng4);
                                                arrayList.add(new GeoPoint(doubleValue, lng4.doubleValue()));
                                                routeMatchTrusted = routeMatchTrusted;
                                                reachedLegEnd = reachedLegEnd2;
                                                next2 = next2;
                                                closeToStop2 = closeToStop2;
                                                subList = subList;
                                            }
                                            next = next2;
                                            this.routeWaypoints = (List) arrayList;
                                            this.voice.resetTrip();
                                            this.voice.announceNextStop(next.getName());
                                            this.session.updateRouteCard(next.getName());
                                            mutableStateFlow = this._ui;
                                            do {
                                                value = mutableStateFlow.getValue();
                                                name = next.getName();
                                                lat2 = next.getLat();
                                                Intrinsics.checkNotNull(lat2);
                                                lng = next.getLng();
                                                Intrinsics.checkNotNull(lng);
                                            } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, name, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, TuplesKt.to(lat2, lng), null, null, -536870945, null)));
                                            Log.i("DashViewModel", "Waypoint " + (index + 1) + "/" + stops.size() + " reached; continuing to " + next.getName());
                                            return true;
                                        }
                                    }
                                    reachedLegEnd = z;
                                    if (closeToStop2) {
                                    }
                                    this.routeRequestGeneration++;
                                    job = this.routeRequestJob;
                                    if (job != null) {
                                    }
                                    this.routeRequestJob = job2;
                                    boolean z22 = z;
                                    this.rerouting = z22;
                                    this.trafficRefreshInFlight = z22;
                                    this.activeStopIndex = index + 1;
                                    SharedLocation next22 = stops.get(this.activeStopIndex);
                                    this.destLat = next22.getLat();
                                    this.destLng = next22.getLng();
                                    Iterable<SharedLocation> subList2 = stops.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(stops));
                                    Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList2, 10));
                                    while (r19.hasNext()) {
                                    }
                                    next = next22;
                                    this.routeWaypoints = (List) arrayList2;
                                    this.voice.resetTrip();
                                    this.voice.announceNextStop(next.getName());
                                    this.session.updateRouteCard(next.getName());
                                    mutableStateFlow = this._ui;
                                    do {
                                        value = mutableStateFlow.getValue();
                                        name = next.getName();
                                        lat2 = next.getLat();
                                        Intrinsics.checkNotNull(lat2);
                                        lng = next.getLng();
                                        Intrinsics.checkNotNull(lng);
                                    } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, name, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, TuplesKt.to(lat2, lng), null, null, -536870945, null)));
                                    Log.i("DashViewModel", "Waypoint " + (index + 1) + "/" + stops.size() + " reached; continuing to " + next.getName());
                                    return true;
                                }
                            } else {
                                index = index2;
                                closeToStop2 = closeToStop;
                            }
                            routeMatchTrusted = z;
                            if (routeMatchTrusted) {
                            }
                            reachedLegEnd = z;
                            if (closeToStop2) {
                            }
                            this.routeRequestGeneration++;
                            job = this.routeRequestJob;
                            if (job != null) {
                            }
                            this.routeRequestJob = job2;
                            boolean z222 = z;
                            this.rerouting = z222;
                            this.trafficRefreshInFlight = z222;
                            this.activeStopIndex = index + 1;
                            SharedLocation next222 = stops.get(this.activeStopIndex);
                            this.destLat = next222.getLat();
                            this.destLng = next222.getLng();
                            Iterable<SharedLocation> subList22 = stops.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(stops));
                            Collection arrayList22 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList22, 10));
                            while (r19.hasNext()) {
                            }
                            next = next222;
                            this.routeWaypoints = (List) arrayList22;
                            this.voice.resetTrip();
                            this.voice.announceNextStop(next.getName());
                            this.session.updateRouteCard(next.getName());
                            mutableStateFlow = this._ui;
                            do {
                                value = mutableStateFlow.getValue();
                                name = next.getName();
                                lat2 = next.getLat();
                                Intrinsics.checkNotNull(lat2);
                                lng = next.getLng();
                                Intrinsics.checkNotNull(lng);
                            } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, name, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, TuplesKt.to(lat2, lng), null, null, -536870945, null)));
                            Log.i("DashViewModel", "Waypoint " + (index + 1) + "/" + stops.size() + " reached; continuing to " + next.getName());
                            return true;
                        }
                    }
                    closeToStop = z;
                    int legIndex2 = index2 - this.routeLegOffset;
                    if (accuracyTrusted) {
                    }
                    routeMatchTrusted = z;
                    if (routeMatchTrusted) {
                    }
                    reachedLegEnd = z;
                    if (closeToStop2) {
                    }
                    this.routeRequestGeneration++;
                    job = this.routeRequestJob;
                    if (job != null) {
                    }
                    this.routeRequestJob = job2;
                    boolean z2222 = z;
                    this.rerouting = z2222;
                    this.trafficRefreshInFlight = z2222;
                    this.activeStopIndex = index + 1;
                    SharedLocation next2222 = stops.get(this.activeStopIndex);
                    this.destLat = next2222.getLat();
                    this.destLng = next2222.getLng();
                    Iterable<SharedLocation> subList222 = stops.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(stops));
                    Collection arrayList222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList222, 10));
                    while (r19.hasNext()) {
                    }
                    next = next2222;
                    this.routeWaypoints = (List) arrayList222;
                    this.voice.resetTrip();
                    this.voice.announceNextStop(next.getName());
                    this.session.updateRouteCard(next.getName());
                    mutableStateFlow = this._ui;
                    do {
                        value = mutableStateFlow.getValue();
                        name = next.getName();
                        lat2 = next.getLat();
                        Intrinsics.checkNotNull(lat2);
                        lng = next.getLng();
                        Intrinsics.checkNotNull(lng);
                    } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, name, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, TuplesKt.to(lat2, lng), null, null, -536870945, null)));
                    Log.i("DashViewModel", "Waypoint " + (index + 1) + "/" + stops.size() + " reached; continuing to " + next.getName());
                    return true;
                }
            }
            accuracy = null;
            if (accuracy != null) {
            }
            if (accuracyTrusted) {
            }
            closeToStop = z;
            int legIndex22 = index2 - this.routeLegOffset;
            if (accuracyTrusted) {
            }
            routeMatchTrusted = z;
            if (routeMatchTrusted) {
            }
            reachedLegEnd = z;
            if (closeToStop2) {
            }
            this.routeRequestGeneration++;
            job = this.routeRequestJob;
            if (job != null) {
            }
            this.routeRequestJob = job2;
            boolean z22222 = z;
            this.rerouting = z22222;
            this.trafficRefreshInFlight = z22222;
            this.activeStopIndex = index + 1;
            SharedLocation next22222 = stops.get(this.activeStopIndex);
            this.destLat = next22222.getLat();
            this.destLng = next22222.getLng();
            Iterable<SharedLocation> subList2222 = stops.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(stops));
            Collection arrayList2222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList2222, 10));
            while (r19.hasNext()) {
            }
            next = next22222;
            this.routeWaypoints = (List) arrayList2222;
            this.voice.resetTrip();
            this.voice.announceNextStop(next.getName());
            this.session.updateRouteCard(next.getName());
            mutableStateFlow = this._ui;
            do {
                value = mutableStateFlow.getValue();
                name = next.getName();
                lat2 = next.getLat();
                Intrinsics.checkNotNull(lat2);
                lng = next.getLng();
                Intrinsics.checkNotNull(lng);
            } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, name, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, TuplesKt.to(lat2, lng), null, null, -536870945, null)));
            Log.i("DashViewModel", "Waypoint " + (index + 1) + "/" + stops.size() + " reached; continuing to " + next.getName());
            return true;
        }
        return false;
    }

    private final double remainingToActiveStop(Route r, NavState nav) {
        int legIndex = this.activeStopIndex - this.routeLegOffset;
        Double d = (Double) CollectionsKt.getOrNull(r.getLegEndCumulative(), legIndex);
        double end = d != null ? d.doubleValue() : r.getTotalMeters();
        return RangesKt.coerceAtLeast(end - nav.getProgressM(), 0.0d);
    }

    private final void maybeRefreshTraffic(Location loc) {
        SharedLocation sharedLocation;
        Double lat;
        Job launch$default;
        if (!this.userWantsConnection || this.session.getState().getValue() != DashState.STREAMING || loc == null || !LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.INSTANCE, loc, 0L, 0L, 6, null) || this.route == null || this.rerouting || this.trafficRefreshInFlight || this.dashConfig.getRoutingSource().getValue() != RoutingSource.GOOGLE) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastTrafficRefreshAt < 120000 || (sharedLocation = (SharedLocation) CollectionsKt.lastOrNull((List) this.routeStops)) == null || (lat = sharedLocation.getLat()) == null) {
            return;
        }
        double finalLat = lat.doubleValue();
        Double lng = sharedLocation.getLng();
        if (lng == null) {
            return;
        }
        double finalLng = lng.doubleValue();
        this.lastTrafficRefreshAt = now;
        if (takeAutoRouteCall("traffic refresh")) {
            this.trafficRefreshInFlight = true;
            this.routeRequestGeneration++;
            long requestId = this.routeRequestGeneration;
            List waypointSnapshot = CollectionsKt.toList(this.routeWaypoints);
            int stopIndexSnapshot = this.activeStopIndex;
            Job job = this.routeRequestJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashViewModel$maybeRefreshTraffic$1(this, loc, finalLat, finalLng, waypointSnapshot, requestId, stopIndexSnapshot, null), 3, null);
            this.routeRequestJob = launch$default;
        }
    }

    private final boolean takeAutoRouteCall(String kind) {
        if (this.autoRouteCalls >= 40) {
            if (!this.autoRouteCapLogged) {
                this.autoRouteCapLogged = true;
                RideDiagnostics.INSTANCE.log("reroute", "auto Routes API cap (40) reached — no more " + kind + "/reroutes for this destination");
                return false;
            }
            return false;
        }
        this.autoRouteCalls++;
        RideDiagnostics.INSTANCE.log("reroute", kind + ": auto Routes call " + this.autoRouteCalls + "/40");
        return true;
    }

    private final float normalizeHeading(float degrees) {
        float value = degrees % 360.0f;
        if (value < 0.0f) {
            return value + 360.0f;
        }
        return value;
    }

    private final int renderScale() {
        return this.dashConfig.getSharperMap().getValue().booleanValue() ? 2 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap newFrameBitmap() {
        int renderScale = renderScale();
        Bitmap createBitmap = Bitmap.createBitmap(renderScale * DashEncoder.WIDTH, renderScale * 300, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "let(...)");
        return createBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void redrawFrame(double centerLat, double centerLng, float cameraHeading, float travelHeading, Double remainingM) {
        List<GeoPoint> emptyList;
        List<TrafficInterval> emptyList2;
        String str;
        String str2;
        RoutingSource value;
        int i;
        List<String> list;
        PhoneStatus latestPhoneStatus;
        List<String> list2;
        boolean z;
        Double d;
        Integer mins;
        boolean arriving;
        boolean recalculating;
        GeoPoint geoPoint;
        Collection collection;
        int scale = renderScale();
        this.tiles.setHiDpi(scale > 1);
        Bitmap bmp = this.frameBitmap;
        if (bmp == null) {
            return;
        }
        if (bmp.getWidth() != scale * DashEncoder.WIDTH) {
            bmp = newFrameBitmap();
            this.frameBitmap = bmp;
        }
        Integer mins2 = this.displayedEtaMin;
        boolean arriving2 = mins2 != null && mins2.intValue() <= 0;
        boolean recalculating2 = this._ui.getValue().getRecalculating();
        String etaPrimary = (!recalculating2 || this.route == null) ? (mins2 == null || this.etaArrivalMs <= 0) ? null : arriving2 ? "Arriving" : mins2.intValue() >= 60 ? (mins2.intValue() / 60) + "h " + (mins2.intValue() % 60) + "m" : mins2 + " min" : "Recalculating";
        String etaSecondary = (etaPrimary == null || arriving2 || recalculating2) ? null : new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date(this.etaArrivalMs));
        int i2 = this.zoom;
        float f = this.panX;
        float f2 = this.panY;
        boolean z2 = this.headingUp;
        Double d2 = this.frameRiderLat;
        Double d3 = this.frameRiderLng;
        Double d4 = this.destLat;
        Double d5 = this.destLng;
        Iterable<SharedLocation> iterable = this.routeStops;
        Collection arrayList = new ArrayList();
        for (SharedLocation sharedLocation : iterable) {
            if (sharedLocation.getLat() == null || sharedLocation.getLng() == null) {
                d = d5;
                mins = mins2;
                arriving = arriving2;
                recalculating = recalculating2;
                geoPoint = null;
            } else {
                d = d5;
                mins = mins2;
                arriving = arriving2;
                recalculating = recalculating2;
                geoPoint = new GeoPoint(sharedLocation.getLat().doubleValue(), sharedLocation.getLng().doubleValue());
            }
            if (geoPoint != null) {
                collection = arrayList;
                collection.add(geoPoint);
            } else {
                collection = arrayList;
            }
            arrayList = collection;
            d5 = d;
            mins2 = mins;
            arriving2 = arriving;
            recalculating2 = recalculating;
        }
        Double d6 = d5;
        ArrayList arrayList2 = (List) arrayList;
        String destinationName = this._ui.getValue().getDestinationName();
        Route route = this.route;
        if (route == null || (emptyList = route.getGeometry()) == null) {
            emptyList = CollectionsKt.emptyList();
        }
        List<GeoPoint> list3 = emptyList;
        Route route2 = this.route;
        if (route2 == null || (emptyList2 = route2.getTraffic()) == null) {
            emptyList2 = CollectionsKt.emptyList();
        }
        List<TrafficInterval> list4 = emptyList2;
        String fmtDist = remainingM != null ? fmtDist(remainingM.doubleValue()) : null;
        List menuRows = this.pickerItems != null ? menuRows() : null;
        switch (WhenMappings.$EnumSwitchMapping$0[this.menuPage.ordinal()]) {
            case 1:
                str = "KAYITLI YERLER";
                break;
            case 2:
                str = "ROTA / DURAK";
                break;
            case 3:
                str = "ROTA KAYNAĞI";
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        switch (WhenMappings.$EnumSwitchMapping$0[this.menuPage.ordinal()]) {
            case 1:
                str2 = "Sol/sağ: gez · sağ basılı: aç";
                break;
            case 2:
                str2 = "Sol/sağ: gez · sağ basılı: seç";
                break;
            case 3:
                str2 = "Sol/sağ: gez · sağ basılı: seç";
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        int i3 = this.pickerIndex;
        String str3 = System.currentTimeMillis() < this.zoomBadgeUntilMs ? "Ölçek " + this.zoom : null;
        Route route3 = this.route;
        if (route3 == null || (value = route3.getSource()) == null) {
            value = this.dashConfig.getRoutingSource().getValue();
        }
        RoutingSource routingSource = value;
        List<String> list5 = this.notice;
        if (list5 != null) {
            if (this.pickerItems == null) {
                i = i3;
                list2 = list5;
                if (System.currentTimeMillis() < this.noticeUntilMs) {
                    z = true;
                    if (z) {
                        list = list2;
                        MapRenderer.Frame frame = new MapRenderer.Frame(centerLat, centerLng, i2, f, f2, z2, cameraHeading, travelHeading, d2, d3, d4, d6, arrayList2, destinationName, list3, list4, null, fmtDist, false, etaPrimary, etaSecondary, menuRows, str, i, str2, str3, !this.dashConfig.getShowJoystickTrail().getValue().booleanValue() ? this.joyTrail.text(System.currentTimeMillis()) : null, TileProvider.OSM_ATTRIBUTION, list, routingSource, (this.pickerItems == null || System.currentTimeMillis() >= this.downHintUntilMs) ? null : "▼ tekrar: menüyü aç", (this.dashConfig.getShowPhoneBattery().getValue().booleanValue() || (latestPhoneStatus = this.session.getLatestPhoneStatus()) == null) ? null : TuplesKt.to(Integer.valueOf(latestPhoneStatus.getBatteryPct()), Boolean.valueOf(latestPhoneStatus.getCharging())), this.gpsStatus != GpsStatus.WEAK, this.gpsStatus != GpsStatus.LOST, this.mapDark);
                        Canvas canvas = new Canvas(bmp);
                        canvas.scale(scale, scale);
                        this.mapRenderer.draw(canvas, frame, DashEncoder.WIDTH, 300);
                        drawDashOverlays(canvas, DashEncoder.WIDTH, 300);
                        this._previewFrame.setValue(bmp.copy(Bitmap.Config.ARGB_8888, false));
                    }
                }
            } else {
                i = i3;
                list2 = list5;
            }
            z = false;
            if (z) {
            }
        } else {
            i = i3;
        }
        list = null;
        MapRenderer.Frame frame2 = new MapRenderer.Frame(centerLat, centerLng, i2, f, f2, z2, cameraHeading, travelHeading, d2, d3, d4, d6, arrayList2, destinationName, list3, list4, null, fmtDist, false, etaPrimary, etaSecondary, menuRows, str, i, str2, str3, !this.dashConfig.getShowJoystickTrail().getValue().booleanValue() ? this.joyTrail.text(System.currentTimeMillis()) : null, TileProvider.OSM_ATTRIBUTION, list, routingSource, (this.pickerItems == null || System.currentTimeMillis() >= this.downHintUntilMs) ? null : "▼ tekrar: menüyü aç", (this.dashConfig.getShowPhoneBattery().getValue().booleanValue() || (latestPhoneStatus = this.session.getLatestPhoneStatus()) == null) ? null : TuplesKt.to(Integer.valueOf(latestPhoneStatus.getBatteryPct()), Boolean.valueOf(latestPhoneStatus.getCharging())), this.gpsStatus != GpsStatus.WEAK, this.gpsStatus != GpsStatus.LOST, this.mapDark);
        Canvas canvas2 = new Canvas(bmp);
        canvas2.scale(scale, scale);
        this.mapRenderer.draw(canvas2, frame2, DashEncoder.WIDTH, 300);
        drawDashOverlays(canvas2, DashEncoder.WIDTH, 300);
        this._previewFrame.setValue(bmp.copy(Bitmap.Config.ARGB_8888, false));
    }

    private final Paint getOvBg() {
        return (Paint) this.ovBg.getValue();
    }

    static final Paint ovBg_delegate$lambda$65() {
        Paint paint = new Paint();
        paint.setColor(-871690994);
        paint.setAntiAlias(true);
        return paint;
    }

    private final Paint getOvTitle() {
        return (Paint) this.ovTitle.getValue();
    }

    static final Paint ovTitle_delegate$lambda$67() {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setAntiAlias(true);
        paint.setTextSize(15.0f);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    private final Paint getOvSub() {
        return (Paint) this.ovSub.getValue();
    }

    static final Paint ovSub_delegate$lambda$69() {
        Paint paint = new Paint();
        paint.setColor(-3158065);
        paint.setAntiAlias(true);
        paint.setTextSize(10.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    private final Paint getOvAccent() {
        return (Paint) this.ovAccent.getValue();
    }

    static final Paint ovAccent_delegate$lambda$71() {
        Paint paint = new Paint();
        paint.setColor(-874181);
        paint.setAntiAlias(true);
        paint.setTextSize(10.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    private final void drawDashOverlays(Canvas canvas, int w, int h) {
        IncomingCall call = CallInfoProvider.INSTANCE.getIncomingCall().getValue();
        if (call != null && call.getIncoming()) {
            float cx = w / 2.0f;
            float cy = h / 2.0f;
            float half = h * 0.28f;
            canvas.drawRoundRect(cx - half, cy - 26.0f, cx + half, cy + 26.0f, 10.0f, 10.0f, getOvBg());
            canvas.drawText("Incoming call", cx, cy - 11.0f, getOvSub());
            canvas.drawText(ellipsizeOverlay(call.getCaller(), 16), cx, 5.0f + cy, getOvTitle());
            canvas.drawText("UP answer · DOWN reject", cx, 19.0f + cy, getOvAccent());
        }
    }

    private final String ellipsizeOverlay(String s, int max) {
        if (s.length() <= max) {
            return s;
        }
        return StringsKt.take(s, max - 1) + "…";
    }

    private final void answerCall(IncomingCall c) {
        boolean sent;
        Object m8392constructorimpl;
        PendingIntent answerIntent = c.getAnswerIntent();
        if (answerIntent != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                DashViewModel dashViewModel = this;
                answerIntent.send();
                m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            sent = Result.m8399isSuccessimpl(m8392constructorimpl);
        } else {
            sent = false;
        }
        if (!sent) {
            this.callController.answer();
        }
        RideDiagnostics.INSTANCE.log("media", "answer (notifAction=" + sent + ")");
    }

    private final void endCall(IncomingCall c) {
        boolean sent;
        Object m8392constructorimpl;
        PendingIntent declineIntent = c.getDeclineIntent();
        if (declineIntent != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                DashViewModel dashViewModel = this;
                declineIntent.send();
                m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            sent = Result.m8399isSuccessimpl(m8392constructorimpl);
        } else {
            sent = false;
        }
        if (!sent) {
            this.callController.hangup();
        }
        RideDiagnostics.INSTANCE.log("media", "end/reject (notifAction=" + sent + ")");
    }

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\t\u0010$\u001a\u00020\nHÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\rHÆ\u0003Jg\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010*\u001a\u00020\b2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020/HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001d¨\u00060"}, d2 = {"Lcom/example/wayfindr/viewmodel/DashViewModel$NavState;", "", "remainingM", "", "nextTurnM", "heading", "", "offRoute", "", "snapped", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "snapDist", "nextManeuver", "Lcom/example/wayfindr/dash/nav/Maneuver;", "progressM", "secondManeuver", "<init>", "(DDFZLcom/example/wayfindr/dash/nav/GeoPoint;DLcom/example/wayfindr/dash/nav/Maneuver;DLcom/example/wayfindr/dash/nav/Maneuver;)V", "getRemainingM", "()D", "getNextTurnM", "getHeading", "()F", "getOffRoute", "()Z", "getSnapped", "()Lcom/example/wayfindr/dash/nav/GeoPoint;", "getSnapDist", "getNextManeuver", "()Lcom/example/wayfindr/dash/nav/Maneuver;", "getProgressM", "getSecondManeuver", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class NavState {
        private final float heading;
        private final Maneuver nextManeuver;
        private final double nextTurnM;
        private final boolean offRoute;
        private final double progressM;
        private final double remainingM;
        private final Maneuver secondManeuver;
        private final double snapDist;
        private final GeoPoint snapped;

        /* renamed from: component1, reason: from getter */
        public final double getRemainingM() {
            return this.remainingM;
        }

        /* renamed from: component2, reason: from getter */
        public final double getNextTurnM() {
            return this.nextTurnM;
        }

        /* renamed from: component3, reason: from getter */
        public final float getHeading() {
            return this.heading;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getOffRoute() {
            return this.offRoute;
        }

        /* renamed from: component5, reason: from getter */
        public final GeoPoint getSnapped() {
            return this.snapped;
        }

        /* renamed from: component6, reason: from getter */
        public final double getSnapDist() {
            return this.snapDist;
        }

        /* renamed from: component7, reason: from getter */
        public final Maneuver getNextManeuver() {
            return this.nextManeuver;
        }

        /* renamed from: component8, reason: from getter */
        public final double getProgressM() {
            return this.progressM;
        }

        /* renamed from: component9, reason: from getter */
        public final Maneuver getSecondManeuver() {
            return this.secondManeuver;
        }

        public final NavState copy(double remainingM, double nextTurnM, float heading, boolean offRoute, GeoPoint snapped, double snapDist, Maneuver nextManeuver, double progressM, Maneuver secondManeuver) {
            Intrinsics.checkNotNullParameter(snapped, "snapped");
            return new NavState(remainingM, nextTurnM, heading, offRoute, snapped, snapDist, nextManeuver, progressM, secondManeuver);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NavState)) {
                return false;
            }
            NavState navState = (NavState) other;
            return Double.compare(this.remainingM, navState.remainingM) == 0 && Double.compare(this.nextTurnM, navState.nextTurnM) == 0 && Float.compare(this.heading, navState.heading) == 0 && this.offRoute == navState.offRoute && Intrinsics.areEqual(this.snapped, navState.snapped) && Double.compare(this.snapDist, navState.snapDist) == 0 && Intrinsics.areEqual(this.nextManeuver, navState.nextManeuver) && Double.compare(this.progressM, navState.progressM) == 0 && Intrinsics.areEqual(this.secondManeuver, navState.secondManeuver);
        }

        public int hashCode() {
            return (((((((((((((((Double.hashCode(this.remainingM) * 31) + Double.hashCode(this.nextTurnM)) * 31) + Float.hashCode(this.heading)) * 31) + Boolean.hashCode(this.offRoute)) * 31) + this.snapped.hashCode()) * 31) + Double.hashCode(this.snapDist)) * 31) + (this.nextManeuver == null ? 0 : this.nextManeuver.hashCode())) * 31) + Double.hashCode(this.progressM)) * 31) + (this.secondManeuver != null ? this.secondManeuver.hashCode() : 0);
        }

        public String toString() {
            return "NavState(remainingM=" + this.remainingM + ", nextTurnM=" + this.nextTurnM + ", heading=" + this.heading + ", offRoute=" + this.offRoute + ", snapped=" + this.snapped + ", snapDist=" + this.snapDist + ", nextManeuver=" + this.nextManeuver + ", progressM=" + this.progressM + ", secondManeuver=" + this.secondManeuver + ")";
        }

        public NavState(double remainingM, double nextTurnM, float heading, boolean offRoute, GeoPoint snapped, double snapDist, Maneuver nextManeuver, double progressM, Maneuver secondManeuver) {
            Intrinsics.checkNotNullParameter(snapped, "snapped");
            this.remainingM = remainingM;
            this.nextTurnM = nextTurnM;
            this.heading = heading;
            this.offRoute = offRoute;
            this.snapped = snapped;
            this.snapDist = snapDist;
            this.nextManeuver = nextManeuver;
            this.progressM = progressM;
            this.secondManeuver = secondManeuver;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public /* synthetic */ NavState(double d, double d2, float f, boolean z, GeoPoint geoPoint, double d3, Maneuver maneuver, double d4, Maneuver maneuver2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(d, d2, f, z, geoPoint, d3, maneuver, d4, r14);
            Maneuver maneuver3;
            if ((i & 256) == 0) {
                maneuver3 = maneuver2;
            } else {
                maneuver3 = null;
            }
        }

        public final float getHeading() {
            return this.heading;
        }

        public final double getNextTurnM() {
            return this.nextTurnM;
        }

        public final boolean getOffRoute() {
            return this.offRoute;
        }

        public final double getRemainingM() {
            return this.remainingM;
        }

        public final double getSnapDist() {
            return this.snapDist;
        }

        public final GeoPoint getSnapped() {
            return this.snapped;
        }

        public final Maneuver getNextManeuver() {
            return this.nextManeuver;
        }

        public final double getProgressM() {
            return this.progressM;
        }

        public final Maneuver getSecondManeuver() {
            return this.secondManeuver;
        }
    }

    static /* synthetic */ NavState trackProgress$default(DashViewModel dashViewModel, Route route, GeoPoint geoPoint, Float f, double d, boolean z, int i, Object obj) {
        boolean z2;
        if ((i & 16) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        return dashViewModel.trackProgress(route, geoPoint, f, d, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a1 A[EDGE_INSN: B:25:0x00a1->B:26:0x00a1 BREAK  A[LOOP:0: B:16:0x006d->B:54:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[LOOP:0: B:16:0x006d->B:54:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final NavState trackProgress(Route r, GeoPoint pos, Float gpsBearing, double speedMps, boolean advanceProgress) {
        Maneuver maneuver;
        double d;
        Object obj;
        boolean z;
        RouteProgressMatch match = RouteProgressMatcher.INSTANCE.match(r.getGeometry(), r.getCumulative(), pos, this.progressM, gpsBearing, speedMps);
        if (match == null) {
            double remaining = RangesKt.coerceAtLeast(r.getTotalMeters() - this.progressM, 0.0d);
            return new NavState(remaining, remaining, gpsBearing != null ? gpsBearing.floatValue() : 0.0f, true, pos, Double.MAX_VALUE, null, this.progressM, null);
        }
        double matchedProgress = this.progressM;
        if (advanceProgress) {
            matchedProgress = Math.max(matchedProgress - WAYPOINT_PROGRESS_TOLERANCE_M, match.getProgressMeters());
        }
        if (advanceProgress) {
            this.progressM = matchedProgress;
        }
        double remaining2 = RangesKt.coerceAtLeast(r.getTotalMeters() - matchedProgress, 0.0d);
        Iterator it = r.getManeuvers().iterator();
        while (true) {
            maneuver = null;
            if (it.hasNext()) {
                obj = it.next();
                Maneuver maneuver2 = (Maneuver) obj;
                if (maneuver2.getCumulativeMeters() > matchedProgress + 1.0d) {
                    d = 1.0d;
                    if (maneuver2.getType() != ManeuverType.DEPART) {
                        z = true;
                        if (!z) {
                            break;
                        }
                    }
                } else {
                    d = 1.0d;
                }
                z = false;
                if (!z) {
                }
            } else {
                d = 1.0d;
                obj = null;
                break;
            }
        }
        Maneuver nextMan = (Maneuver) obj;
        double nextTurn = nextMan != null ? RangesKt.coerceAtLeast(nextMan.getCumulativeMeters() - matchedProgress, 0.0d) : remaining2;
        if (nextMan != null) {
            Iterator it2 = r.getManeuvers().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                Maneuver maneuver3 = (Maneuver) next;
                if (maneuver3.getCumulativeMeters() > nextMan.getCumulativeMeters() + d && maneuver3.getType() != ManeuverType.DEPART) {
                    maneuver = next;
                    break;
                }
            }
            maneuver = maneuver;
        }
        Maneuver secondMan = maneuver;
        return new NavState(remaining2, nextTurn, match.getHeadingDegrees(), match.getLateralDistanceMeters() > 70.0d, match.getSnappedPoint(), match.getLateralDistanceMeters(), nextMan, matchedProgress, secondMan);
    }

    private final void updateThermal() {
        Object m8392constructorimpl;
        String label;
        try {
            Result.Companion companion = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(Integer.valueOf(this.powerManager.getCurrentThermalStatus()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = 0;
        }
        int status = ((Number) m8392constructorimpl).intValue();
        switch (status) {
            case 0:
            case 1:
                label = "OK";
                break;
            case 2:
                label = "Warm";
                break;
            case 3:
            case 4:
                label = "Hot";
                break;
            default:
                label = "Throttling";
                break;
        }
        if (!Intrinsics.areEqual(label, this._ui.getValue().getThermal())) {
            MutableStateFlow mutableStateFlow = this._ui;
            while (true) {
                DashUiState value = mutableStateFlow.getValue();
                MutableStateFlow mutableStateFlow2 = mutableStateFlow;
                int status2 = status;
                String label2 = label;
                if (mutableStateFlow2.compareAndSet(value, DashUiState.copy$default(value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, label2, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -2097153, null))) {
                    return;
                }
                mutableStateFlow = mutableStateFlow2;
                label = label2;
                status = status2;
            }
        }
    }

    private final Pair<Integer, Integer> toDashDistance(double meters) {
        return meters < 1000.0d ? TuplesKt.to(Integer.valueOf(RangesKt.coerceIn((int) meters, 0, 65535)), 48) : TuplesKt.to(Integer.valueOf(RangesKt.coerceIn((int) (meters / 100.0d), 0, 65535)), 16);
    }

    private final String fmtDist(double m) {
        if (m < 1000.0d) {
            return ((int) m) + " m";
        }
        String format = String.format("%.1f km", Arrays.copyOf(new Object[]{Double.valueOf(m / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final float angleDelta(float a, float b) {
        float d = ((((a - b) % 360.0f) + 540.0f) % 360.0f) - 180.0f;
        return Math.abs(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void teardown() {
        List loops = CollectionsKt.listOfNotNull((Object[]) new Job[]{this.tickerJob, this.streamJob});
        this.tickerJob = null;
        this.streamJob = null;
        Iterator it = loops.iterator();
        while (it.hasNext()) {
            Job.DefaultImpls.cancel$default((Job) it.next(), (CancellationException) null, 1, (Object) null);
        }
        stopMediaForwarding();
        final DashEncoder oldEncoder = this.encoder;
        this.encoder = null;
        this.frameBitmap = null;
        this._previewFrame.setValue(null);
        if (oldEncoder != null) {
            if (!loops.isEmpty()) {
                final AtomicInteger running = new AtomicInteger(loops.size());
                Iterator it2 = loops.iterator();
                while (it2.hasNext()) {
                    ((Job) it2.next()).invokeOnCompletion(new Function1() { // from class: com.example.wayfindr.viewmodel.DashViewModel$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DashViewModel.teardown$lambda$85$lambda$84(running, this, oldEncoder, (Throwable) obj);
                        }
                    });
                }
                return;
            }
            oldEncoder.release();
        }
    }

    static final Unit teardown$lambda$85$lambda$84(AtomicInteger $running, DashViewModel this$0, DashEncoder $oldEncoder, Throwable it) {
        if ($running.decrementAndGet() == 0) {
            try {
                Result.Companion companion = Result.INSTANCE;
                $oldEncoder.release();
                Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
        }
        return Unit.INSTANCE;
    }

    private final void startMediaForwarding() {
        Job launch$default;
        boolean granted = MediaInfoProvider.INSTANCE.isAccessGranted(getApplication());
        RideDiagnostics.INSTANCE.log("media", "forwarding start — notification access=" + granted);
        this.mediaInfo.start();
        Job job = this.mediaObserveJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new DashViewModel$startMediaForwarding$1(this, null), 3, null);
        this.mediaObserveJob = launch$default;
    }

    private final void stopMediaForwarding() {
        Job job = this.mediaObserveJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.mediaObserveJob = null;
        this.mediaInfo.stop();
        this.session.updateNowPlaying(null, "", "", false);
        this.session.updateCall(null);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        Object m8392constructorimpl;
        super.onCleared();
        BroadcastReceiver broadcastReceiver = this.debugButtonRx;
        if (broadcastReceiver != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                getApplication().unregisterReceiver(broadcastReceiver);
                m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            Result.m8391boximpl(m8392constructorimpl);
        }
        stopRecording();
        this.session.disconnect();
        this.wifiManager.disconnect();
        releaseBackgroundResources();
        this.voice.shutdown();
        RideDiagnostics.INSTANCE.stop("app closed");
    }
}
