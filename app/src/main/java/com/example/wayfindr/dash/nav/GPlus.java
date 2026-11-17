package com.example.wayfindr.dash.nav;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.dash.GoogleKey;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.util.InternetNetwork;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class GPlus {
    public static volatile Context ctx;
    public static volatile long exp1;
    public static volatile long exp2;
    public static volatile long failUntil;
    public static volatile File gdir;
    public static volatile String key;
    public static volatile String lastBody;
    public static volatile int lastCode;
    public static volatile String lastErr;
    public static volatile Object lastFallback;
    public static volatile Pair lastNear;
    public static volatile ConcurrentHashMap pcache;
    public static volatile long placesFailUntil;
    public static volatile String sess1;
    public static volatile String sess2;
    public static volatile boolean tilesOn;

    public static String attribution(String str) {
        if (str != null) {
            try {
                if (tilesOn) {
                    return "Harita verileri ©".concat(String.valueOf(Calendar.getInstance().get(1))).concat(" Google");
                }
            } catch (Throwable unused) {
                return str;
            }
        }
        return str;
    }

    /** Google's own error text from the last failed call, shortened, so the key test says why (e.g. Map Tiles 404). */
    public static String errMsg() {
        try {
            JSONObject e = new JSONObject(lastBody).optJSONObject("error");
            String m = e == null ? "" : e.optString("message");
            if (m.length() == 0) return "";
            return " (" + (m.length() > 90 ? m.substring(0, 90) + "…" : m) + ")";
        } catch (Throwable t) {
            return "";
        }
    }

    public static String codeText(int i) {
        return i < 0 ? "bağlantı yok" : String.valueOf(i);
    }

    public static int command(Context context, String str) {
        int i = 0;
        if (str != null) {
            try {
                String trim = str.trim();
                int offCmd = NavPlus.offCmd(context, trim);
                if (offCmd != 0) {
                    return offCmd;
                }
                if (trim.equalsIgnoreCase("google sil") || trim.equalsIgnoreCase("googlesil")) {
                    trim = "";
                } else {
                    i = looksLikeKey(trim) ? 1 : 2;
                }
                try {
                    init(context);
                    setKey(context, trim);
                    setSource(context, i);
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                return 0;
            }
        }
        return i;
    }

    public static Object enterHook(Context context, String str, Pair pair) {
        if (command(context, str) != 0) {
            return Boolean.FALSE;
        }
        try {
            return placesBest(context, str, pair);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Route googleFallback(Context ctx, GeoPoint from, GeoPoint to, List<GeoPoint> intermediates, Route route, Object err) {
        if (route != null) {
            return route;
        }
        String str = "Google route failed -> OSM: ";
        if (err != null) {
            try {
                String obj3 = err.toString();
                if (obj3.length() > 200) {
                    obj3 = obj3.substring(0, 200);
                }
                str = "Google route failed -> OSM: ".concat(obj3);
            } catch (Throwable unused) {
                return null;
            }
        }
        RideDiagnostics.INSTANCE.log("route", str);
        List<Route> list = FreeRouter.requestRoutesNow(ctx, from, to, intermediates, false);
        if (list == null || list.isEmpty()) {
            return null;
        }
        Route obj4 = list.get(0);
        lastFallback = obj4;
        return obj4;
    }

    private static volatile String certSha1;

    /**
     * Adds the Android app identity headers Google checks for keys restricted to "Android apps"
     * (package + signing-cert SHA-1). Without them such a key answers 403 "Requests from this Android
     * client application <empty> are blocked." Safe to call for any googleapis.com request.
     */
    public static void androidAuth(java.net.URLConnection conn) {
        try {
            Context c = ctx;
            if (c == null || conn == null || !conn.getURL().getHost().endsWith("googleapis.com")) return;
            String sha = certSha1;
            if (sha == null) {
                android.content.pm.Signature[] sigs = c.getPackageManager()
                        .getPackageInfo(c.getPackageName(), android.content.pm.PackageManager.GET_SIGNING_CERTIFICATES)
                        .signingInfo.getApkContentsSigners();
                byte[] d = java.security.MessageDigest.getInstance("SHA-1").digest(sigs[0].toByteArray());
                StringBuilder sb = new StringBuilder();
                for (byte b : d) sb.append(String.format("%02X", b));
                sha = sb.toString();
                certSha1 = sha;
            }
            conn.setRequestProperty("X-Android-Package", c.getPackageName());
            conn.setRequestProperty("X-Android-Cert", sha);
        } catch (Throwable ignored) {
        }
    }

    public static int http(String str, String str2, String str3) {
        try {
            Context context = ctx;
            if (context == null) {
                lastBody = "no context";
                lastCode = -1;
                return -1;
            }
            HttpURLConnection openConnection = InternetNetwork.INSTANCE.openConnection(context, str);
            androidAuth(openConnection);
            openConnection.setRequestMethod("POST");
            openConnection.setDoOutput(true);
            openConnection.setRequestProperty("Content-Type", "application/json");
            if (str3 != null) {
                openConnection.setRequestProperty("X-Goog-Api-Key", key());
                openConnection.setRequestProperty("X-Goog-FieldMask", str3);
            }
            openConnection.setConnectTimeout(10000);
            openConnection.setReadTimeout(10000);
            OutputStream outputStream = openConnection.getOutputStream();
            Charset charset = StandardCharsets.UTF_8;
            outputStream.write(str2.getBytes(charset));
            outputStream.close();
            int responseCode = openConnection.getResponseCode();
            InputStream errorStream = (responseCode < 200 || responseCode >= 300) ? openConnection.getErrorStream() : openConnection.getInputStream();
            String str4 = "";
            if (errorStream != null) {
                byte[] readBytes = ByteStreamsKt.readBytes(errorStream);
                errorStream.close();
                str4 = new String(readBytes, charset);
            }
            lastBody = str4;
            lastCode = responseCode;
            openConnection.disconnect();
            return responseCode;
        } catch (Throwable th) {
            lastBody = th.toString();
            lastCode = -1;
            return -1;
        }
    }

    public static String httpErr(int i) {
        String sb;
        if (i < 0) {
            sb = "bağlantı yok";
        } else {
            try {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("HTTP ");
                sb2.append(i);
                String str = lastBody;
                if (str != null) {
                    try {
                        JSONObject optJSONObject = new JSONObject(str).optJSONObject("error");
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString(NotificationCompat.CATEGORY_STATUS);
                            if (optString.length() != 0) {
                                sb2.append(" ");
                                sb2.append(optString);
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                sb = sb2.toString();
            } catch (Throwable unused2) {
                return "hata";
            }
        }
        lastErr = sb;
        String str2 = lastBody;
        if (str2 == null) {
            str2 = "";
        }
        if (str2.length() > 300) {
            str2 = str2.substring(0, 300);
        }
        RideDiagnostics.INSTANCE.log("google", sb.concat(" ").concat(str2));
        return sb;
    }

    public static void init(Context context) {
        if (context != null) {
            try {
                if (ctx == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext == null) {
                        applicationContext = context;
                    }
                    ctx = applicationContext;
                }
                if (key == null) {
                    load();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean isReal(String str) {
        if (str == null) {
            return false;
        }
        try {
            return GoogleKey.INSTANCE.isReal(str);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String key() {
        String str = key;
        if (str != null) {
            return str;
        }
        load();
        String str2 = key;
        return str2 == null ? "" : str2;
    }

    public static List keyCommand(Context context, String str, Pair pair) {
        String str2;
        String testKey;
        int command = command(context, str);
        if (command == 0) {
            try {
                return places(context, str, pair);
            } catch (Throwable unused) {
                return null;
            }
        }
        if (command == 3) {
            str2 = "✓ Çevrimdışı harita ekranı açılıyor";
            testKey = "Dosya seç · aç/kapat · sil";
        } else if (command == 1) {
            try {
                str2 = "✓ Google anahtarı kaydedildi";
                testKey = testKey();
            } catch (Throwable unused2) {
                return new ArrayList();
            }
        } else {
            str2 = "✓ Google anahtarı silindi";
            testKey = "Harita, rota ve arama yine OpenStreetMap";
        }
        double d = 0.0d;
        double d2 = 0.0d;
        if (pair != null) {
            Object first = pair.getFirst();
            if (first instanceof Number) {
                Object second = pair.getSecond();
                if (second instanceof Number) {
                    d = ((Number) first).doubleValue();
                    d2 = ((Number) second).doubleValue();
                }
            }
        }
        PlaceSearch.Hit hit = new PlaceSearch.Hit(str2, d, d2, "Wayfindr", testKey); // test result ("Harita ✓ · Rota ✓ · Arama ✓") shown under the title
        ArrayList arrayList = new ArrayList();
        arrayList.add(hit);
        return arrayList;
    }

    public static void load() {
        try {
            Context context = ctx;
            if (context != null) {
                String string = context.getSharedPreferences("dash_config", 0).getString("google_key", "");
                if (string == null) {
                    string = "";
                }
                String trim = string.trim();
                key = trim;
                tilesOn = isReal(trim);
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean looksLikeKey(String str) {
        int length;
        if (str == null || (length = str.length()) < 30 || length > 60 || !str.startsWith("AIza")) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != '-' && charAt != '_') {
                if (charAt < '0') {
                    return false;
                }
                if (charAt <= '9') {
                    continue;
                } else {
                    if (charAt < 'A') {
                        return false;
                    }
                    if (charAt > 'Z' && (charAt < 'a' || charAt > 'z')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static String nearKey(Pair pair) {
        if (pair == null) {
            return "-";
        }
        try {
            Object first = pair.getFirst();
            if (!(first instanceof Number)) {
                return "-";
            }
            Object second = pair.getSecond();
            if (!(second instanceof Number)) {
                return "-";
            }
            return Math.round(((Number) first).doubleValue() * 10.0d) + "," + Math.round(((Number) second).doubleValue() * 10.0d);
        } catch (Throwable unused) {
            return "-";
        }
    }

    public static String okText(int i) {
        if (i == 200) {
            return "✓";
        }
        httpErr(i);
        return "✗ ".concat(codeText(i));
    }

    public static List parsePlaces(String str) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("places");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject3 = optJSONArray.optJSONObject(i);
                    if (optJSONObject3 != null && (optJSONObject = optJSONObject3.optJSONObject("displayName")) != null) {
                        String optString = optJSONObject.optString("text");
                        if (optString.length() != 0 && (optJSONObject2 = optJSONObject3.optJSONObject("location")) != null) {
                            double optDouble = optJSONObject2.optDouble("latitude", Double.NaN);
                            double optDouble2 = optJSONObject2.optDouble("longitude", Double.NaN);
                            if (optDouble == optDouble && optDouble2 == optDouble2) {
                                arrayList.add(new PlaceSearch.Hit(optString, optDouble, optDouble2, stripCountry(optJSONObject3.optString("formattedAddress")), "Google"));
                            }
                        }
                    }
                }
            }
            return arrayList;
        } catch (Throwable unused) {
            return arrayList;
        }
    }

    public static List places(Context context, String str, Pair pair) {
        Pair pair2 = pair;
        if (str == null) {
            return null;
        }
        try {
            init(context);
            if (!isReal(key()) || System.currentTimeMillis() < placesFailUntil) {
                return null;
            }
            String trim = str.trim();
            if (trim.length() < 3) {
                return null;
            }
            if (pair2 != null) {
                lastNear = pair2;
            } else {
                pair2 = lastNear;
            }
            String concat = trim.toLowerCase().concat("|").concat(nearKey(pair2));
            ConcurrentHashMap concurrentHashMap = pcache;
            if (concurrentHashMap == null) {
                concurrentHashMap = new ConcurrentHashMap();
                pcache = concurrentHashMap;
            }
            Object obj = concurrentHashMap.get(concat);
            if (obj != null) {
                return (List) obj;
            }
            int http = http("https://places.googleapis.com/v1/places:searchText", placesBody(trim, pair2), "places.displayName,places.formattedAddress,places.location");
            if (http != 200) {
                httpErr(http);
                placesFailUntil = System.currentTimeMillis() + ((http < 0 || http >= 500) ? 30000L : 600000L);
                return null;
            }
            List parsePlaces = parsePlaces(lastBody);
            if (parsePlaces == null || parsePlaces.isEmpty()) {
                return null;
            }
            if (concurrentHashMap.size() >= 40) {
                concurrentHashMap.clear();
            }
            concurrentHashMap.put(concat, parsePlaces);
            return parsePlaces;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Object placesBest(Context context, String str, Pair pair) {
        try {
            List places = places(context, str, pair);
            if (places == null || places.isEmpty()) {
                return null;
            }
            return places.get(0);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String placesBody(String str, Pair pair) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("textQuery", str);
        jSONObject.put("languageCode", "tr");
        jSONObject.put("regionCode", "TR");
        jSONObject.put("pageSize", 8);
        if (pair != null) {
            Object first = pair.getFirst();
            if (first instanceof Number) {
                Object second = pair.getSecond();
                if (second instanceof Number) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("latitude", ((Number) first).doubleValue());
                    jSONObject2.put("longitude", ((Number) second).doubleValue());
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("center", jSONObject2);
                    jSONObject3.put("radius", 50000.0d);
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("circle", jSONObject3);
                    jSONObject.put("locationBias", jSONObject4);
                }
            }
        }
        return jSONObject.toString();
    }

    public static String session(boolean z) {
        String str;
        long j;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (z) {
                str = sess2;
                j = exp2;
            } else {
                str = sess1;
                j = exp1;
            }
            if (str != null && currentTimeMillis < j - 600000L) {
                return str;
            }
            if (currentTimeMillis < failUntil) {
                return null;
            }
            String key2 = key();
            if (!isReal(key2)) {
                return null;
            }
            int http = http("https://tile.googleapis.com/v1/createSession?key=".concat(key2), z ? "{\"mapType\":\"roadmap\",\"language\":\"tr-TR\",\"region\":\"TR\",\"scale\":\"scaleFactor2x\",\"highDpi\":true}" : "{\"mapType\":\"roadmap\",\"language\":\"tr-TR\",\"region\":\"TR\",\"scale\":\"scaleFactor1x\"}", null);
            if (http == 200) {
                JSONObject jSONObject = new JSONObject(lastBody);
                String optString = jSONObject.optString("session");
                if (optString.length() != 0) {
                    long optLong = jSONObject.optLong("expiry", 0L) * 1000;
                    if (optLong <= currentTimeMillis) {
                        optLong = 21600000 + currentTimeMillis;
                    }
                    if (z) {
                        sess2 = optString;
                        exp2 = optLong;
                    } else {
                        sess1 = optString;
                        exp1 = optLong;
                    }
                    RideDiagnostics.INSTANCE.log("tiles", "Google map tile session ready");
                    return optString;
                }
            }
            httpErr(http);
            failUntil = 60000 + currentTimeMillis;
            return null;
        } catch (Throwable unused) {
            failUntil = System.currentTimeMillis() + 60000;
            return null;
        }
    }

    public static void setKey(Context context, String str) {
        try {
            init(context);
            Context context2 = ctx;
            if (context2 != null) {
                context2.getSharedPreferences("dash_config", 0).edit().putString("google_key", str).apply();
            }
            key = str;
            tilesOn = isReal(str);
            sess1 = null;
            sess2 = null;
            failUntil = 0L;
        } catch (Throwable unused) {
        }
    }

    public static void setSource(Context context, int i) {
        try {
            DashConfig dashConfig = DashConfig.Companion.get(context);
            if (dashConfig != null) {
                if (i == 1) {
                    dashConfig.setRoutingSource(RoutingSource.GOOGLE);
                } else if (dashConfig.getRoutingSource().getValue() == RoutingSource.GOOGLE) {
                    dashConfig.setRoutingSource(RoutingSource.OSM);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static RoutingSource sourceFor(Object obj) {
        return (obj == null || obj != lastFallback) ? RoutingSource.GOOGLE : RoutingSource.OSM;
    }

    public static String stripCountry(String str) {
        return str == null ? "" : str.endsWith(", Türkiye") ? str.substring(0, str.length() - ", Türkiye".length()) : str;
    }

    public static String testKey() {
        try {
            failUntil = 0L;
            placesFailUntil = 0L;
            sess1 = null;
            // Rota and Arama first: the Harita part may carry Google's long error text.
            StringBuilder sb = new StringBuilder();
            int http = http("https://routes.googleapis.com/directions/v2:computeRoutes", "{\"origin\":{\"location\":{\"latLng\":{\"latitude\":39.9208,\"longitude\":32.8541}}},\"destination\":{\"location\":{\"latLng\":{\"latitude\":39.9255,\"longitude\":32.8662}}},\"travelMode\":\"DRIVE\"}", "routes.distanceMeters");
            sb.append("Rota ");
            sb.append(okText(http));
            int http2 = http("https://places.googleapis.com/v1/places:searchText", "{\"textQuery\":\"Ankara\",\"pageSize\":1}", "places.id");
            sb.append(" · Arama ");
            sb.append(okText(http2));
            sb.append(" · Harita ");
            sb.append(session(false) != null ? "✓" : "✗ ".concat(codeText(lastCode)).concat(errMsg()));
            return sb.toString();
        } catch (Throwable unused) {
            return "Kontrol edilemedi";
        }
    }

    public static String testRoute() {
        try {
            int http = http("https://routes.googleapis.com/directions/v2:computeRoutes", "{\"origin\":{\"location\":{\"latLng\":{\"latitude\":39.9208,\"longitude\":32.8541}}},\"destination\":{\"location\":{\"latLng\":{\"latitude\":39.9255,\"longitude\":32.8662}}},\"travelMode\":\"DRIVE\"}", "routes.distanceMeters");
            return http == 200 ? "Rota ✓" : "Rota ✗ ".concat(httpErr(http));
        } catch (Throwable unused) {
            return "Rota ✗";
        }
    }

    public static File tileDir(File file) {
        try {
            if (!tilesOn || file == null) {
                return file;
            }
            File file2 = gdir;
            if (file2 != null) {
                return file2;
            }
            File file3 = new File(file.getParentFile(), "tiles_google_v1");
            file3.mkdirs();
            gdir = file3;
            return file3;
        } catch (Throwable unused) {
            return file;
        }
    }

    public static void tileHttp(int i) {
        try {
            if (!tilesOn || i < 400 || i == 404 || i >= 500) {
                return;
            }
            failUntil = System.currentTimeMillis() + 600000L;
            sess1 = null;
            sess2 = null;
            tilesOn = false;
            RideDiagnostics.INSTANCE.log("tiles", "Google tiles HTTP ".concat(String.valueOf(i)).concat(" -> OpenStreetMap for 10 min"));
        } catch (Throwable unused) {
        }
    }

    public static String tileUrl(String str, int i, int i2, int i3, int i4, boolean z) {
        String session;
        try {
            String key2 = key();
            if (!isReal(key2) || i4 <= 0 || (session = session(z)) == null) {
                tilesOn = false;
                return str;
            }
            tilesOn = true;
            return "https://tile.googleapis.com/v1/2dtiles/" + i + "/" + (((i2 % i4) + i4) % i4) + "/" + (((i3 % i4) + i4) % i4) + "?session=" + session + "&key=" + key2;
        } catch (Throwable unused) {
            tilesOn = false;
            return str;
        }
    }

    public static String usageName(String str) {
        return tilesOn ? "Google Map Tiles (2D)" : str;
    }
}
