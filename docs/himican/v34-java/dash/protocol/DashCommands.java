package com.example.wayfindr.dash.protocol;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.ComposerKt;
import com.example.wayfindr.dash.PhoneStatus;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;

/* compiled from: DashCommands.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b&\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\u000e\u001a\u00020\u0005J\u0006\u0010\u000f\u001a\u00020\u0005J\u0006\u0010\u0010\u001a\u00020\u0005J\u0006\u0010\u0011\u001a\u00020\u0005J\u0006\u0010\u0012\u001a\u00020\u0005J\u0006\u0010\u0013\u001a\u00020\u0005J\u0006\u0010\u0014\u001a\u00020\u0005J\u0006\u0010\u0015\u001a\u00020\u0005J\u0006\u0010\u0016\u001a\u00020\u0005J\u0006\u0010\u0017\u001a\u00020\u0005J\u000e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aJ$\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020'J\u0006\u0010)\u001a\u00020\u0005J}\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020/2\n\b\u0002\u00100\u001a\u0004\u0018\u00010$2\n\b\u0002\u00101\u001a\u0004\u0018\u00010$2\n\b\u0002\u00102\u001a\u0004\u0018\u00010$2\n\b\u0002\u00103\u001a\u0004\u0018\u00010$2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00105\u001a\u0004\u0018\u00010$2\n\b\u0002\u00106\u001a\u0004\u0018\u00010$2\n\b\u0002\u00107\u001a\u0004\u0018\u00010$¢\u0006\u0002\u00108J`\u0010>\u001a\u00020\u00052\b\b\u0002\u00100\u001a\u00020$2\b\b\u0002\u0010?\u001a\u00020$2\b\b\u0002\u00101\u001a\u00020$2\b\b\u0002\u00102\u001a\u00020$2\b\b\u0002\u00103\u001a\u00020$2\b\b\u0002\u0010\u0013\u001a\u00020/2\b\b\u0002\u00105\u001a\u00020$2\b\b\u0002\u00106\u001a\u00020$2\b\b\u0002\u00107\u001a\u00020$J\u0010\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u000bH\u0002J\u000e\u0010D\u001a\u00020\u00052\u0006\u0010E\u001a\u00020/J\u000e\u0010F\u001a\u00020\u00052\u0006\u0010G\u001a\u00020/J\u001e\u0010H\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u000b2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000bJ\u000e\u0010K\u001a\u00020\u00052\u0006\u0010L\u001a\u00020\u000bJ\u0006\u0010M\u001a\u00020\u0005J\u001e\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010O\u001a\u00020\u00052\b\b\u0002\u0010P\u001a\u00020$J\"\u0010Q\u001a\u00020$2\u0006\u0010R\u001a\u00020\u00052\u0006\u0010S\u001a\u00020\u00052\b\b\u0002\u0010T\u001a\u00020/H\u0002R\u000e\u0010\u001b\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u001aX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020$X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020$X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020$X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020$X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020$X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020$X\u0082T¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Lcom/example/wayfindr/dash/protocol/DashCommands;", "", "<init>", "()V", "authRequest", "", "authSendKey", "ciphertext", "initialBurst", "", "hostname", "", "timeSync", "hostnameAnnounce", "navContext", "emptyLists", "navStart", "navPlaceholder", "projectionFrame", "projectionOn", "projectionStop", "projectionOff", "frameDecodedIdr", "frameDecodedP", "buttonAck", "code", "", "BTN_05", "BTN_06", "BTN_07", "BTN_09", "BTN_0A", "BTN_22", "HB_0049", "heartbeat", "tempC", "", "headingDeg", HintConstants.AUTOFILL_HINT_PHONE, "Lcom/example/wayfindr/dash/PhoneStatus;", "EVT_COMPASS", "gpsAvailable", "NAV_TEMPLATE", "navPrefix", "navSuffix", "routeCard", "title", "", "maneuver", "primaryUnit", "totalDist", "totalUnit", "etaHHMM", "secondary", "secondaryDist", "secondaryUnit", "(Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)[B", "NAV_MANEUVER_CONTINUE", "NAV_SECONDARY_NONE", "NAV_UNIT_KM_TENTHS", "NAV_UNIT_METERS", "NAV_HDR", "activeNavPacket", "primaryDist", "MEDIA_FIELD_MAX", "CALLER_MAX", "mediaField", "s", "musicActive", DebugKt.DEBUG_PROPERTY_VALUE_ON, "musicPlaying", "playing", "nowPlaying", "album", "artist", "callNotify", "callerName", "callClear", "albumArtFrames", "jpeg", "chunk", "indexOf", "haystack", "needle", "fromEnd", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DashCommands {
    public static final int $stable;
    public static final byte BTN_05 = 5;
    public static final byte BTN_06 = 6;
    public static final byte BTN_07 = 7;
    public static final byte BTN_09 = 9;
    public static final byte BTN_0A = 10;
    public static final byte BTN_22 = 34;
    private static final int CALLER_MAX = 20;
    public static final byte EVT_COMPASS = 34;
    private static final int MEDIA_FIELD_MAX = 19;
    private static final String NAV_HDR = "00000000020100054B31472000";
    public static final int NAV_MANEUVER_CONTINUE = 9;
    public static final int NAV_SECONDARY_NONE = 255;
    public static final int NAV_UNIT_KM_TENTHS = 16;
    public static final int NAV_UNIT_METERS = 48;
    private static final byte[] navPrefix;
    private static final byte[] navSuffix;
    public static final DashCommands INSTANCE = new DashCommands();
    private static final byte[] HB_0049 = K1GPacketKt.hexToBytes("0049000b00000000020100054b3147200006080001050610000139060300015506040001a2060f0001aa0601000101054c000113052d00020000051b0001190521000132054d000132");
    private static final byte[] NAV_TEMPLATE = K1GPacketKt.hexToBytes("007e001100000000020100054b31472025050100145461696c6c65206465204d617320647520477200050200013c050300013405050002000a050600013005070001300508000430333033055400013005090002004f0546000110050a000155050c000104050b0006303031303030055500012006050001aa060d0001aa");

    private DashCommands() {
    }

    public final byte[] authRequest() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054b314720000804000101");
    }

    public final byte[] authSendKey(byte[] ciphertext) {
        Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
        if (ciphertext.length == 128) {
            return ArraysKt.plus(K1GPacketKt.hexToBytes("0095000200000000020100054B3147200008000080"), ciphertext);
        }
        throw new IllegalArgumentException(("q3c.d expects 128B RSA ciphertext, got " + ciphertext.length).toString());
    }

    public final List<byte[]> initialBurst(String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return CollectionsKt.listOf((Object[]) new byte[][]{authRequest(), hostnameAnnounce(hostname), timeSync(), K1GPacketKt.hexToBytes("0016000200000000020100054b314720030557000155"), K1GPacketKt.hexToBytes("0016000200000000020100054b3147200405560001aa"), K1GPacketKt.hexToBytes("0016000200000000020100054b3147200506050001aa"), K1GPacketKt.hexToBytes("0016000200000000020100054b3147200605170001aa"), K1GPacketKt.hexToBytes("001d000200000000020100054b314720080a020008aa55000000000000"), K1GPacketKt.hexToBytes("0044000a00000000020100054b3147200906080001ff060300015506040001a2060f0001aa0601000101054c000113052d00020000051b0001190521000132054d000132")});
    }

    public final byte[] timeSync() {
        Calendar cal = Calendar.getInstance();
        return K1GPacket.INSTANCE.build(K1GPacket.INSTANCE.tlv(6, 6, cal.get(11), cal.get(12), cal.get(13)));
    }

    public final byte[] hostnameAnnounce(String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        byte[] raw = hostname.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(raw, "getBytes(...)");
        if (raw.length > 200) {
            byte[] copyOf = Arrays.copyOf(raw, ComposerKt.invocationKey);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
            raw = copyOf;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(K1GPacketKt.hexToBytes("0021000200000000020100054b314720"));
        out.write(new byte[]{1, 6, 11, 0, (byte) (raw.length + 1)});
        out.write(raw);
        out.write(0);
        byte[] bytes = out.toByteArray();
        bytes[0] = (byte) ((bytes.length >> 8) & 255);
        bytes[1] = (byte) (bytes.length & 255);
        Intrinsics.checkNotNull(bytes);
        return bytes;
    }

    public final byte[] navContext() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054B31472000052E00011E");
    }

    public final byte[] emptyLists() {
        return K1GPacketKt.hexToBytes("002A000600000000020100054B31472000052F0001000530000100053100010005320001000533000100");
    }

    public final byte[] navStart() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054B31472000068000010B");
    }

    public final byte[] navPlaceholder() {
        return K1GPacket.INSTANCE.build(K1GPacket.INSTANCE.tlv(6, 10, 0, 0));
    }

    public final byte[] projectionFrame() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054B314720000556000155");
    }

    public final byte[] projectionOn() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054B314720000605000155");
    }

    public final byte[] projectionStop() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054B3147200005560001AA");
    }

    public final byte[] projectionOff() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054B3147200006050001AA");
    }

    public final byte[] frameDecodedIdr() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054B314720000611000155");
    }

    public final byte[] frameDecodedP() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054B314720000612000155");
    }

    public final byte[] buttonAck(byte code) {
        return K1GPacket.INSTANCE.build(K1GPacket.INSTANCE.tlv(6, 128, code & 255));
    }

    static {
        int magic = indexOf$default(INSTANCE, NAV_TEMPLATE, K1GPacketKt.hexToBytes("4b314720"), false, 4, null);
        if (!(magic >= 0)) {
            throw new IllegalArgumentException("K1G marker missing in nav template".toString());
        }
        int seqOff = magic + 4;
        int titleLen = ((NAV_TEMPLATE[seqOff + 3] & UByte.MAX_VALUE) << 8) | (NAV_TEMPLATE[seqOff + 4] & UByte.MAX_VALUE);
        navPrefix = ArraysKt.copyOfRange(NAV_TEMPLATE, 0, seqOff);
        navSuffix = ArraysKt.copyOfRange(NAV_TEMPLATE, seqOff + 5 + titleLen, NAV_TEMPLATE.length);
        $stable = 8;
    }

    public static /* synthetic */ byte[] heartbeat$default(DashCommands dashCommands, int i, int i2, PhoneStatus phoneStatus, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 25;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            phoneStatus = new PhoneStatus(0, false, false, false, 0, 0, 63, null);
        }
        return dashCommands.heartbeat(i, i2, phoneStatus);
    }

    public final byte[] heartbeat(int tempC, int headingDeg, PhoneStatus phone) {
        Intrinsics.checkNotNullParameter(phone, "phone");
        byte[] bArr = HB_0049;
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
        heartbeat$put(copyOf, 6, 4, RangesKt.coerceIn(phone.getBatteryPct(), 0, 100) + 100);
        heartbeat$put(copyOf, 6, 15, phone.getCharging() ? 85 : 170);
        heartbeat$put(copyOf, 6, 1, phone.getSignal() ? 1 : 0);
        heartbeat$put(copyOf, 6, 3, phone.getGpsOn() ? 85 : 170);
        heartbeat$put(copyOf, 5, 76, RangesKt.coerceIn(phone.getMediaVolume(), 0, 10) + 16);
        heartbeat$put(copyOf, 5, 27, RangesKt.coerceIn(phone.getCallVolume(), 0, 10) + 16);
        int indexOf$default = indexOf$default(this, copyOf, new byte[]{6, 16, 0, 1}, false, 4, null);
        if (indexOf$default >= 0 && indexOf$default + 4 < copyOf.length) {
            copyOf[indexOf$default + 4] = (byte) ((tempC + 40) & 255);
        }
        int indexOf$default2 = indexOf$default(this, copyOf, new byte[]{5, 45, 0, 2}, false, 4, null);
        int i = ((headingDeg % 360) + 360) % 360;
        if (indexOf$default2 >= 0 && indexOf$default2 + 5 < copyOf.length) {
            copyOf[indexOf$default2 + 4] = (byte) ((i >> 8) & 255);
            copyOf[indexOf$default2 + 5] = (byte) (i & 255);
        }
        return copyOf;
    }

    private static final void heartbeat$put(byte[] pkt, int type, int sub, int v) {
        int at = indexOf$default(INSTANCE, pkt, new byte[]{(byte) type, (byte) sub, 0, 1}, false, 4, null);
        if (at < 0 || at + 4 >= pkt.length) {
            return;
        }
        pkt[at + 4] = (byte) (v & 255);
    }

    public final byte[] gpsAvailable() {
        return K1GPacketKt.hexToBytes("0016000200000000020100054B314720000556000155");
    }

    public final byte[] routeCard(String title, boolean projectionOn, Integer maneuver, Integer primaryUnit, Integer totalDist, Integer totalUnit, String etaHHMM, Integer secondary, Integer secondaryDist, Integer secondaryUnit) {
        Intrinsics.checkNotNullParameter(title, "title");
        byte[] bytes = title.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        if (bytes.length > 60) {
            byte[] copyOf = Arrays.copyOf(bytes, 60);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
            bytes = copyOf;
        }
        byte[] rt = ArraysKt.plus(bytes, (byte) 0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(navPrefix);
        out.write(0);
        out.write(new byte[]{5, 1});
        out.write((rt.length >> 8) & 255);
        out.write(rt.length & 255);
        out.write(rt);
        out.write(navSuffix);
        byte[] bytes2 = out.toByteArray();
        routeCard$patch1(bytes2, 6, 5, projectionOn ? 85 : 170);
        if (maneuver != null) {
            routeCard$patch1(bytes2, 5, 2, maneuver.intValue());
        }
        if (primaryUnit != null) {
            routeCard$patch1(bytes2, 5, 6, primaryUnit.intValue());
        }
        routeCard$patch2(bytes2, 5, 9, totalDist != null ? totalDist.intValue() : 0);
        routeCard$patch1(bytes2, 5, 3, secondary != null ? secondary.intValue() : 255);
        routeCard$patch2(bytes2, 5, 5, secondaryDist != null ? secondaryDist.intValue() : 0);
        if (secondaryUnit != null) {
            routeCard$patch1(bytes2, 5, 7, secondaryUnit.intValue());
        }
        if (totalUnit != null) {
            routeCard$patch1(bytes2, 5, 70, totalUnit.intValue());
        }
        if (etaHHMM != null && etaHHMM.length() == 4) {
            Intrinsics.checkNotNull(bytes2);
            int m = indexOf(bytes2, new byte[]{5, 8, 0, 4}, true);
            if (m >= 0 && m + 8 < bytes2.length) {
                for (int i = 0; i < 4; i++) {
                    bytes2[m + 4 + i] = (byte) etaHHMM.charAt(i);
                }
            }
        }
        bytes2[0] = (byte) ((bytes2.length >> 8) & 255);
        bytes2[1] = (byte) (bytes2.length & 255);
        Intrinsics.checkNotNull(bytes2);
        return bytes2;
    }

    private static final void routeCard$patch1(byte[] bytes, int t, int s, int v) {
        DashCommands dashCommands = INSTANCE;
        Intrinsics.checkNotNull(bytes);
        int m = dashCommands.indexOf(bytes, new byte[]{(byte) t, (byte) s, 0, 1}, true);
        if (m < 0 || m + 4 >= bytes.length) {
            return;
        }
        bytes[m + 4] = (byte) (v & 255);
    }

    private static final void routeCard$patch2(byte[] bytes, int t, int s, int v) {
        DashCommands dashCommands = INSTANCE;
        Intrinsics.checkNotNull(bytes);
        int m = dashCommands.indexOf(bytes, new byte[]{(byte) t, (byte) s, 0, 2}, true);
        if (m >= 0 && m + 5 < bytes.length) {
            bytes[m + 4] = (byte) ((v >> 8) & 255);
            bytes[m + 5] = (byte) (v & 255);
        }
    }

    public static /* synthetic */ byte[] activeNavPacket$default(DashCommands dashCommands, int i, int i2, int i3, int i4, int i5, boolean z, int i6, int i7, int i8, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            i = 9;
        }
        if ((i9 & 2) != 0) {
            i2 = 500;
        }
        if ((i9 & 4) != 0) {
            i3 = 48;
        }
        if ((i9 & 8) != 0) {
            i4 = 500;
        }
        if ((i9 & 16) != 0) {
            i5 = 48;
        }
        if ((i9 & 32) != 0) {
            z = true;
        }
        if ((i9 & 64) != 0) {
            i6 = 255;
        }
        if ((i9 & 128) != 0) {
            i7 = 0;
        }
        int i10 = (i9 & 256) != 0 ? 48 : i8;
        return dashCommands.activeNavPacket(i, i2, i3, i4, i5, z, i6, i7, i10);
    }

    private static final String activeNavPacket$u16(int v) {
        String format = String.format("%04X", Arrays.copyOf(new Object[]{Integer.valueOf(65535 & v)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private static final String activeNavPacket$u8(int v) {
        String format = String.format("%02X", Arrays.copyOf(new Object[]{Integer.valueOf(v & 255)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final byte[] activeNavPacket(int maneuver, int primaryDist, int primaryUnit, int totalDist, int totalUnit, boolean projectionOn, int secondary, int secondaryDist, int secondaryUnit) {
        StringBuilder tlvs = new StringBuilder();
        tlvs.append("05020001").append(activeNavPacket$u8(maneuver));
        tlvs.append("05040002").append(activeNavPacket$u16(primaryDist));
        tlvs.append("05060001").append(activeNavPacket$u8(primaryUnit));
        tlvs.append("05030001").append(activeNavPacket$u8(secondary));
        tlvs.append("05050002").append(activeNavPacket$u16(secondaryDist));
        tlvs.append("05070001").append(activeNavPacket$u8(secondaryUnit));
        tlvs.append("05090002").append(activeNavPacket$u16(totalDist));
        tlvs.append("05460001").append(activeNavPacket$u8(totalUnit));
        tlvs.append("050A000155");
        tlvs.append("06050001").append(projectionOn ? "55" : "AA");
        tlvs.append("060D0001AA");
        String format = String.format("%04X", Arrays.copyOf(new Object[]{12}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        String innerHex = format + NAV_HDR + ((Object) tlvs);
        int innerBytes = innerHex.length() / 2;
        int outerLen = innerBytes + 2;
        String format2 = String.format("%04X", Arrays.copyOf(new Object[]{Integer.valueOf(outerLen)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        return K1GPacketKt.hexToBytes(format2 + innerHex);
    }

    private final byte[] mediaField(String s) {
        String t;
        if (s.length() > 19) {
            t = s.substring(0, 19);
            Intrinsics.checkNotNullExpressionValue(t, "substring(...)");
        } else {
            t = s;
        }
        int length = t.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) RangesKt.coerceAtMost((int) t.charAt(i), 255);
        }
        return bArr;
    }

    public final byte[] musicActive(boolean on) {
        K1GPacket k1GPacket = K1GPacket.INSTANCE;
        Tlv[] tlvArr = new Tlv[1];
        tlvArr[0] = K1GPacket.INSTANCE.tlv(5, 23, on ? 85 : 170);
        return k1GPacket.build(tlvArr);
    }

    public final byte[] musicPlaying(boolean playing) {
        K1GPacket k1GPacket = K1GPacket.INSTANCE;
        Tlv[] tlvArr = new Tlv[1];
        tlvArr[0] = K1GPacket.INSTANCE.tlv(5, 25, playing ? 85 : 170);
        return k1GPacket.build(tlvArr);
    }

    public final byte[] nowPlaying(String title, String album, String artist) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(artist, "artist");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(mediaField(title));
        out.write(0);
        out.write(mediaField(album));
        out.write(0);
        out.write(mediaField(artist));
        K1GPacket k1GPacket = K1GPacket.INSTANCE;
        K1GPacket k1GPacket2 = K1GPacket.INSTANCE;
        byte[] byteArray = out.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return k1GPacket.build(k1GPacket2.tlv(5, 13, byteArray));
    }

    public final byte[] callNotify(String callerName) {
        Intrinsics.checkNotNullParameter(callerName, "callerName");
        byte[] name = StringsKt.take(callerName, 20).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(name, "getBytes(...)");
        return K1GPacket.INSTANCE.build(K1GPacket.INSTANCE.tlv(5, 34, ArraysKt.plus(name, (byte) 0)));
    }

    public final byte[] callClear() {
        return K1GPacket.INSTANCE.build(K1GPacket.INSTANCE.tlv(5, 34, new byte[]{0}));
    }

    public static /* synthetic */ List albumArtFrames$default(DashCommands dashCommands, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1000;
        }
        return dashCommands.albumArtFrames(bArr, i);
    }

    public final List<byte[]> albumArtFrames(byte[] jpeg, int chunk) {
        Intrinsics.checkNotNullParameter(jpeg, "jpeg");
        Iterable chunked = CollectionsKt.chunked(ArraysKt.toList(jpeg), chunk);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(chunked, 10));
        Iterator it = chunked.iterator();
        while (it.hasNext()) {
            arrayList.add(K1GPacket.INSTANCE.build(K1GPacket.INSTANCE.tlv(5, 64, CollectionsKt.toByteArray((List) it.next()))));
            chunked = chunked;
        }
        return (List) arrayList;
    }

    static /* synthetic */ int indexOf$default(DashCommands dashCommands, byte[] bArr, byte[] bArr2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return dashCommands.indexOf(bArr, bArr2, z);
    }

    private final int indexOf(byte[] haystack, byte[] needle, boolean fromEnd) {
        IntRange range = fromEnd ? RangesKt.downTo(haystack.length - needle.length, 0) : new IntRange(0, haystack.length - needle.length);
        int i = range.getFirst();
        int last = range.getLast();
        int step = range.getStep();
        if ((step <= 0 || i > last) && (step >= 0 || last > i)) {
            return -1;
        }
        while (true) {
            int length = needle.length;
            for (int j = 0; j < length; j++) {
                if (haystack[i + j] != needle[j]) {
                    if (i == last) {
                        return -1;
                    }
                    i += step;
                }
            }
            return i;
        }
    }
}
