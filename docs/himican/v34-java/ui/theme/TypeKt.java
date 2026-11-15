package com.example.wayfindr.ui.theme;

import androidx.compose.material3.Typography;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontFamilyKt;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.googlefonts.GoogleFont;
import androidx.compose.ui.text.googlefonts.GoogleFontKt;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.wayfindr.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Type.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0005\"\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"provider", "Landroidx/compose/ui/text/googlefonts/GoogleFont$Provider;", "GeistFamily", "Landroidx/compose/ui/text/font/FontFamily;", "getGeistFamily", "()Landroidx/compose/ui/text/font/FontFamily;", "GeistMonoFamily", "getGeistMonoFamily", "Typography", "Landroidx/compose/material3/Typography;", "getTypography", "()Landroidx/compose/material3/Typography;", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class TypeKt {
    private static final GoogleFont.Provider provider = new GoogleFont.Provider("com.google.android.gms.fonts", "com.google.android.gms", R.array.com_google_android_gms_fonts_certs);
    private static final FontFamily GeistMonoFamily = FontFamilyKt.FontFamily(GoogleFontKt.m7490FontwCLgNak$default(new GoogleFont("Geist Mono", false, 2, null), provider, FontWeight.INSTANCE.getNormal(), 0, 8, null), GoogleFontKt.m7490FontwCLgNak$default(new GoogleFont("Geist Mono", false, 2, null), provider, FontWeight.INSTANCE.getMedium(), 0, 8, null), GoogleFontKt.m7490FontwCLgNak$default(new GoogleFont("Geist Mono", false, 2, null), provider, FontWeight.INSTANCE.getSemiBold(), 0, 8, null), GoogleFontKt.m7490FontwCLgNak$default(new GoogleFont("Geist Mono", false, 2, null), provider, FontWeight.INSTANCE.getBold(), 0, 8, null));
    private static final FontFamily GeistFamily = FontFamilyKt.FontFamily(GoogleFontKt.m7490FontwCLgNak$default(new GoogleFont("Geist", false, 2, null), provider, FontWeight.INSTANCE.getNormal(), 0, 8, null), GoogleFontKt.m7490FontwCLgNak$default(new GoogleFont("Geist", false, 2, null), provider, FontWeight.INSTANCE.getMedium(), 0, 8, null), GoogleFontKt.m7490FontwCLgNak$default(new GoogleFont("Geist", false, 2, null), provider, FontWeight.INSTANCE.getSemiBold(), 0, 8, null), GoogleFontKt.m7490FontwCLgNak$default(new GoogleFont("Geist", false, 2, null), provider, FontWeight.INSTANCE.getBold(), 0, 8, null));
    private static final Typography Typography = new Typography(new TextStyle(ColorKt.getGold(), TextUnitKt.getSp(38), FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, GeistMonoFamily, (String) null, 0, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777176, (DefaultConstructorMarker) null), null, null, new TextStyle(ColorKt.getTextHi(), TextUnitKt.getSp(24), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, GeistFamily, (String) null, TextUnitKt.getSp(-0.48d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777048, (DefaultConstructorMarker) null), new TextStyle(ColorKt.getTextHi(), TextUnitKt.getSp(19), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, GeistFamily, (String) null, TextUnitKt.getSp(-0.38d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777048, (DefaultConstructorMarker) null), null, new TextStyle(ColorKt.getTextHi(), TextUnitKt.getSp(23), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, GeistFamily, (String) null, TextUnitKt.getSp(-0.46d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777048, (DefaultConstructorMarker) null), new TextStyle(ColorKt.getTextHi(), TextUnitKt.getSp(16), FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, GeistFamily, (String) null, TextUnitKt.getSp(-0.16d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777048, (DefaultConstructorMarker) null), null, new TextStyle(ColorKt.getTextMid(), TextUnitKt.getSp(15), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, GeistFamily, (String) null, 0, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777176, (DefaultConstructorMarker) null), new TextStyle(ColorKt.getTextMid(), TextUnitKt.getSp(13), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, GeistFamily, (String) null, 0, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777176, (DefaultConstructorMarker) null), null, null, null, new TextStyle(ColorKt.getTextLo(), TextUnitKt.getSp(11), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, GeistMonoFamily, (String) null, TextUnitKt.getSp(0.16d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777048, (DefaultConstructorMarker) null), 14630, null);

    public static final FontFamily getGeistFamily() {
        return GeistFamily;
    }

    public static final FontFamily getGeistMonoFamily() {
        return GeistMonoFamily;
    }

    public static final Typography getTypography() {
        return Typography;
    }
}
