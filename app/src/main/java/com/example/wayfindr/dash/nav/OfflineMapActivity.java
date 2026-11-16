package com.example.wayfindr.dash.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class OfflineMapActivity extends Activity implements View.OnClickListener, Runnable, CompoundButton.OnCheckedChangeListener, View.OnTouchListener {
    private Button btn;
    private TextView btn2;
    private ImageView iv;
    private float ld;
    private float lx;
    private float ly;
    private int mode;
    private boolean moved;
    private Uri pending;
    private ArrayList rows;
    private TextView tv;
    private TextView tv2;

    static final class Ui implements Runnable {
        private final OfflineMapActivity act;
        private final String msg;

        public Ui(OfflineMapActivity offlineMapActivity, String str) {
            this.act = offlineMapActivity;
            this.msg = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.act.setStatus(this.msg);
            } catch (Throwable unused) {
            }
        }
    }

    private void buildMap(LinearLayout linearLayout) {
        LinearLayout card = Ui2.card(this);
        TextView text = Ui2.text(this, "", 14, -3091498, false);
        this.tv = text;
        card.addView(text);
        linearLayout.addView(card);
        linearLayout.addView(Ui2.btn(this, "Harita dosyası seç (.mbtiles)", 1, this, -1525446, -16777216));
        TextView btn = Ui2.btn(this, "…", 2, this, -14407894, -1);
        this.btn2 = btn;
        linearLayout.addView(btn);
        linearLayout.addView(Ui2.btn(this, "Dosyayı sil", 3, this, 0, -1754835));
    }

    private void buildRides(LinearLayout linearLayout) {
        ArrayList rideRows = NavPlus.rideRows(this);
        this.rows = rideRows;
        int size = rideRows.size();
        if (size == 0) {
            LinearLayout card = Ui2.card(this);
            card.addView(Ui2.text(this, "Henüz kayıtlı sürüş yok. Konsola bağlanıp sürdüğünde her sürüş burada görünür; bağlantıyı kesince kaydedilir.", 15, -6644319, false));
            linearLayout.addView(card);
            return;
        }
        sumCard(linearLayout, "BU HAFTA", NavPlus.rideSum(rideRows, NavPlus.periodStart(false)));
        sumCard(linearLayout, "BU AY", NavPlus.rideSum(rideRows, NavPlus.periodStart(true)));
        double[] rideSum = NavPlus.rideSum(rideRows, 0L);
        sumCard(linearLayout, "TÜM ZAMANLAR", rideSum);
        linearLayout.addView(Ui2.section(this, "REKORLAR"));
        LinearLayout card2 = Ui2.card(this);
        card2.addView(Ui2.text(this, "En uzun sürüş: ".concat(NavPlus.fmtKm(rideSum[4])), 17, -1, true));
        card2.addView(Ui2.text(this, "En yüksek hız: ".concat(String.valueOf(Math.round(rideSum[3]))).concat(" km/sa"), 17, -1, true));
        linearLayout.addView(card2);
        linearLayout.addView(Ui2.section(this, "SÜRÜŞLER"));
        int min = Math.min(size, 20);
        for (int i = 0; i < min; i++) {
            Object obj = rideRows.get(i);
            LinearLayout card3 = Ui2.card(this);
            card3.addView(Ui2.text(this, NavPlus.fmtDate(NavPlus.rowL(obj, 0)), 16, -1, true));
            card3.addView(Ui2.text(this, NavPlus.fmtKm(NavPlus.rowD(obj, 1) / 1000.0d) + " · " + NavPlus.fmtDur(NavPlus.rowL(obj, 2)) + " · ort. " + NavPlus.avgKmh(obj), 14, -6644319, false));
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            int dp = Ui2.dp(this, 12);
            layoutParams.setMargins(0, dp, 0, dp);
            imageView.setLayoutParams(layoutParams);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(NavPlus.trackBmp((String) ((Object[]) obj)[4], 900, 440, 8.0f));
            card3.addView(imageView);
            card3.addView(Ui2.btn(this, "Paylaş", i + 200, this, -3562166, -16777216));
            linearLayout.addView(card3);
        }
        linearLayout.addView(Ui2.text(this, "Renkler: yeşil 0–30, açık yeşil 30–60, sarı 60–90, turuncu 90–120, kırmızı 120+ km/sa. Hız rengi bu sürümden sonra kaydedilen sürüşlerde görünür; eski sürüşler altın renkte çizilir.", 13, -6644319, false));
    }

    private void buildWall(LinearLayout linearLayout) {
        linearLayout.addView(Ui2.toggle(this, "Fotoğrafımı konsolda göster", "Rota yokken ve motor dururken haritanın yerine fotoğrafın görünür; yola çıkınca harita geri gelir.", 10, NavPlus.phOn, this));
        linearLayout.addView(Ui2.toggle(this, "Arka planda albüm kapağı", "Rota yokken müzik çalarken fotoğraf yerine albüm kapağı görünür. Müzik durunca fotoğrafın veya harita geri gelir.", 11, NavPlus.phAlb, this));
        linearLayout.addView(Ui2.section(this, "FOTOĞRAFIN"));
        LinearLayout card = Ui2.card(this);
        card.setGravity(1);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        imageView.setAdjustViewBounds(true);
        imageView.setId(4);
        imageView.setOnTouchListener(this);
        this.iv = imageView;
        card.addView(imageView);
        TextView text = Ui2.text(this, "Konsolda tam olarak böyle görünür. Tek parmakla sürükleyerek kaydır, iki parmakla yakınlaştır. Değiştirmek için dokun.", 13, -6644319, false);
        text.setGravity(17);
        text.setPadding(0, Ui2.dp(this, 12), 0, 0);
        card.addView(text);
        linearLayout.addView(card);
        linearLayout.addView(Ui2.btn(this, "Fotoğrafı değiştir", 4, this, -1525446, -16777216));
        linearLayout.addView(Ui2.btn(this, "Kırpmayı sıfırla", 6, this, -14407894, -1));
        linearLayout.addView(Ui2.btn(this, "Kaldır", 5, this, 0, -1754835));
        linearLayout.addView(Ui2.toggle(this, "Müziği fotoğrafın üstünde göster", "Bir şey çalarken fotoğrafın altındaki şerit albüm kapağını, şarkıyı ve sanatçıyı gösterir. Bildirim erişimi gerekir.", 12, NavPlus.phMus, this));
        linearLayout.addView(Ui2.toggle(this, "Hava durumu ve benzinlik konsolda", "Konsolun üstünde küçük şerit: sıcaklık, hava ve gittiğin yöndeki en yakın benzinliğe uzaklık. Yağmur bekleniyorsa uyarı da çıkar. İnternet gerekir.", 14, NavPlus.hudOn, this));
        LinearLayout card2 = Ui2.card(this);
        TextView text2 = Ui2.text(this, "", 14, -3091498, false);
        this.tv2 = text2;
        text2.setPadding(0, 0, 0, Ui2.dp(this, 12));
        card2.addView(text2);
        card2.addView(Ui2.btn(this, "Bildirim erişimi ayarını aç", 13, this, -14407894, -1));
        linearLayout.addView(card2);
    }

    public static void post(Activity activity, String str) {
        try {
            activity.runOnUiThread(new Ui((OfflineMapActivity) activity, str));
        } catch (Throwable unused) {
        }
    }

    private float span(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private void sumCard(LinearLayout linearLayout, String str, double[] dArr) {
        linearLayout.addView(Ui2.section(this, str));
        LinearLayout card = Ui2.card(this);
        card.addView(Ui2.text(this, NavPlus.fmtKm(dArr[0]), 30, -1, true));
        card.addView(Ui2.text(this, NavPlus.sumLine(dArr), 14, -6644319, false));
        linearLayout.addView(card);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            if (i == 78) {
                if (i2 != -1 || intent == null) {
                    return;
                }
                Uri data = intent.getData();
                if (data != null) {
                    String phImport = NavPlus.phImport(getApplicationContext(), data);
                    refresh();
                    Toast.makeText(this, phImport, 1).show();
                }
            } else {
                if (i != 77 || i2 != -1 || intent == null) {
                    return;
                }
                Uri data2 = intent.getData();
                if (data2 != null && !NavPlus.mbBusy) {
                    this.pending = data2;
                    NavPlus.mbBusy = true;
                    getWindow().addFlags(128);
                    setStatus("Kopyalanıyor… ekranı kapatmayın");
                    new Thread(this, "MbCopy").start();
                }
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        try {
            int id = compoundButton.getId();
            Ui2.setActive((View) compoundButton.getParent(), z);
            String str = id == 11 ? "ph_alb" : "ph_on";
            if (id == 12) {
                str = "ph_mus";
            }
            if (id == 14) {
                str = "hud_on";
            }
            NavPlus.phSet(getApplicationContext(), str, z);
            refresh();
        } catch (Throwable unused) {
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            int id = view.getId();
            if (id >= 200) {
                NavPlus.rideShare(this, this.rows.get(id - 200));
                return;
            }
            Context applicationContext = getApplicationContext();
            if (id == 1) {
                if (NavPlus.mbBusy) {
                    return;
                }
                Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("*/*");
                startActivityForResult(intent, 77);
                return;
            }
            if (id == 2) {
                if (NavPlus.mbBusy) {
                    return;
                }
                NavPlus.mbToggle(applicationContext);
                refresh();
                return;
            }
            if (id == 3) {
                NavPlus.mbDelete(applicationContext);
                refresh();
                return;
            }
            if (id == 4) {
                Intent intent2 = new Intent("android.intent.action.OPEN_DOCUMENT");
                intent2.addCategory("android.intent.category.OPENABLE");
                intent2.setType("image/*");
                startActivityForResult(intent2, 78);
                return;
            }
            if (id == 5) {
                NavPlus.phClear(applicationContext);
                refresh();
            } else if (id == 6) {
                NavPlus.phResetCrop(applicationContext);
                refresh();
            } else if (id == 99) {
                finish();
            } else if (id == 13) {
                startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mode = NavPlus.offMode;
        NavPlus.offMode = 0;
        Context applicationContext = getApplicationContext();
        GPlus.init(applicationContext);
        NavPlus.phCfg(applicationContext);
        try {
            Window window = getWindow();
            window.setStatusBarColor(-15855856);
            window.setNavigationBarColor(-15855856);
        } catch (Throwable unused) {
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        int dp = Ui2.dp(this, 20);
        linearLayout.setPadding(dp, Ui2.dp(this, 36), dp, dp);
        String str = "ÇEVRİMDIŞI HARİTA";
        int i = this.mode;
        if (i != 0) {
            str = "KONSOL DUVAR KAĞIDI";
            if (i == 2) {
                str = "SÜRÜŞ ÖZETİ";
            }
        }
        linearLayout.addView(Ui2.header(this, str));
        if (i == 0) {
            buildMap(linearLayout);
        } else if (i == 2) {
            buildRides(linearLayout);
        } else {
            buildWall(linearLayout);
        }
        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(-15855856);
        scrollView.addView(linearLayout);
        setContentView(scrollView);
        refresh();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        try {
            if (view.getId() != 4) {
                return false;
            }
            Bitmap bitmap = NavPlus.phBmp;
            int actionMasked = motionEvent.getActionMasked();
            int pointerCount = motionEvent.getPointerCount();
            float x = motionEvent.getX(0);
            float y = motionEvent.getY(0);
            if (actionMasked == 0) {
                ViewParent parent = view.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                this.lx = x;
                this.ly = y;
                this.ld = 0.0f;
                this.moved = false;
                return true;
            }
            if (actionMasked == 1 || actionMasked == 3) {
                NavPlus.phSaveCrop(getApplicationContext());
                if (actionMasked == 1 && !this.moved) {
                    onClick(view);
                    return true;
                }
            } else {
                if (actionMasked == 5) {
                    this.moved = true;
                    this.ld = span(motionEvent);
                    return true;
                }
                if (actionMasked == 6) {
                    this.ld = 0.0f;
                    this.lx = -1.0f;
                    return true;
                }
                if (actionMasked == 2 && bitmap != null) {
                    if (pointerCount >= 2) {
                        float span = span(motionEvent);
                        float f = this.ld;
                        if (f > 10.0f) {
                            NavPlus.phZoomBy(span / f);
                        }
                        this.ld = span;
                        this.lx = -1.0f;
                        this.moved = true;
                    } else {
                        float f2 = this.lx;
                        if (f2 < 0.0f) {
                            this.lx = x;
                            this.ly = y;
                            return true;
                        }
                        int width = view.getWidth();
                        int height = view.getHeight();
                        if (width > 0 && height > 0) {
                            float f3 = width;
                            float f4 = height;
                            float f5 = x - f2;
                            float f6 = y - this.ly;
                            if (Math.abs(f5) > 8.0f || Math.abs(f6) > 8.0f) {
                                this.moved = true;
                            }
                            if (this.moved) {
                                NavPlus.phPan(f5 / f3, f6 / f4);
                                this.lx = x;
                                this.ly = y;
                            }
                        }
                    }
                    this.iv.setImageBitmap(NavPlus.phPreview(640));
                }
            }
            return true;
        } catch (Throwable unused) {
            return true;
        }
    }

    public void refresh() {
        try {
            Context applicationContext = getApplicationContext();
            int i = this.mode;
            if (i == 0) {
                setStatus(NavPlus.mbStatus(applicationContext));
            } else {
                if (i == 2) {
                    return;
                }
                this.iv.setImageBitmap(NavPlus.phPreview(640));
                this.tv2.setText(NavPlus.phAccess(applicationContext));
            }
        } catch (Throwable unused) {
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            post(this, NavPlus.mbImport(this, this.pending));
        } catch (Throwable unused) {
        }
        NavPlus.mbBusy = false;
    }

    public void setStatus(String str) {
        try {
            TextView textView = this.tv;
            if (textView != null) {
                textView.setText(str);
                this.btn2.setText(NavPlus.mbToggleLabel(getApplicationContext()));
            }
        } catch (Throwable unused) {
        }
    }
}
