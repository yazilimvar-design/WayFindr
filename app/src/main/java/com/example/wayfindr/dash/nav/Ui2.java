package com.example.wayfindr.dash.nav;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

/* loaded from: classes2.dex */
public final class Ui2 {
    public static GradientDrawable bg(int i, int i2, int i3, int i4) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius(i2);
        if (i4 > 0) {
            gradientDrawable.setStroke(i4, i3);
        }
        return gradientDrawable;
    }

    public static TextView btn(Context context, String str, int i, View.OnClickListener onClickListener, int i2, int i3) {
        TextView text = text(context, str, 17, i3, true);
        text.setGravity(17);
        int dp = dp(context, 18);
        text.setPadding(dp, dp, dp, dp);
        if (i2 != 0) {
            text.setBackground(bg(i2, dp(context, 20), 0, 0));
        }
        text.setId(i);
        text.setOnClickListener(onClickListener);
        lp(context, text, -1, 10);
        return text;
    }

    public static LinearLayout card(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        int dp = dp(context, 18);
        linearLayout.setPadding(dp, dp, dp, dp);
        setActive(linearLayout, false);
        lp(context, linearLayout, -1, 14);
        return linearLayout;
    }

    public static int dp(Context context, int i) {
        return (int) ((context.getResources().getDisplayMetrics().density * i) + 0.5f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static LinearLayout header(Activity activity, String str) {
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(0, 0, 0, dp(activity, 22));
        TextView text = text(activity, "←", 22, -1, false);
        text.setGravity(17);
        int dp = dp(activity, 50);
        text.setBackground(bg(-14802909, dp / 2, 0, 0));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp, dp);
        layoutParams.setMargins(0, 0, dp(activity, 16), 0);
        text.setLayoutParams(layoutParams);
        text.setId(99);
        text.setOnClickListener((View.OnClickListener) activity);
        linearLayout.addView(text);
        TextView text2 = text(activity, str, 25, -1, true);
        text2.setTypeface(Typeface.create("sans-serif-condensed", 1));
        linearLayout.addView(text2);
        return linearLayout;
    }

    public static void lp(Context context, View view, int i, int i2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, -2);
        layoutParams.setMargins(0, 0, 0, dp(context, i2));
        view.setLayoutParams(layoutParams);
    }

    public static LinearLayout section(Context context, String str) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        int dp = dp(context, 12);
        linearLayout.setPadding(0, dp, 0, dp);
        boolean z = false;
        while (true) {
            View view = new View(context);
            view.setBackgroundColor(1724491082);
            view.setLayoutParams(new LinearLayout.LayoutParams(0, dp(context, 1), 1.0f));
            linearLayout.addView(view);
            if (z) {
                lp(context, linearLayout, -1, 4);
                return linearLayout;
            }
            TextView text = text(context, str, 12, -3562166, true);
            text.setLetterSpacing(0.25f);
            int dp2 = dp(context, 14);
            text.setPadding(dp2, 0, dp2, 0);
            linearLayout.addView(text);
            z = true;
        }
    }

    public static void setActive(View view, boolean z) {
        if (view != null) {
            try {
                Context context = view.getContext();
                int dp = dp(context, 24);
                int dp2 = dp(context, 1);
                int i = -15461097;
                int i2 = -14276308;
                if (z) {
                    i = -14869998;
                    i2 = -8558029;
                }
                view.setBackground(bg(i, dp, i2, dp2));
            } catch (Throwable unused) {
            }
        }
    }

    public static String sym(int i) {
        return i == 10 ? "▣" : i == 11 ? "♫" : i == 12 ? "♪" : i == 14 ? "☀" : "•";
    }

    public static TextView text(Context context, String str, int i, int i2, boolean z) {
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextSize(2, i);
        textView.setTextColor(i2);
        if (z) {
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }
        return textView;
    }

    public static ColorStateList[] tints() {
        int[][] iArr = {new int[]{R.attr.state_checked}, new int[0]};
        return new ColorStateList[]{new ColorStateList(iArr, new int[]{-3562166, -12960447}), new ColorStateList(iArr, new int[]{-855310, -6644319})};
    }

    public static LinearLayout toggle(Context context, String str, String str2, int i, boolean z, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        LinearLayout card = card(context);
        card.setOrientation(0);
        card.setGravity(16);
        TextView text = text(context, sym(i), 22, -3562166, false);
        text.setGravity(17);
        int dp = dp(context, 50);
        int dp2 = dp(context, 14);
        text.setBackground(bg(-14015466, dp2, 0, 0));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dp, dp);
        layoutParams.setMargins(0, 0, dp2, 0);
        text.setLayoutParams(layoutParams);
        card.addView(text);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout.addView(text(context, str, 18, -1, true));
        TextView text2 = text(context, str2, 14, -6644319, false);
        text2.setPadding(0, dp(context, 4), 0, 0);
        linearLayout.addView(text2);
        card.addView(linearLayout);
        Switch r7 = new Switch(context);
        ColorStateList[] tints = tints();
        r7.setTrackTintList(tints[0]);
        r7.setThumbTintList(tints[1]);
        r7.setChecked(z);
        r7.setId(i);
        r7.setOnCheckedChangeListener(onCheckedChangeListener);
        card.addView(r7);
        setActive(card, z);
        return card;
    }
}
