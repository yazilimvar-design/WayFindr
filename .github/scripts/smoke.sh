#!/usr/bin/env bash
# Emulator smoke test: install, launch, open the 4 bottom tabs, collect logcat + CrashGuard logs.
# Fails if logcat has a FATAL EXCEPTION for our process.
set -u
APK="$1"; PKG="${2:-com.wayfindr.app.preview}"; OUT="${3:-smoke-out}"
mkdir -p "$OUT"
adb logcat -c
adb install -r -g "$APK" | tee "$OUT/install.txt" || exit 1
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

# Fourth pass: Türkiye çevrimdışı paketi. (a) The real one-button download (map from the
# tr-map-latest manifest + 8 BRouter segments, ~1.1 GB, via DownloadManager) from the Konsol card.
# (b) Wi-Fi and mobile data off, then the debug OFFLINE_TEST entry routes Kızılay → Gölbaşı
# (Router → built-in BRouter) and draws console frames from the offline vector map. If the full
# download didn't finish, (b) runs on a small Ankara cut of the published map + one segment.
ui_has() { adb shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1; adb exec-out cat /sdcard/ui.xml | grep -q "$1"; }
ODIR="/sdcard/Android/data/$PKG/files/offline"
MANIFEST=https://github.com/yazilimvar-design/WayFindr/releases/download/tr-map-latest/manifest.json
curl -fsSL -o "$OUT/manifest.json" "$MANIFEST" && cat "$OUT/manifest.json"
adb shell monkey -p "$PKG" -c android.intent.category.LAUNCHER 1; sleep 4
tap "Konsol"; sleep 4
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
adb shell ls -l "$ODIR/map" "$ODIR/segments4" | tee "$OUT/offline-files.txt"
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
for f in label-file label-asset label-style; do adb pull "/sdcard/Android/data/$PKG/files/$f.png" "$OUT/$f.png" || true; done
adb pull "/sdcard/Android/data/$PKG/files/offline-route.txt" "$OUT/offline-route.txt" && cat "$OUT/offline-route.txt" || echo "::warning::offline-route.txt not written"
grep -q "source=BROUTER" "$OUT/offline-route.txt" 2>/dev/null || echo "::warning::offline route did not come from the built-in BRouter"
adb shell svc wifi enable; adb shell svc data enable; sleep 5

adb shell pidof "$PKG" > "$OUT/pid.txt" && echo "process alive: $(cat $OUT/pid.txt)" || echo "process NOT running"
adb logcat -d > "$OUT/logcat.txt"
adb pull "/sdcard/Android/data/$PKG/files/diag" "$OUT/diag" 2>/dev/null || true
adb shell run-as "$PKG" ls -R files > "$OUT/files-ls.txt" 2>&1 || true

if grep -A40 "FATAL EXCEPTION" "$OUT/logcat.txt" | grep -q "Process: $PKG"; then
  grep -A60 "FATAL EXCEPTION" "$OUT/logcat.txt"; echo "::error::FATAL EXCEPTION in $PKG"; exit 1
fi
pidof_ok=$(cat "$OUT/pid.txt" 2>/dev/null)
[ -n "$pidof_ok" ] || { echo "::error::app process died"; grep -E "AndroidRuntime|$PKG" "$OUT/logcat.txt" | tail -80; exit 1; }
echo "smoke OK"
