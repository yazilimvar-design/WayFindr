package com.example.wayfindr.media;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaModels.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J=\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/example/wayfindr/media/NowPlaying;", "", "title", "", "album", "artist", "art", "Landroid/graphics/Bitmap;", "playing", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;Z)V", "getTitle", "()Ljava/lang/String;", "getAlbum", "getArtist", "getArt", "()Landroid/graphics/Bitmap;", "getPlaying", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class NowPlaying {
    public static final int $stable = 8;
    private final String album;
    private final Bitmap art;
    private final String artist;
    private final boolean playing;
    private final String title;

    public static /* synthetic */ NowPlaying copy$default(NowPlaying nowPlaying, String str, String str2, String str3, Bitmap bitmap, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = nowPlaying.title;
        }
        if ((i & 2) != 0) {
            str2 = nowPlaying.album;
        }
        if ((i & 4) != 0) {
            str3 = nowPlaying.artist;
        }
        if ((i & 8) != 0) {
            bitmap = nowPlaying.art;
        }
        if ((i & 16) != 0) {
            z = nowPlaying.playing;
        }
        boolean z2 = z;
        String str4 = str3;
        return nowPlaying.copy(str, str2, str4, bitmap, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAlbum() {
        return this.album;
    }

    /* renamed from: component3, reason: from getter */
    public final String getArtist() {
        return this.artist;
    }

    /* renamed from: component4, reason: from getter */
    public final Bitmap getArt() {
        return this.art;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getPlaying() {
        return this.playing;
    }

    public final NowPlaying copy(String title, String album, String artist, Bitmap art, boolean playing) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(artist, "artist");
        return new NowPlaying(title, album, artist, art, playing);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NowPlaying)) {
            return false;
        }
        NowPlaying nowPlaying = (NowPlaying) other;
        return Intrinsics.areEqual(this.title, nowPlaying.title) && Intrinsics.areEqual(this.album, nowPlaying.album) && Intrinsics.areEqual(this.artist, nowPlaying.artist) && Intrinsics.areEqual(this.art, nowPlaying.art) && this.playing == nowPlaying.playing;
    }

    public int hashCode() {
        return (((((((this.title.hashCode() * 31) + this.album.hashCode()) * 31) + this.artist.hashCode()) * 31) + (this.art == null ? 0 : this.art.hashCode())) * 31) + Boolean.hashCode(this.playing);
    }

    public String toString() {
        return "NowPlaying(title=" + this.title + ", album=" + this.album + ", artist=" + this.artist + ", art=" + this.art + ", playing=" + this.playing + ")";
    }

    public NowPlaying(String title, String album, String artist, Bitmap art, boolean playing) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(artist, "artist");
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.art = art;
        this.playing = playing;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ NowPlaying(String str, String str2, String str3, Bitmap bitmap, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, r4, r5);
        Bitmap bitmap2;
        boolean z2;
        if ((i & 8) == 0) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = null;
        }
        if ((i & 16) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getAlbum() {
        return this.album;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final Bitmap getArt() {
        return this.art;
    }

    public final boolean getPlaying() {
        return this.playing;
    }
}
