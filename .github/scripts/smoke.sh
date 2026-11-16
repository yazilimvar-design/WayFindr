#!/usr/bin/env bash
# Emulator smoke test: install, launch, open the 4 bottom tabs, collect logcat + CrashGuard logs.
# Fails if logcat has a FATAL EXCEPTION for our process.
set -u
APK="$1"; PKG=com.wayfindr.app.preview; OUT=smoke-out
mkdir -p "$OUT"
adb logcat -c
adb install -r -g "$APK" | tee "$OUT/install.txt" || exit 1
adb shell monkey -p "$PKG" -c android.intent.category.LAUNCHER 1
sleep 20
adb exec-out screencap -p > "$OUT/0-start.png"

tap() {  # tap the node whose text or content-desc is $1
  adb shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1
  adb exec-out cat /sdcard/ui.xml > "$OUT/ui.xml"
  python3 - "$1" "$OUT/ui.xml" <<'PY' | xargs -r adb shell input tap
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
i=1
for t in "Rota" "Konsol" "Sürüşler" "Ana Sayfa"; do
  tap "$t"; sleep 6
  adb exec-out screencap -p > "$OUT/$i-$t.png"; i=$((i+1))
done

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
