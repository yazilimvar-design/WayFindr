# Himican450 — eski APK eklemelerini kaynağa taşıma

Bu dosya, "Himalayan450 Casa Brown OSM v34" APK'sındaki (WayFindr 1.3.2 tabanlı, bytecode
yamalarıyla değiştirilmiş) değişikliklerin bu kaynak koda (WayFindr 1.3.4 fork'u) taşınması
için başvuru kaydıdır. Her yeni oturum buradan devam eder.

## Nasıl çıkarıldı
- Upstream `63d3112` (1.3.2, tag WayfindrV1.3.2) `himican/upstream-132` dalında CI ile
  debug olarak derlendi (minify yok).
- v34 APK ile sınıf/metot bazında karşılaştırıldı (`tools/sig.py`): aynı derleyici çıktısı
  verdiği için değişmeyen sınıflar birebir eşleşiyor, farklar gerçek değişiklik.
- Sabit metin farkları: `string-diff-v34-vs-1.3.2.txt` (`tools/strdiff.py`).
- Metot farkları: `method-diff-v34-vs-1.3.2.txt`.
- Araçlar (`tools/dex.py`): saf Python dex okuyucu/disassembler; Android SDK gerekmez.
  `python3 tools/dump.py OUT classes2.dex ...` sınıfları metne döker.

## v34'te KALDIRILMIŞ upstream özellikleri
Garaj/bakım (GarageScreen, GarageViewModel, MaintenanceNotifier), Canlı veri ekranı
(LiveDataScreen), upstream MbTiles/OfflineMapFiles/OfflineMapStyle, MapSource enum.
→ Kullanıcıya sorulacak: geri gelsin mi?

## v34'te EKLENMİŞ / değiştirilmiş alanlar (gruplar)
- Tema: MapTheme (Casa Brown renkleri), StylePlus, OsmMapStyle
- Konum: LocPlus (preferReject, stillLock, fused), LocationTracker, NavigationReliability,
  OffRouteConfirmation, RouteProgressMatcher
- Navigasyon/arama: NavPlus (konsol HUD, duvar kağıdı + müzik kartı, hava, benzinlik,
  yer arama), GPlus (Google rota/arama), SearchPlus, Router, FreeRouter, OfflineRouter,
  PlaceSearch, RouteAlternativeStyle
- Çevrimdışı harita: OfflineMapActivity (.mbtiles içe aktarma), TileProvider
- Türkçe arayüz: AppNavigation, DashScreen, RouteScreen, SettingsScreen, HomeScreen,
  RidesScreen, Ui2, DashWifiManager, DashKeepAliveService ...
- Medya: MediaInfoProvider (albüm kapağı)
- v35–v38 bytecode düzeltmeleri (bu oturumda yapıldı, kaynakta da uygulanacak):
  * Müzik çalarken arka plan daima duvar kağıdı (phDraw albüm-kapağı dalı kapalı)
  * Konsola "WayfindR" yerine uygulama adı
  * Konsola musicActive/musicPlaying = false (konsolun yuvarlak müzik balonu çıkmasın)
  * Müzik şeridi → navigasyon kartı tarzı yuvarlak köşeli kart (alt %68–90, kapak solda)
  * network sağlayıcı konumları son GPS/fused konumundan sonra 120 sn yok sayılır
  * (v38: bilinmeyen manevra glif 9→255; kullanıcı topun kalmasını tercih etti → UYGULANMAYACAK)

## Kararlar (Play Store planı, 2026-11-15)
Ad Himican450 · paket io.github.<hesap>.himican450 · kişisel hesap, geliştirici adı
"Kılavuz Moto" · ücretsiz/reklamsız · destek yazilimvar@gmail.com · varsayılan OSM,
Google için kullanıcı kendi anahtarı · Hakkında: WayFindr teşekkür + MIT · "Royal Enfield
ile bağlantılı değildir" · arka plan konum izni kaldırılır · veri toplanmaz · ilk açılış
konsol uyarısı · Play App Signing · yalnız Türkiye · 12 kişi/14 gün kapalı test ·
sürüş kaydı + Google Navigation SDK yayından önce · GitHub'dan APK dağıtılmaz.

## Durum (2026-11-15)
- Taban: v34 = upstream `63d3112` (1.3.2 + QR paylaşımı) üzerine. Bu dal (`himican/v34-src`)
  o commit'ten başlar; önce v34 birebir kaynağa çevrilecek, sonra upstream 1.3.3/1.3.4
  `git merge` ile alınacak, sonra Himican450 adı/paket ve yeni özellikler.
- `docs/himican/v34-java/`: v34 APK'nın jadx çıktısı (com.example.wayfindr). Plus sınıfları
  (NavPlus, GPlus, LocPlus, SearchPlus, StylePlus, Ui2, OfflineMapActivity) aslen Java;
  MapTheme, NavigationReliability, OffRouteConfirmation, RouteProgressMatch(er),
  RouteAlternativeStyle, OsmMapStyle, InternetNetwork aslen Kotlin.
- `docs/himican/v34-vs-1.3.2.java.diff`: upstream 1.3.2 debug derlemesinin jadx çıktısı ile
  v34'ün jadx çıktısı arasındaki fark. En büyük değişiklikler: DashScreen, RouteScreen
  (Compose, büyük ölçüde yeniden yazılmış), DashViewModel, FreeWayfindrMap, SettingsScreen,
  AppNavigation, Router, RidesScreen, LocationTracker, FreeRouter, TileProvider, PlaceSearch.
- Karar: Garaj, Canlı veri ekranı, upstream MbTiles GERİ GELMİYOR (v34'teki gibi kaldırılmış).
- Sürüş kaydı tasarımı onaylandı (konsol özeti tasarımı: Design artifact "Himican450 Sürüş Kaydı").
- DİKKAT: v34'te 30 sınıfın iki kopyası var; çalışan kopya classes2.dex'teki. `v34-java/` artık
  classes2 kopyalarıyla düzeltildi (önceki sürüm gölgede kalan eski kopyaları gösteriyordu).
