#!/usr/bin/env bash
# Emulator smoke test: install, launch, open the 4 bottom tabs, collect logcat + CrashGuard logs.
# Fails if logcat has a FATAL EXCEPTION for our process.
set -u
APK="$1"; PKG="${2:-com.wayfindr.app.preview}"; OUT="${3:-smoke-out}"
mkdir -p "$OUT"
adb logcat -c
adb install -r -g "$APK" | tee "$OUT/install.txt" || exit 1
# Test entry points only work with this marker in the app's private folder (run-as = adb only).
adb shell "run-as $PKG sh -c 'mkdir -p files && touch files/test_hooks'"
adb shell "run-as $PKG ls files/test_hooks" || echo "::error::test_hooks marker not created"
adb shell monkey -p "$PKG" -c android.intent.category.LAUNCHER 1
sleep 20
adb exec-out screencap -p > "$OUT/0-start.png"

# Console frames with fake turns (debug-only entry in MainActivity) for the turn-card design.
adb shell am start -n "$PKG/com.example.wayfindr.MainActivity" -a com.example.wayfindr.RENDER_NAV_CARD
sleep 15
adb pull "/sdcard/Android/data/$PKG/files/navcard.png" "$OUT/navcard.png" || echo "::warning::navcard.png not rendered"

# Dash frame path benchmark (debug-only entry): achieved fps + render/encode ms, old 4 fps vs now.
adb shell am start -n "$PKG/com.example.wayfindr.MainActivity" -a com.example.wayfindr.FRAME_BENCH
sleep 32
adb pull "/sdcard/Android/data/$PKG/files/framebench.txt" "$OUT/framebench.txt" && cat "$OUT/framebench.txt" || echo "::warning::framebench.txt not written"

