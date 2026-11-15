package com.example.wayfindr.dash;

import com.example.wayfindr.dash.AuthEvent;
import com.example.wayfindr.dash.protocol.DashCommands;
import com.example.wayfindr.dash.protocol.Tlv;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: DashAuth.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcom/example/wayfindr/dash/DashAuth;", "", "ssid", "", "<init>", "(Ljava/lang/String;)V", "modulus", "Ljava/math/BigInteger;", "exponent", "keySent", "", "value", "", "sessionKey", "getSessionKey", "()[B", "ingest", "Lcom/example/wayfindr/dash/AuthEvent;", "tlv", "Lcom/example/wayfindr/dash/protocol/Tlv;", "reset", "", "buildKeyPacket", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DashAuth {
    public static final int $stable = 8;
    private BigInteger exponent;
    private boolean keySent;
    private BigInteger modulus;
    private byte[] sessionKey;
    private final String ssid;

    public DashAuth(String ssid) {
        Intrinsics.checkNotNullParameter(ssid, "ssid");
        this.ssid = ssid;
    }

    public final byte[] getSessionKey() {
        return this.sessionKey;
    }

    public final AuthEvent ingest(Tlv tlv) {
        Intrinsics.checkNotNullParameter(tlv, "tlv");
        if (tlv.getType() != 7) {
            return AuthEvent.None.INSTANCE;
        }
        switch (tlv.getSub()) {
            case 0:
                this.modulus = new BigInteger(1, tlv.getValue());
                break;
            case 1:
                Byte firstOrNull = ArraysKt.firstOrNull(tlv.getValue());
                return firstOrNull != null && firstOrNull.byteValue() == 1 ? AuthEvent.Confirmed.INSTANCE : AuthEvent.Rejected.INSTANCE;
            case 2:
            default:
                return AuthEvent.None.INSTANCE;
            case 3:
                this.exponent = new BigInteger(1, tlv.getValue());
                break;
        }
        BigInteger m = this.modulus;
        BigInteger e = this.exponent;
        if (!this.keySent && m != null && e != null) {
            this.keySent = true;
            return new AuthEvent.SendKey(buildKeyPacket(m, e));
        }
        return AuthEvent.None.INSTANCE;
    }

    public final void reset() {
        this.modulus = null;
        this.exponent = null;
        this.keySent = false;
    }

    private final byte[] buildKeyPacket(BigInteger modulus, BigInteger exponent) {
        byte[] aes = new byte[32];
        new SecureRandom().nextBytes(aes);
        this.sessionKey = aes;
        byte[] bytes = this.ssid.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] payload = ArraysKt.plus(bytes, aes);
        PublicKey pubKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, exponent));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, pubKey);
        DashCommands dashCommands = DashCommands.INSTANCE;
        byte[] doFinal = cipher.doFinal(payload);
        Intrinsics.checkNotNullExpressionValue(doFinal, "doFinal(...)");
        return dashCommands.authSendKey(doFinal);
    }
}
