package com.example.wayfindr.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;
import com.example.wayfindr.util.BuildId;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;

/* compiled from: UpdateChecker.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001 B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0002J\u0016\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005J,\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00052\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001aJ\u0016\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/example/wayfindr/data/UpdateChecker;", "", "<init>", "()V", "TAG", "", "CHECK_ENABLED", "", "LATEST_RELEASE_API", "RELEASES_PAGE", "FULL", "parseSha", "body", "shouldOffer", "context", "Landroid/content/Context;", "remote", "Lcom/example/wayfindr/data/UpdateChecker$ReleaseInfo;", "currentVersionName", "fetchLatest", "isNewer", "installed", "download", "Ljava/io/File;", ImagesContract.URL, "onProgress", "Lkotlin/Function1;", "", "", "install", "file", "openReleasesPage", "ReleaseInfo", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class UpdateChecker {
    public static final int $stable = 0;
    private static final boolean CHECK_ENABLED = false;
    private static final boolean FULL = false;
    public static final UpdateChecker INSTANCE = new UpdateChecker();
    private static final String LATEST_RELEASE_API = "https://api.github.com/repos/1857rebels/WayFindr/releases/latest";
    private static final String RELEASES_PAGE = "https://github.com/1857rebels/WayFindr/releases/latest";
    private static final String TAG = "UpdateChecker";

    private UpdateChecker() {
    }

    /* compiled from: UpdateChecker.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006!"}, d2 = {"Lcom/example/wayfindr/data/UpdateChecker$ReleaseInfo;", "", "versionName", "", "title", "notes", "apkUrl", "apkSize", "", "sha256", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getVersionName", "()Ljava/lang/String;", "getTitle", "getNotes", "getApkUrl", "getApkSize", "()J", "getSha256", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ReleaseInfo {
        public static final int $stable = 0;
        private final long apkSize;
        private final String apkUrl;
        private final String notes;
        private final String sha256;
        private final String title;
        private final String versionName;

        public static /* synthetic */ ReleaseInfo copy$default(ReleaseInfo releaseInfo, String str, String str2, String str3, String str4, long j, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = releaseInfo.versionName;
            }
            if ((i & 2) != 0) {
                str2 = releaseInfo.title;
            }
            if ((i & 4) != 0) {
                str3 = releaseInfo.notes;
            }
            if ((i & 8) != 0) {
                str4 = releaseInfo.apkUrl;
            }
            if ((i & 16) != 0) {
                j = releaseInfo.apkSize;
            }
            if ((i & 32) != 0) {
                str5 = releaseInfo.sha256;
            }
            String str6 = str5;
            long j2 = j;
            return releaseInfo.copy(str, str2, str3, str4, j2, str6);
        }

        /* renamed from: component1, reason: from getter */
        public final String getVersionName() {
            return this.versionName;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* renamed from: component3, reason: from getter */
        public final String getNotes() {
            return this.notes;
        }

        /* renamed from: component4, reason: from getter */
        public final String getApkUrl() {
            return this.apkUrl;
        }

        /* renamed from: component5, reason: from getter */
        public final long getApkSize() {
            return this.apkSize;
        }

        /* renamed from: component6, reason: from getter */
        public final String getSha256() {
            return this.sha256;
        }

        public final ReleaseInfo copy(String versionName, String title, String notes, String apkUrl, long apkSize, String sha256) {
            Intrinsics.checkNotNullParameter(versionName, "versionName");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(notes, "notes");
            Intrinsics.checkNotNullParameter(apkUrl, "apkUrl");
            Intrinsics.checkNotNullParameter(sha256, "sha256");
            return new ReleaseInfo(versionName, title, notes, apkUrl, apkSize, sha256);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ReleaseInfo)) {
                return false;
            }
            ReleaseInfo releaseInfo = (ReleaseInfo) other;
            return Intrinsics.areEqual(this.versionName, releaseInfo.versionName) && Intrinsics.areEqual(this.title, releaseInfo.title) && Intrinsics.areEqual(this.notes, releaseInfo.notes) && Intrinsics.areEqual(this.apkUrl, releaseInfo.apkUrl) && this.apkSize == releaseInfo.apkSize && Intrinsics.areEqual(this.sha256, releaseInfo.sha256);
        }

        public int hashCode() {
            return (((((((((this.versionName.hashCode() * 31) + this.title.hashCode()) * 31) + this.notes.hashCode()) * 31) + this.apkUrl.hashCode()) * 31) + Long.hashCode(this.apkSize)) * 31) + this.sha256.hashCode();
        }

        public String toString() {
            return "ReleaseInfo(versionName=" + this.versionName + ", title=" + this.title + ", notes=" + this.notes + ", apkUrl=" + this.apkUrl + ", apkSize=" + this.apkSize + ", sha256=" + this.sha256 + ")";
        }

        public ReleaseInfo(String versionName, String title, String notes, String apkUrl, long apkSize, String sha256) {
            Intrinsics.checkNotNullParameter(versionName, "versionName");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(notes, "notes");
            Intrinsics.checkNotNullParameter(apkUrl, "apkUrl");
            Intrinsics.checkNotNullParameter(sha256, "sha256");
            this.versionName = versionName;
            this.title = title;
            this.notes = notes;
            this.apkUrl = apkUrl;
            this.apkSize = apkSize;
            this.sha256 = sha256;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public /* synthetic */ ReleaseInfo(String str, String str2, String str3, String str4, long j, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, j, r8);
            String str6;
            if ((i & 32) == 0) {
                str6 = str5;
            } else {
                str6 = "";
            }
        }

        public final String getVersionName() {
            return this.versionName;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getNotes() {
            return this.notes;
        }

        public final String getApkUrl() {
            return this.apkUrl;
        }

        public final long getApkSize() {
            return this.apkSize;
        }

        public final String getSha256() {
            return this.sha256;
        }
    }

    private final String parseSha(String body) {
        List<String> groupValues;
        String str;
        String str2 = null;
        MatchResult find$default = Regex.find$default(new Regex(FULL ? "sha-?256-full[:\\s]+([0-9a-fA-F]{12,64})" : "sha-?256(?!-full)[:\\s]+([0-9a-fA-F]{12,64})", RegexOption.IGNORE_CASE), body, 0, 2, null);
        if (find$default != null && (groupValues = find$default.getGroupValues()) != null && (str = groupValues.get(1)) != null) {
            String lowerCase = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                str2 = StringsKt.take(lowerCase, 12);
            }
        }
        return str2 == null ? "" : str2;
    }

    public final boolean shouldOffer(Context context, ReleaseInfo remote) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(remote, "remote");
        String mine = BuildId.INSTANCE.sha12(context);
        if (StringsKt.isBlank(remote.getSha256()) || Intrinsics.areEqual(mine, EnvironmentCompat.MEDIA_UNKNOWN)) {
            return isNewer(remote.getVersionName(), currentVersionName(context));
        }
        return !Intrinsics.areEqual(mine, remote.getSha256());
    }

    public final String currentVersionName(Context context) {
        Object obj;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            UpdateChecker updateChecker = this;
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str == null) {
                str = "0";
            }
            obj = Result.m8392constructorimpl(str);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            obj = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        return (String) (Result.m8398isFailureimpl(obj) ? "0" : obj);
    }

    public final ReleaseInfo fetchLatest(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            UpdateChecker updateChecker = this;
            return null;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Object m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
            if (m8395exceptionOrNullimpl != null) {
                Log.w(TAG, "release check failed: " + m8395exceptionOrNullimpl.getMessage());
            }
            return (ReleaseInfo) (Result.m8398isFailureimpl(m8392constructorimpl) ? null : m8392constructorimpl);
        }
    }

    private static final List<Integer> isNewer$parts(String v) {
        int i;
        Iterable<String> split$default = StringsKt.split$default((CharSequence) StringsKt.trim((CharSequence) v).toString(), new char[]{'.', '-', '+'}, false, 0, 6, (Object) null);
        int i2 = 0;
        Collection arrayList = new ArrayList();
        for (String str : split$default) {
            int i3 = 0;
            Iterable iterable = split$default;
            int length = str.length();
            while (true) {
                if (i3 >= length) {
                    i = i2;
                    break;
                }
                if (!Character.isDigit(str.charAt(i3))) {
                    String substring = str.substring(0, i3);
                    i = i2;
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    str = substring;
                    break;
                }
                i3++;
            }
            Integer intOrNull = StringsKt.toIntOrNull(str);
            if (intOrNull != null) {
                arrayList.add(intOrNull);
            }
            split$default = iterable;
            i2 = i;
        }
        return (List) arrayList;
    }

    public final boolean isNewer(String remote, String installed) {
        Intrinsics.checkNotNullParameter(remote, "remote");
        Intrinsics.checkNotNullParameter(installed, "installed");
        List r = isNewer$parts(remote);
        List i = isNewer$parts(installed);
        if (r.isEmpty()) {
            return false;
        }
        int idx = 0;
        int max = Math.max(r.size(), i.size());
        while (idx < max) {
            int rv = (idx >= 0 && idx < r.size() ? r.get(idx) : 0).intValue();
            int iv = (idx >= 0 && idx < i.size() ? i.get(idx) : 0).intValue();
            if (rv != iv) {
                return rv > iv;
            }
            idx++;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x018e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final File download(Context context, String url, Function1<? super Float, Unit> onProgress) {
        Object m8392constructorimpl;
        Throwable m8395exceptionOrNullimpl;
        File file;
        HttpURLConnection httpURLConnection;
        boolean z;
        Throwable th;
        FileOutputStream fileOutputStream;
        Throwable th2;
        FileOutputStream fileOutputStream2;
        FileOutputStream fileOutputStream3;
        int i;
        Context context2 = context;
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(onProgress, "onProgress");
        try {
            Result.Companion companion = Result.INSTANCE;
            UpdateChecker updateChecker = this;
            File file2 = new File(context2.getCacheDir(), "updates");
            file2.mkdirs();
            file = new File(file2, "wayfindr-update.apk");
            if (file.exists()) {
                file.delete();
            }
            URLConnection openConnection = new URL(url).openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(30000);
            httpURLConnection.setRequestProperty("User-Agent", "WayfindR-App");
            z = true;
            httpURLConnection.setInstanceFollowRedirects(true);
            try {
                try {
                    int responseCode = httpURLConnection.getResponseCode();
                    if (200 > responseCode || responseCode >= 300) {
                        z = false;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
                if (m8395exceptionOrNullimpl != null) {
                }
                return (File) (!Result.m8398isFailureimpl(m8392constructorimpl) ? context2 : m8392constructorimpl);
            }
        } catch (Throwable th5) {
            th = th5;
            context2 = null;
        }
        if (!z) {
            Log.w(TAG, "download HTTP " + httpURLConnection.getResponseCode());
            httpURLConnection.disconnect();
            return null;
        }
        try {
            long contentLengthLong = httpURLConnection.getContentLengthLong();
            InputStream inputStream = httpURLConnection.getInputStream();
            try {
                InputStream inputStream2 = inputStream;
                try {
                    try {
                        FileOutputStream fileOutputStream4 = new FileOutputStream(file);
                        try {
                            FileOutputStream fileOutputStream5 = fileOutputStream4;
                            byte[] bArr = new byte[65536];
                            long j = 0;
                            while (true) {
                                int read = inputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                try {
                                    fileOutputStream5.write(bArr, 0, read);
                                    fileOutputStream2 = fileOutputStream4;
                                    long j2 = j + read;
                                    if (contentLengthLong > 0) {
                                        fileOutputStream3 = fileOutputStream5;
                                        i = read;
                                        j = j2;
                                        try {
                                            onProgress.invoke(Float.valueOf(RangesKt.coerceIn(j2 / contentLengthLong, 0.0f, 1.0f)));
                                        } catch (Throwable th6) {
                                            th = th6;
                                            th2 = th;
                                            fileOutputStream = fileOutputStream2;
                                            try {
                                                throw th2;
                                            } catch (Throwable th7) {
                                                CloseableKt.closeFinally(fileOutputStream, th2);
                                                throw th7;
                                            }
                                        }
                                    } else {
                                        fileOutputStream3 = fileOutputStream5;
                                        i = read;
                                        j = j2;
                                    }
                                    fileOutputStream4 = fileOutputStream2;
                                    fileOutputStream5 = fileOutputStream3;
                                } catch (Throwable th8) {
                                    th = th8;
                                    fileOutputStream2 = fileOutputStream4;
                                }
                            }
                            FileOutputStream fileOutputStream6 = fileOutputStream4;
                            try {
                                Unit unit = Unit.INSTANCE;
                                context2 = null;
                                CloseableKt.closeFinally(fileOutputStream6, null);
                                Unit unit2 = Unit.INSTANCE;
                                CloseableKt.closeFinally(inputStream, null);
                                httpURLConnection.disconnect();
                                m8392constructorimpl = Result.m8392constructorimpl(file);
                                m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
                                if (m8395exceptionOrNullimpl != null) {
                                    Log.w(TAG, "download failed: " + m8395exceptionOrNullimpl.getMessage());
                                }
                                return (File) (!Result.m8398isFailureimpl(m8392constructorimpl) ? context2 : m8392constructorimpl);
                            } catch (Throwable th9) {
                                th = th9;
                                fileOutputStream = fileOutputStream6;
                                th2 = th;
                                throw th2;
                            }
                        } catch (Throwable th10) {
                            th = th10;
                            fileOutputStream = fileOutputStream4;
                        }
                    } catch (Throwable th11) {
                        th = th11;
                        th = th;
                        try {
                            throw th;
                        } catch (Throwable th12) {
                            CloseableKt.closeFinally(inputStream, th);
                            throw th12;
                        }
                    }
                } catch (Throwable th13) {
                    th = th13;
                    th = th;
                    throw th;
                }
            } catch (Throwable th14) {
                th = th14;
            }
        } catch (Throwable th15) {
            th = th15;
            httpURLConnection.disconnect();
            throw th;
        }
    }

    public final boolean install(Context context, File file) {
        Object m8392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        if (!context.getPackageManager().canRequestPackageInstalls()) {
            try {
                Result.Companion companion = Result.INSTANCE;
                UpdateChecker updateChecker = this;
                context.startActivity(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + context.getPackageName())).addFlags(268435456));
                Result.m8392constructorimpl(Unit.INSTANCE);
                return false;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th));
                return false;
            }
        }
        Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        try {
            Result.Companion companion3 = Result.INSTANCE;
            UpdateChecker updateChecker2 = this;
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(268435457);
            context.startActivity(intent);
            m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th2));
        }
        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl != null) {
            Log.w(TAG, "install intent failed: " + m8395exceptionOrNullimpl.getMessage());
            return true;
        }
        return true;
    }

    public final void openReleasesPage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            UpdateChecker updateChecker = this;
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(RELEASES_PAGE)).addFlags(268435456));
            Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
    }
}