find_xy() {  # print the centre of the node whose text or content-desc is $1
  adb shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1
  adb exec-out cat /sdcard/ui.xml > "$OUT/ui.xml"
  python3 - "$1" "$OUT/ui.xml" <<'PY'
import re, sys
label, xml = sys.argv[1], open(sys.argv[2], encoding='utf-8').read()
for node in re.findall(r'<node [^>]*>', xml):
    if f'text="{label}"' in node or f'content-desc="{label}"' in node:
        x1, y1, x2, y2 = map(int, re.search(r'bounds="\[(\d+),(\d+)\]\[(\d+),(\d+)\]"', node).groups())
        print((x1 + x2) // 2, (y1 + y2) // 2); break
else:
    print(f'not found: {label}', file=sys.stderr)
PY
}
tap() {  # a system dialog (e.g. the dash Wi-Fi "Connect to device") hides the tabs: dismiss it once
  xy=$(find_xy "$1"); [ -n "$xy" ] || { adb shell input keyevent KEYCODE_BACK; sleep 2; xy=$(find_xy "$1"); }
  [ -n "$xy" ] && adb shell input tap $xy
}
i=1
for t in "Rota" "Konsol" "Sürüşler" "Ana Sayfa"; do
  tap "$t"; sleep 6
  adb exec-out screencap -p > "$OUT/$i-$t.png"; i=$((i+1))
done

# Second pass like a real phone: GPS fix + notification access granted, cold start.
adb shell cmd notification allow_listener "$PKG/com.example.wayfindr.media.WayfindrNotificationListener" || true
adb emu geo fix 32.85 39.92
adb shell am force-stop "$PKG"
adb shell monkey -p "$PKG" -c android.intent.category.LAUNCHER 1
for n in 1 2 3 4; do adb emu geo fix 32.85$n 39.92$n; sleep 5; done
adb exec-out screencap -p > "$OUT/5-gps.png"
for t in "Rota" "Konsol" "Sürüşler" "Ana Sayfa"; do
  adb emu geo fix 32.86 39.93; tap "$t"; sleep 6
  adb exec-out screencap -p > "$OUT/$i-gps-$t.png"; i=$((i+1))
done

# Third pass: import a real GPX export (Super TripperDash) through "GPX içe aktar" and the system
# file picker, then open the ride detail, the full-screen map and the share sheet.
tap0() { xy=$(find_xy "$1"); [ -n "$xy" ] && adb shell input tap $xy; }  # no BACK retry: it would close the picker / detail
read -r SW SH <<<"$(adb shell wm size | sed -n 's/.*: \([0-9]*\)x\([0-9]*\).*/\1 \2/p' | tail -1)"
up() { adb shell input swipe $((SW/2)) $((SH*4/5)) $((SW/2)) $((SH/4)) 300; }
shot() { adb exec-out screencap -p > "$OUT/$i-$1.png"; i=$((i+1)); }
GPX=app/src/test/resources/gpx/xfitness.gpx
adb push "$GPX" /sdcard/Download/xfitness.gpx
adb shell am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///sdcard/Download/xfitness.gpx >/dev/null || true
tap "Sürüşler"; sleep 4
for n in 1 2 3; do up; done; sleep 1
tap0 "GPX içe aktar"; sleep 5; shot picker
if ! tap0 "xfitness.gpx"; then  # picker opened on Recent: go to Downloads via the roots drawer
  tap0 "Show roots" || tap0 "Kökleri göster"; sleep 2
  tap0 "Downloads" || tap0 "İndirilenler"; sleep 3
  tap0 "xfitness.gpx"
fi
sleep 6; shot after-import
if [ -z "$(find_xy 'AYRINTILARI GÖRÜNTÜLE')" ]; then
  echo "::warning::GPX import through the picker failed; seeding the ride straight into the app's files"
  adb shell input keyevent KEYCODE_BACK; adb shell am force-stop "$PKG"
  python3 - "$GPX" "$OUT/seed" <<'PY'
import math, os, re, sys, datetime
src, out = sys.argv[1], sys.argv[2]; os.makedirs(out, exist_ok=True)
pts = [(float(a), float(b), float(e), datetime.datetime.fromisoformat(t.replace('Z', '+00:00')).timestamp())
       for a, b, e, t in re.findall(r'lat="([^"]+)" lon="([^"]+)"><ele>([^<]+)</ele><time>([^<]+)</time>', open(src, encoding='utf-8').read())]
def d(p, q):
    la1, lo1, la2, lo2 = map(math.radians, (p[0], p[1], q[0], q[1]))
    h = math.sin((la2 - la1) / 2) ** 2 + math.cos(la1) * math.cos(la2) * math.sin((lo2 - lo1) / 2) ** 2
    return 12742000 * math.asin(math.sqrt(h))
def enc(v):
    v = ~(v << 1) if v < 0 else v << 1; s = ''
    while v >= 0x20: s += chr((0x20 | (v & 0x1f)) + 63); v >>= 5
    return s + chr(v + 63)
poly, plat, plng = '', 0, 0
for p in pts:
    la, lo = round(p[0] * 1e5), round(p[1] * 1e5); poly += enc(la - plat) + enc(lo - plng); plat, plng = la, lo
sp = [0] + [min(250, round(d(pts[i - 1], pts[i]) / max(1, pts[i][3] - pts[i - 1][3]) * 3.6)) for i in range(1, len(pts))]
dist = sum(d(pts[i - 1], pts[i]) for i in range(1, len(pts))); dur = int(pts[-1][3] - pts[0][3])
key = 'sp_%d_%d.bin' % (round(round(pts[0][0] * 1e5) / 1e5 * 1e5), round(round(pts[0][1] * 1e5) / 1e5 * 1e5))
open(f'{out}/{key}', 'wb').write(bytes(sp)); open(f'{out}/key', 'w').write(key)
eles = [p[2] for p in pts]; climb = sum(max(0, eles[i] - eles[i - 1]) for i in range(1, len(eles)))
open(f'{out}/stats', 'w').write(f'stopped=240\nclimb={round(climb)}\naltMin={round(min(eles))}\naltMax={round(max(eles))}\n')
open(f'{out}/sql', 'w').write("INSERT INTO ride(sid,start_ms,end_ms,distance_m,duration_s,avg_speed,max_speed,track,start_lat,start_lng,end_lat,end_lng) "
    f"VALUES('smoke-ride',{int(pts[0][3]*1000)},{int(pts[-1][3]*1000)},{dist},{dur},{dist/dur},{max(sp)/3.6},'{poly}',{pts[0][0]},{pts[0][1]},{pts[-1][0]},{pts[-1][1]});")
PY
  for f in "$(cat "$OUT/seed/key")" stats; do adb push "$OUT/seed/$f" "/data/local/tmp/$f"; done
  adb shell "run-as $PKG sh -c 'mkdir -p files/ride_speeds files/ride_stats; cp /data/local/tmp/$(cat "$OUT/seed/key") files/ride_speeds/; cp /data/local/tmp/stats files/ride_stats/smoke-ride.properties'"
  adb shell "run-as $PKG sqlite3 databases/wayfindr.db \"$(cat "$OUT/seed/sql")\"" || echo "::warning::sqlite3 seed failed"
  adb shell monkey -p "$PKG" -c android.intent.category.LAUNCHER 1; sleep 8
  tap0 "Sürüşler"; sleep 5; shot seeded
fi
tap0 "AYRINTILARI GÖRÜNTÜLE"; sleep 5; shot detail
up; sleep 2; shot detail-scrolled
adb shell input swipe $((SW/2)) $((SH/4)) $((SW/2)) $((SH*4/5)) 300; sleep 2
tap0 "Haritayı açmak için dokun"; sleep 5; shot fullmap
adb shell input keyevent KEYCODE_BACK; sleep 2
tap0 "Paylaş"; sleep 8; shot share-sheet
tap0 "Diğer"; sleep 4; shot share-chooser
adb shell "run-as $PKG sh -c 'cat cache/route_qr/*.png'" > "$OUT/share-image.png" 2>/dev/null || true
adb shell input keyevent KEYCODE_BACK; sleep 1; adb shell input keyevent KEYCODE_BACK; sleep 1

# Fourth pass: Türkiye çevrimdışı paketi. (a) The real one-button download (map + search index from
# the tr-map-latest manifest + 8 BRouter segments, ~1.1 GB, via DownloadManager) from the card in
# Ayarlar → Çevrimdışı harita.
# (b) Wi-Fi and mobile data off, then the debug OFFLINE_TEST entry routes Kızılay → Gölbaşı
# (Router → built-in BRouter) and draws console frames from the offline vector map. If the full
# download didn't finish, (b) runs on a small Ankara cut of the published map + one segment.
ui_has() { adb shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1; adb exec-out cat /sdcard/ui.xml | grep -q "$1"; }
ODIR="/sdcard/Android/data/$PKG/files/offline"
MANIFEST=https://github.com/yazilimvar-design/WayFindr/releases/download/tr-map-latest/manifest.json
curl -fsSL -o "$OUT/manifest.json" "$MANIFEST" && cat "$OUT/manifest.json"
adb shell monkey -p "$PKG" -c android.intent.category.LAUNCHER 1; sleep 4
tap "Ana Sayfa"; sleep 3
tap0 "Ayarlar"; sleep 4
for n in 1 2 3 4 5 6; do  # scroll until the card's button is on screen
  [ -n "$(find_xy 'Wi-Fi ile indirmeniz önerilir' 2>/dev/null)" ] && break; up; sleep 1
done; sleep 6
shot offline-card
tap0 "Türkiye çevrimdışı paketini indir"; sleep 25
shot offline-downloading
FULL=0
for n in $(seq 1 60); do  # up to ~10 min
  if ui_has 'Kurulu ·'; then FULL=1; break; fi
  ui_has 'İndirme başarısız' && break
  sleep 10
done
shot offline-installed
adb shell run-as "$PKG" cat shared_prefs/offline_pack.xml > "$OUT/offline_pack.xml" 2>/dev/null || true
shot settings-offline-section
adb shell ls -l "$ODIR/map" "$ODIR/search" "$ODIR/segments4" | tee "$OUT/offline-files.txt"
EXTRA=""
if [ "$FULL" != 1 ]; then
  echo "::warning::full offline package download did not finish; testing with an Ankara extract"
  PMV=1.31.2
  MAPURL=$(python3 -c 'import json,sys; print(json.load(open(sys.argv[1]))["url"])' "$OUT/manifest.json" 2>/dev/null)
  curl -fsSL -o /tmp/pmtiles.tgz "https://github.com/protomaps/go-pmtiles/releases/download/v$PMV/go-pmtiles_${PMV}_Linux_x86_64.tar.gz" \
    && tar xzf /tmp/pmtiles.tgz -C /tmp pmtiles
  /tmp/pmtiles extract "${MAPURL:-https://build.protomaps.com/20261117.pmtiles}" /tmp/ank.pmtiles --bbox=32.70,39.74,32.98,40.00 --maxzoom=14 \
    || echo "::warning::pmtiles extract failed"
  curl -fsSL -o /tmp/E30_N35.rd5 https://brouter.de/brouter/segments4/E30_N35.rd5 || echo "::warning::segment download failed"
  adb shell mkdir -p "$ODIR/map" "$ODIR/segments4"
  adb push /tmp/ank.pmtiles "$ODIR/map/ank.pmtiles"
  adb push /tmp/E30_N35.rd5 "$ODIR/segments4/E30_N35.rd5"
  EXTRA="--es map ank.pmtiles --es date 20261117"
  SURL=$(python3 -c 'import json,sys; print(json.load(open(sys.argv[1]))["search"]["url"])' "$OUT/manifest.json" 2>/dev/null)
  if [ -n "$SURL" ] && curl -fsSL -o /tmp/tr-search.sqlite "$SURL"; then
    adb shell mkdir -p "$ODIR/search"; adb push /tmp/tr-search.sqlite "$ODIR/search/tr-search.sqlite"
    EXTRA="$EXTRA --es search tr-search.sqlite"
  else echo "::warning::search index download failed"; fi
fi
adb shell svc wifi disable; adb shell svc data disable; sleep 8
adb shell dumpsys connectivity | grep -m3 -i "active default network" > "$OUT/offline-net.txt" || true
adb shell rm -f "/sdcard/Android/data/$PKG/files/offline-console.png"
adb shell am start -n "$PKG/com.example.wayfindr.MainActivity" -a com.example.wayfindr.OFFLINE_TEST $EXTRA
for n in $(seq 1 30); do
  sleep 5; adb shell ls "/sdcard/Android/data/$PKG/files/offline-console.png" >/dev/null 2>&1 && break
done
sleep 2
adb pull "/sdcard/Android/data/$PKG/files/offline-console.png" "$OUT/offline-console.png" || echo "::warning::offline-console.png not rendered"
adb pull "/sdcard/Android/data/$PKG/files/offline-route.txt" "$OUT/offline-route.txt" && cat "$OUT/offline-route.txt" || echo "::warning::offline-route.txt not written"
grep -q "source=BROUTER" "$OUT/offline-route.txt" 2>/dev/null || echo "::warning::offline route did not come from the built-in BRouter"

# Fifth pass, still with no network: the Rota tab like a rider. Offline search ("mamak devlet
# hastanesi", "kizilay") → pick → route (distance/time, no provider picker); save it, Temizle
# (search text must go too), saved place → offline route; then start navigation and save an
# active-navigation console frame with a fake now-playing, weather pill, turn card, speed sign.
( for n in $(seq 1 600); do adb emu geo fix 32.8541 39.9208 >/dev/null 2>&1; sleep 2; done ) & GEO=$!
ui_dump() { adb shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1; adb exec-out cat /sdcard/ui.xml > "$OUT/ui.xml"; }
find_sub() {  # centre of the first node whose text contains $1
  ui_dump
  python3 - "$1" "$OUT/ui.xml" <<'PY'
import re, sys, html
label, xml = sys.argv[1], open(sys.argv[2], encoding='utf-8').read()
for node in re.findall(r'<node [^>]*>', xml):
    m = re.search(r' text="([^"]*)"', node)
    if m and label in html.unescape(m.group(1)) and 'EditText' not in node:
        x1, y1, x2, y2 = map(int, re.search(r'bounds="\[(\d+),(\d+)\]\[(\d+),(\d+)\]"', node).groups())
        print((x1 + x2) // 2, (y1 + y2) // 2); break
PY
}
field_text() { ui_dump; python3 - "$OUT/ui.xml" <<'PY'
import re, sys, html
xml = open(sys.argv[1], encoding='utf-8').read()
for node in re.findall(r'<node [^>]*>', xml):
    if 'EditText' in node:
        print(html.unescape(re.search(r' text="([^"]*)"', node).group(1))); break
PY
}
sheet_up() { adb shell input swipe $((SW/2)) $((SH*78/100)) $((SW/2)) $((SH*52/100)) 300; sleep 1; }
sheet_down() { adb shell input swipe $((SW/2)) $((SH*52/100)) $((SW/2)) $((SH*78/100)) 300; sleep 1; }
has_route() { ui_dump; grep -qE 'text="[0-9][0-9,.]* km' "$OUT/ui.xml"; }
sheet_find() { for n in 1 2 3 4 5 6 7 8; do [ -n "$(find_xy "$1" 2>/dev/null)" ] && return 0; sheet_up; done; return 1; }
type_search() {  # $1 = words separated by spaces
  xy=$(find_xy "Yer ara" 2>/dev/null); [ -n "$xy" ] || xy=$(find_xy "Durak ekle" 2>/dev/null)
  [ -n "$xy" ] && adb shell input tap $xy; sleep 1
  adb shell input text "$(echo "$1" | sed 's/ /%s/g')"; sleep 6
}
wait_route() {
  for n in $(seq 1 30); do sleep 3; ui_dump; grep -q 'Rota aranıyor' "$OUT/ui.xml" || break; done; sleep 2
}
adb shell am force-stop "$PKG"; adb shell monkey -p "$PKG" -c android.intent.category.LAUNCHER 1; sleep 8
tap "Rota"; sleep 5
type_search "mamak devlet hastanesi"; adb shell input keyevent KEYCODE_BACK; sleep 2; shot offline-search-mamak
xy=$(find_xy "Aramayı temizle"); [ -n "$xy" ] && adb shell input tap $xy; sleep 2
type_search "kizilay"; adb shell input keyevent KEYCODE_BACK; sleep 2; shot offline-search-kizilay
grep -o 'text="[^"]*"' "$OUT/ui.xml" | head -60 > "$OUT/offline-search-kizilay.txt"
xy=$(find_xy "Aramayı temizle"); [ -n "$xy" ] && adb shell input tap $xy; sleep 2
type_search "mamak devlet hastanesi"
adb shell input keyevent KEYCODE_BACK; sleep 2   # hide the keyboard, keep the suggestions
xy=$(find_sub "Mamak Devlet Hastanesi")
if [ -n "$xy" ]; then adb shell input tap $xy; else echo "::warning::offline suggestion 'Mamak Devlet Hastanesi' not found"; fi
wait_route; shot offline-route-picked
sheet_up; sheet_up; shot offline-route-stats
ui_dump; grep -o 'text="[^"]*"' "$OUT/ui.xml" > "$OUT/offline-route-ui.txt"
sheet_up; sheet_up; ui_dump; grep -o 'text="[^"]*"' "$OUT/ui.xml" >> "$OUT/offline-route-ui.txt"; shot offline-route-bottom
grep -qi 'sağlayıcı' "$OUT/offline-route-ui.txt" && echo "::error::provider selector still on the Rota tab"
grep -qE 'text="[0-9][0-9,.]* km' "$OUT/offline-route-ui.txt" && echo "offline route on Rota tab: OK ($(grep -oE 'text="[0-9][0-9,.]* km[^"]*"' "$OUT/offline-route-ui.txt" | head -3 | tr '\n' ' '))" \
  || echo "::warning::no route distance on the Rota tab offline"
grep -o 'text="BR ·[^"]*"' "$OUT/offline-route-ui.txt" | head -1
# Save it, then Temizle: route, target and search text must all go.
sheet_find "Bu hedefi kaydet"; tap0 "Bu hedefi kaydet"; sleep 2; tap0 "Kaydet"; sleep 2
for n in 1 2 3 4 5 6 7 8; do sheet_down; done
tap0 "Temizle"; sleep 3; shot temizle
FT=$(field_text); echo "after Temizle: search field='$FT'" | tee "$OUT/temizle.txt"
case "$FT" in *Mamak*) echo "::warning::Temizle left the search text" | tee -a "$OUT/temizle.txt";; esac
grep -q 'Hedef seçilmedi' "$OUT/ui.xml" && echo "after Temizle: HEDEF card cleared: OK" | tee -a "$OUT/temizle.txt" \
  || echo "::warning::Temizle left the HEDEF card" | tee -a "$OUT/temizle.txt"
# Map tap → offline route.
adb shell input tap $((SW*3/4)) $((SH/5)); wait_route; shot offline-maptap-route
for n in 1 2 3 4; do has_route && break; sheet_up; done
has_route && echo "offline route from map tap: OK" || echo "::warning::map tap gave no offline route"
for n in 1 2 3 4 5 6 7 8; do sheet_down; done
tap0 "Temizle"; sleep 2
# Saved place → offline route, then "Navigasyonu başlat": active navigation.
sheet_find "Mamak Devlet Hastanesi"; xy=$(find_sub "Mamak Devlet Hastanesi")
[ -n "$xy" ] && adb shell input tap $xy || echo "::warning::saved place not found"
wait_route; shot offline-saved-route
for n in 1 2 3 4; do has_route && break; sheet_up; done
has_route && echo "offline route from saved place: OK" || echo "::warning::saved place gave no offline route"
sheet_find "Navigasyonu başlat"; tap0 "Navigasyonu başlat"; sleep 10; shot nav-started
adb shell input keyevent KEYCODE_BACK; sleep 1   # a Wi-Fi "connect to device" dialog, if any
BR="adb shell am broadcast -a com.wayfindr.DASH_BUTTON -p $PKG"
# (extras with spaces must be quoted again for the device shell)
$BR --es np_title "'Bir Derdim Var'" --es np_artist "'mor ve ötesi'" --ez fakehud true; sleep 2
$BR --ez snap true --es snapname nav-music; sleep 10   # first frame asks for the offline tiles
$BR --ez fakehud true; sleep 1
$BR --ez dump true --ez snap true --es snapname nav-music; sleep 3
adb logcat -d -s DashViewModel | grep "DEBUG stops" | tail -1
$BR --ez toggles true; sleep 3
adb pull "/sdcard/Android/data/$PKG/files/toggles.txt" "$OUT/toggles.txt" && cat "$OUT/toggles.txt" || echo "::warning::toggles.txt not written"
# Same frame with "Radar ve hız limiti" off (no sign, no camera pill) and with "Haritada şarkı ve
# sanatçı" off (no music card).
$BR --ez fakehud true --es set_safety false; sleep 1
$BR --ez snap true --es snapname nav-radar-off; sleep 2
$BR --es set_safety true --es set_mapmus false --ez fakehud true; sleep 1
$BR --ez snap true --es snapname nav-music-off; sleep 2
$BR --es set_mapmus true --ez np_clear true; sleep 1
for f in nav-music nav-radar-off nav-music-off; do
  adb pull "/sdcard/Android/data/$PKG/files/diag/$f.png" "$OUT/$f.png" || echo "::warning::$f.png not rendered"
done
# Fuel stop: pause / resume / end navigation from the console menu (joystick codes: 21 = DOWN 0x15,
# 19 = RIGHT 0x13, 20 = LEFT 0x14) and from the app's Konsol tab. A test wallpaper stands in for
# "Fotoğrafımı konsolda göster"; fake_ride puts ~500 m into the recording so "bitir" saves a ride.
dumpnav() { $BR --ez dump true; sleep 2; adb logcat -d -s DashViewModel | grep "DEBUG stops" | tail -1 | tee -a "$OUT/nav-pause.txt"; }
menu_open() { $BR --ei code 21; sleep 1; $BR --ei code 21; sleep 3; }
snapf() { $BR --ez snap true --es snapname "$1"; sleep 3; adb pull "/sdcard/Android/data/$PKG/files/diag/$1.png" "$OUT/$1.png" || echo "::warning::$1.png not rendered"; }
: > "$OUT/nav-pause.txt"
$BR --ez test_wallpaper true --ez fake_ride true; sleep 2
dumpnav
tap0 "Konsol"; sleep 4; shot app-nav-buttons      # one row: "Duraklat" | "Bitir"
ui_dump; grep -o 'text="\(Duraklat\|Bitir\)"[^>]*bounds="[^"]*"' "$OUT/ui.xml" | tee -a "$OUT/nav-pause.txt"
menu_open; snapf nav-menu-pill-duraklat; dumpnav  # top pill row, "Duraklat" highlighted
$BR --ei code 21; sleep 2; snapf nav-menu-pill-bitir   # DOWN: cursor to the "Bitir" pill
dumpnav | grep -q "menu=ROOT@1" && echo "DOWN moves to the Bitir pill: OK" | tee -a "$OUT/nav-pause.txt" \
  || echo "::warning::DOWN did not move to the Bitir pill" | tee -a "$OUT/nav-pause.txt"
grep -q "menu=ROOT@0" "$OUT/nav-pause.txt" && echo "menu opens on the Duraklat pill: OK" | tee -a "$OUT/nav-pause.txt" \
  || echo "::warning::menu did not open on the Duraklat pill" | tee -a "$OUT/nav-pause.txt"
$BR --ei code 20; sleep 2          # LEFT closes the menu
menu_open
$BR --ei code 19; sleep 3          # pill 0: "Duraklat"
snapf nav-paused-wallpaper; dumpnav
grep -q "paused=true wallHold=true" "$OUT/nav-pause.txt" && echo "pause from console menu: OK" | tee -a "$OUT/nav-pause.txt" \
  || echo "::error::console menu did not pause navigation" | tee -a "$OUT/nav-pause.txt"
$BR --ez clear_wallpaper true; sleep 1
snapf nav-paused-pill
tap0 "Konsol"; sleep 4; shot app-nav-paused
tap0 "Devam et"; sleep 4; shot app-nav-resumed   # resume from the phone
snapf nav-resumed; dumpnav
tail -1 "$OUT/nav-pause.txt" | grep -q "paused=false" && echo "resume from the app: OK" | tee -a "$OUT/nav-pause.txt" \
  || echo "::error::app button did not resume navigation" | tee -a "$OUT/nav-pause.txt"
$BR --ez test_wallpaper true --ez fake_ride true; sleep 2
menu_open; $BR --ei code 21; sleep 2; snapf nav-menu-end   # cursor on the "Bitir" pill
$BR --ei code 19; sleep 4
snapf nav-ended-wallpaper; dumpnav
tail -1 "$OUT/nav-pause.txt" | grep -q "stops=\[\].*wallHold=true rideEnded=true recording=false" && echo "end from console menu: OK" | tee -a "$OUT/nav-pause.txt" \
  || echo "::error::console menu did not end navigation" | tee -a "$OUT/nav-pause.txt"
adb logcat -d -s DashViewModel | grep "Ride saved" | tail -2 | tee -a "$OUT/nav-pause.txt"
adb shell run-as "$PKG" cat shared_prefs/ride_names.xml | tee "$OUT/ride_names.xml" | tee -a "$OUT/nav-pause.txt"
grep -q "Mamak" "$OUT/ride_names.xml" && echo "ride saved under the destination name: OK" | tee -a "$OUT/nav-pause.txt" \
  || echo "::warning::no ride named after the destination" | tee -a "$OUT/nav-pause.txt"
$BR --ez clear_wallpaper true; sleep 1
tap0 "Sürüşler"; sleep 5; shot rides-after-end
# A Google Maps short link with no internet: a clear message, not silence. (Last: it opens a
# second MainActivity, whose view models would also answer the debug broadcasts above.)
adb shell am start -a android.intent.action.SEND -t text/plain --es android.intent.extra.TEXT "https://maps.app.goo.gl/9wMHSeJvD6xQZbH7A" -n "$PKG/com.example.wayfindr.MainActivity"
sleep 8; shot offline-shared-link
ui_dump; grep -o 'text="[^"]*bağlantı[^"]*"' "$OUT/ui.xml" | tee "$OUT/offline-shared-link.txt"
kill $GEO 2>/dev/null
adb shell svc wifi enable; adb shell svc data enable; sleep 10

# Online radar / speed limit: the live Overpass fetch the app does on the road, at known Ankara
# spots, with the switch on and off (debug SAFETY_TEST → files/safety.txt).
adb shell am start -n "$PKG/com.example.wayfindr.MainActivity" -a com.example.wayfindr.SAFETY_TEST
for n in $(seq 1 90); do sleep 5; adb shell ls "/sdcard/Android/data/$PKG/files/safety.txt" >/dev/null 2>&1 && break; done
adb pull "/sdcard/Android/data/$PKG/files/safety.txt" "$OUT/safety.txt" && cat "$OUT/safety.txt" || echo "::warning::safety.txt not written"
# The same Overpass query from the runner itself, for comparison.
for S in overpass-api.de overpass.private.coffee; do
  curl -4 -s -m 60 -o "$OUT/overpass-$S.json" -w "runner $S: HTTP %{http_code} %{time_total}s %{size_download} B\n" -A "Wayfindr-CI/1.0" \
    --data-urlencode 'data=[out:json][timeout:20];(node(around:6000,39.9208,32.8541)["highway"="speed_camera"];way(around:40,39.90795,32.77297)["highway"]["maxspeed"];);out tags 20;' \
    "https://$S/api/interpreter" | tee -a "$OUT/safety.txt"
done

# Place search centres (needs internet): "Mamak", "Çankaya", "Keçiören", "Kızılay" and a POI through
# the Rota tab's suggestion + selection path (debug SEARCH_TEST → files/search-centre.txt). An
# area must land on the settlement's centre, not its boundary's middle (Mamak ≤ 3 km of 39.93, 32.91).
adb shell am start -n "$PKG/com.example.wayfindr.MainActivity" -a com.example.wayfindr.SEARCH_TEST
for n in $(seq 1 60); do sleep 3; adb shell ls "/sdcard/Android/data/$PKG/files/search-centre.txt" >/dev/null 2>&1 && break; done
if adb pull "/sdcard/Android/data/$PKG/files/search-centre.txt" "$OUT/search-centre.txt"; then
  cat "$OUT/search-centre.txt"
  grep -q "uzaklık.*HATA" "$OUT/search-centre.txt" && echo "::error::'Mamak' destination is not the town centre"
  grep -q "uzaklık.*UYARI" "$OUT/search-centre.txt" && echo "::warning::'Mamak' centre not found online (Photon/Nominatim unreachable?)"
  grep -q "uzaklık.*OK" "$OUT/search-centre.txt" || echo "::warning::'Mamak' centre check did not run"
else echo "::warning::search-centre.txt not written"; fi

adb shell pidof "$PKG" > "$OUT/pid.txt" && echo "process alive: $(cat $OUT/pid.txt)" || echo "process NOT running"
adb logcat -d > "$OUT/logcat.txt"
adb pull "/sdcard/Android/data/$PKG/files/diag" "$OUT/diag" 2>/dev/null || true
adb shell run-as "$PKG" ls -R files > "$OUT/files-ls.txt" 2>&1 || true

if grep -A40 "FATAL EXCEPTION" "$OUT/logcat.txt" | grep -q "Process: $PKG"; then
  grep -A60 "FATAL EXCEPTION" "$OUT/logcat.txt"; echo "::error::FATAL EXCEPTION in $PKG"; exit 1
fi
pidof_ok=$(cat "$OUT/pid.txt" 2>/dev/null)
[ -n "$pidof_ok" ] || { echo "::error::app process died"; grep -E "AndroidRuntime|$PKG" "$OUT/logcat.txt" | tail -80; exit 1; }
grep -q "uzaklık.*HATA" "$OUT/search-centre.txt" 2>/dev/null && exit 1
echo "smoke OK"
