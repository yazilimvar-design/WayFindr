package com.example.wayfindr.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InternetNetwork.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcom/example/wayfindr/util/InternetNetwork;", "", "<init>", "()V", "validated", "Landroid/net/Network;", "context", "Landroid/content/Context;", "openConnection", "Ljava/net/HttpURLConnection;", ImagesContract.URL, "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class InternetNetwork {
    public static final int $stable = 0;
    public static final InternetNetwork INSTANCE = new InternetNetwork();

    private InternetNetwork() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x004a, code lost:
    
        if ((r9.hasCapability(12) && r9.hasCapability(16)) == true) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Network validated(Context context) {
        Object obj;
        boolean z;
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager manager = (ConnectivityManager) systemService;
        Object[] allNetworks = manager.getAllNetworks();
        Intrinsics.checkNotNullExpressionValue(allNetworks, "getAllNetworks(...)");
        Object[] objArr = allNetworks;
        int length = objArr.length;
        int i = 0;
        while (true) {
            if (i < length) {
                obj = objArr[i];
                NetworkCapabilities networkCapabilities = manager.getNetworkCapabilities((Network) obj);
                if (networkCapabilities != null) {
                    z = true;
                }
                z = false;
                if (z) {
                    break;
                }
                i++;
            } else {
                obj = null;
                break;
            }
        }
        return (Network) obj;
    }

    public final HttpURLConnection openConnection(Context context, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        Network network = validated(context);
        if (network == null) {
            throw new IOException("No validated internet network is available");
        }
        URLConnection openConnection = network.openConnection(new URL(url));
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        return (HttpURLConnection) openConnection;
    }
}
