#!/usr/bin/env python3
"""Türkiye offline search index for the Himalayan 450 app ("Türkiye çevrimdışı paketi").

Reads an OpenStreetMap extract (Geofabrik turkey-latest.osm.pbf, ideally pre-filtered with
`osmium tags-filter`, see tr-map.yml) and writes a compact SQLite file:

  place(id, name, kind, rank, lat, lon, area)   lat/lon as integers (degrees * 1e5)
  kind(id, label)                               Turkish type label ("Hastane", "Sokak/Cadde", …)
  area(id, label)                               containing ilçe and il ("Mamak, Ankara")
  s  FTS4, contentless, columns (name, area)    normalized tokens, docid = place.id
  meta(k, v)                                    date, entries, version

Text is normalized the same way as the app (OfflineSearch.norm): lower case, Turkish letters
folded to ASCII (İ/ı→i, ş→s, ğ→g, ü→u, ö→o, ç→c, other accents dropped), anything that is not
a-z/0-9 becomes a space. FTS4's simple tokenizer then splits on spaces, so the app can run
case/diacritic-insensitive prefix queries ("kizilay*", "name:mamak* name:devlet*").

Entries: named POIs (amenity, shop, tourism, leisure, healthcare, office, government, historic,
aeroway, railway stations, a few natural features), places (il, ilçe, şehir…köy, semt/mahalle),
and named streets, one point per street name per ilçe and ~1.5 km cluster.

Usage: tr_search.py <in.osm.pbf> <out.sqlite> <YYYYMMDD>
"""
import math
import os
import re
import sqlite3
import sys
import time
import unicodedata

import osmium
import shapely
from shapely import wkb as shp_wkb

T0 = time.time()


def log(*a):
    print(f"[{time.time() - T0:7.1f}s]", *a, flush=True)


_non_alnum = re.compile(r"[^a-z0-9]+")
_marks = re.compile(r"[̀-ͯ]+")


def norm(s: str) -> str:
    s = unicodedata.normalize("NFD", s.lower())
    s = _marks.sub("", s).replace("ı", "i").replace("ß", "ss")
    return _non_alnum.sub(" ", s).strip()


# ── What is indexed, and how it is labelled ─────────────────────────────────────────────────
AMENITY = {
    "hospital": "Hastane", "clinic": "Sağlık merkezi", "doctors": "Doktor", "dentist": "Diş hekimi",
    "pharmacy": "Eczane", "fuel": "Akaryakıt", "charging_station": "Şarj istasyonu",
    "school": "Okul", "university": "Üniversite", "college": "Yüksekokul", "kindergarten": "Anaokulu",
    "police": "Polis", "fire_station": "İtfaiye", "townhall": "Belediye", "courthouse": "Adliye",
    "post_office": "PTT", "bank": "Banka", "restaurant": "Restoran", "cafe": "Kafe",
    "fast_food": "Yemek", "pub": "Bar", "bar": "Bar", "place_of_worship": "İbadet yeri",
    "parking": "Otopark", "motorcycle_parking": "Motosiklet otoparkı", "bus_station": "Otogar",
    "ferry_terminal": "İskele", "marketplace": "Pazar yeri", "library": "Kütüphane",
    "cinema": "Sinema", "theatre": "Tiyatro", "arts_centre": "Kültür merkezi",
    "community_centre": "Kültür merkezi", "social_facility": "Sosyal tesis", "veterinary": "Veteriner",
    "car_wash": "Oto yıkama", "car_rental": "Araç kiralama", "vehicle_inspection": "Araç muayene",
    "embassy": "Büyükelçilik", "prison": "Cezaevi", "public_building": "Kamu binası",
    "driving_school": "Sürücü kursu", "nursing_home": "Huzurevi", "food_court": "Yemek",
    "ice_cream": "Dondurma", "events_venue": "Etkinlik alanı", "conference_centre": "Kongre merkezi",
    "exhibition_centre": "Fuar alanı", "grave_yard": "Mezarlık", "shelter": "Barınak",
    "toilets": None, "bench": None, "waste_basket": None, "drinking_water": None, "atm": None,
    "fountain": "Çeşme", "townhall;community_centre": "Belediye",
}
SHOP = {
    "supermarket": "Market", "convenience": "Bakkal", "bakery": "Fırın", "butcher": "Kasap",
    "car_repair": "Oto tamir", "car": "Otomobil bayi", "car_parts": "Oto yedek parça",
    "motorcycle": "Motosiklet", "motorcycle_repair": "Motosiklet tamir", "tyres": "Lastikçi",
    "mall": "AVM", "department_store": "Mağaza", "clothes": "Giyim", "shoes": "Ayakkabı",
    "hardware": "Hırdavat", "doityourself": "Yapı market", "electronics": "Elektronik",
    "mobile_phone": "Telefoncu", "hairdresser": "Kuaför", "beauty": "Güzellik", "optician": "Optik",
    "greengrocer": "Manav", "kiosk": "Büfe", "pastry": "Pastane", "confectionery": "Şekerci",
    "jewelry": "Kuyumcu", "furniture": "Mobilya", "books": "Kitapçı", "stationery": "Kırtasiye",
    "florist": "Çiçekçi", "gift": "Hediyelik", "pet": "Pet shop", "sports": "Spor",
    "outdoor": "Kamp malzemesi", "bicycle": "Bisiklet", "chemist": "Kozmetik", "cosmetics": "Kozmetik",
}
TOURISM = {
    "hotel": "Otel", "motel": "Motel", "guest_house": "Pansiyon", "hostel": "Hostel",
    "apartment": "Apart", "camp_site": "Kamp alanı", "caravan_site": "Karavan alanı",
    "picnic_site": "Piknik alanı", "museum": "Müze", "attraction": "Gezilecek yer",
    "viewpoint": "Seyir noktası", "gallery": "Galeri", "zoo": "Hayvanat bahçesi",
    "theme_park": "Lunapark", "information": None, "artwork": "Sanat eseri", "chalet": "Dağ evi",
    "alpine_hut": "Dağ evi", "wilderness_hut": "Dağ evi", "aquarium": "Akvaryum",
}
LEISURE = {
    "park": "Park", "stadium": "Stadyum", "sports_centre": "Spor merkezi",
    "fitness_centre": "Spor salonu", "swimming_pool": "Yüzme havuzu", "golf_course": "Golf sahası",
    "marina": "Marina", "nature_reserve": "Tabiat parkı", "water_park": "Su parkı",
    "garden": "Bahçe", "pitch": "Spor sahası", "playground": None, "track": "Pist",
    "ice_rink": "Buz pisti", "beach_resort": "Plaj", "picnic_table": None, "dog_park": "Park",
}
HISTORIC = "Tarihi yer"
NATURAL = {"peak": "Dağ", "volcano": "Dağ", "beach": "Plaj", "bay": "Koy", "cape": "Burun",
           "spring": "Kaynak", "cave_entrance": "Mağara", "water": "Göl", "glacier": None,
           "saddle": "Geçit", "waterfall": "Şelale"}
WATERWAY = {"waterfall": "Şelale", "dam": "Baraj"}
PLACE = {  # label, rank (lower = more important)
    "city": ("Şehir", 1), "town": ("Kasaba", 3), "village": ("Köy", 5), "hamlet": ("Mezra", 6),
    "suburb": ("Semt", 4), "quarter": ("Mahalle", 5), "neighbourhood": ("Mahalle", 5),
    "locality": ("Mevki", 7), "isolated_dwelling": ("Yer", 8), "island": ("Ada", 4),
    "islet": ("Ada", 6), "province": ("İl", 0), "district": ("İlçe", 2), "municipality": ("Belde", 4),
    "borough": ("İlçe", 2), "plot": None, "farm": ("Çiftlik", 8), "square": ("Meydan", 6),
}
STREETS = {"motorway": "Otoyol", "trunk": "Ana yol", "primary": "Ana yol", "secondary": "Cadde",
           "tertiary": "Cadde", "unclassified": "Yol", "residential": "Sokak", "living_street": "Sokak",
           "pedestrian": "Yaya yolu", "service": "Yol", "road": "Yol", "track": None}
RANK_POI = 6
RANK_STREET = 7


def poi_kind(t):
    """(label, rank) for a POI/place tag set, or None."""
    v = t.get("place")
    if v and v in PLACE and PLACE[v]:
        return PLACE[v]
    v = t.get("amenity")
    if v:
        if v == "place_of_worship":
            rel = t.get("religion")
            return ("Cami" if rel == "muslim" else "Kilise" if rel == "christian" else
                    "Sinagog" if rel == "jewish" else "İbadet yeri"), RANK_POI
        if v in AMENITY:
            return (AMENITY[v], RANK_POI) if AMENITY[v] else None
        return "Yer", RANK_POI
    v = t.get("healthcare")
    if v:
        return ("Hastane" if v == "hospital" else "Eczane" if v == "pharmacy" else "Sağlık"), RANK_POI
    v = t.get("shop")
    if v:
        return SHOP.get(v, "Mağaza"), RANK_POI
    v = t.get("tourism")
    if v:
        if v in TOURISM:
            return (TOURISM[v], RANK_POI) if TOURISM[v] else None
        return "Turistik yer", RANK_POI
    v = t.get("leisure")
    if v:
        if v in LEISURE:
            return (LEISURE[v], RANK_POI) if LEISURE[v] else None
        return None
    v = t.get("office")
    if v:
        return ("Kamu kurumu" if v == "government" else "Ofis"), RANK_POI
    if t.get("government"):
        return "Kamu kurumu", RANK_POI
    if t.get("aeroway") in ("aerodrome", "airport"):
        return "Havalimanı", 3
    if t.get("railway") in ("station", "halt") or t.get("public_transport") == "station":
        return ("Metro istasyonu" if t.get("station") == "subway" else "İstasyon"), RANK_POI
    if t.get("historic") and t.get("historic") not in ("memorial", "boundary_stone", "milestone"):
        return HISTORIC, RANK_POI
    v = t.get("natural")
    if v in NATURAL and NATURAL[v]:
        if v == "water" and t.get("water") not in (None, "lake", "reservoir", "lagoon"):
            return None
        return NATURAL[v], RANK_POI
    v = t.get("waterway")
    if v in WATERWAY:
        return WATERWAY[v], RANK_POI
    if t.get("landuse") == "industrial" and "sanayi" in norm(t.get("name", "")):
        return "Sanayi sitesi", RANK_POI
    return None


def names(t):
    """(display name, every distinct normalized name joined) or None."""
    disp = t.get("name:tr") or t.get("name")
    if not disp:
        return None
    alts = [disp]
    for k in ("name", "name:tr", "alt_name", "alt_name:tr", "official_name", "official_name:tr",
              "short_name", "short_name:tr", "loc_name", "old_name"):
        for v in (t.get(k) or "").split(";"):
            v = v.strip()
            if v:
                alts.append(v)
    seen, out = set(), []
    for a in alts:
        n = norm(a)
        if n and n not in seen:
            seen.add(n)
            out.append(n)
    if not out:
        return None
    return disp.strip(), " ".join(out)


# ── Read OSM ───────────────────────────────────────────────────────────────────────────────
class Collector:
    def __init__(self):
        self.pois = []      # (disp, ntext, label, rank, lat, lon)
        self.streets = {}   # norm name -> [disp, label, [(lat, lon), ...]]
        self.il = []        # (name, polygon)
        self.ilce = []      # (name, polygon)
        self.wkb = osmium.geom.WKBFactory()

    def tags(self, o):
        return {t.k: t.v for t in o.tags}

    def node(self, n):
        if "name" not in n.tags and "name:tr" not in n.tags:
            return
        t = self.tags(n)
        k = poi_kind(t)
        nm = k and names(t)
        if nm and n.location.valid():
            self.pois.append((nm[0], nm[1], k[0], k[1], n.location.lat, n.location.lon))

    def way(self, w):
        if "name" not in w.tags and "name:tr" not in w.tags:
            return
        t = self.tags(w)
        hw = t.get("highway")
        nodes = w.nodes
        if len(nodes) < 2:
            return
        if hw in STREETS and STREETS[hw] and t.get("area") != "yes":
            nm = names(t)
            mid = nodes[len(nodes) // 2]
            if nm and mid.location.valid():
                key = norm(nm[0])
                e = self.streets.get(key)
                if e is None:
                    e = self.streets[key] = [nm[0], STREETS[hw], []]
                elif STREETS[hw] in ("Otoyol", "Ana yol"):
                    e[1] = STREETS[hw]
                e[2].append((mid.location.lat, mid.location.lon))
            return
        if nodes[0].ref == nodes[-1].ref:
            return  # closed: comes back as an area
        k = poi_kind(t)
        nm = k and names(t)
        mid = nodes[len(nodes) // 2]
        if nm and mid.location.valid():
            self.pois.append((nm[0], nm[1], k[0], k[1], mid.location.lat, mid.location.lon))

    def area(self, a):
        t = self.tags(a)
        if t.get("boundary") == "administrative" and t.get("admin_level") in ("4", "6") and t.get("name"):
            try:
                g = shapely.make_valid(shp_wkb.loads(self.wkb.create_multipolygon(a), hex=True))
            except Exception as e:  # broken ring at the extract border
                log("skip boundary", t.get("name"), e)
                return
            (self.il if t["admin_level"] == "4" else self.ilce).append((t["name"], g))
            return
        if "name" not in t and "name:tr" not in t:
            return
        if t.get("highway"):
            return
        if t.get("boundary") == "administrative" and t.get("admin_level") in ("7", "8", "9", "10"):
            k = ("Mahalle", 5)   # mahalle / köy boundaries ("Tuzluçayır Mahallesi")
        elif t.get("boundary"):
            return
        else:
            k = poi_kind(t)
        nm = k and names(t)
        if not nm:
            return
        sx = sy = 0.0
        c = 0
        for ring in a.outer_rings():
            for nr in ring:
                if nr.location.valid():
                    sx += nr.location.lon
                    sy += nr.location.lat
                    c += 1
            break
        if c:
            self.pois.append((nm[0], nm[1], k[0], k[1], sy / c, sx / c))


def read(path):
    col = Collector()
    fp = osmium.FileProcessor(path).with_locations().with_areas()
    n = 0
    for o in fp:
        n += 1
        if n % 2_000_000 == 0:
            log(f"{n} objects, {len(col.pois)} POIs, {len(col.streets)} street names")
        if o.is_node():
            col.node(o)
        elif o.is_way():
            col.way(o)
        elif o.is_area():
            col.area(o)
    log(f"read {n} objects: {len(col.pois)} POIs/places, {len(col.streets)} street names, "
        f"{len(col.il)} il, {len(col.ilce)} ilçe")
    return col


# ── Areas (point in polygon) ──────────────────────────────────────────────────────────────
class Areas:
    def __init__(self, il, ilce):
        self.il_names = [n for n, _ in il]
        self.il_tree = shapely.STRtree([g for _, g in il]) if il else None
        self.ilce_names = [n for n, _ in ilce]
        self.ilce_geoms = [g for _, g in ilce]
        self.ilce_tree = shapely.STRtree(self.ilce_geoms) if ilce else None
        # Which il each ilçe lies in.
        self.ilce_il = [None] * len(ilce)
        if ilce and il:
            pts = shapely.points([shapely.get_coordinates(g.representative_point())[0] for g in self.ilce_geoms])
            for i, j in self._within(self.il_tree, pts):
                self.ilce_il[i] = j

    @staticmethod
    def _within(tree, pts):
        a, b = tree.query(pts, predicate="within")
        seen = {}
        for i, j in zip(a.tolist(), b.tolist()):
            seen.setdefault(i, j)
        return seen.items()

    def label(self, lats, lons):
        """Per point: (ilçe or None, il or None)."""
        pts = shapely.points(list(zip(lons, lats)))
        ilce = [None] * len(pts)
        il = [None] * len(pts)
        if self.ilce_tree is not None:
            for i, j in self._within(self.ilce_tree, pts):
                ilce[i] = j
        missing = [i for i in range(len(pts)) if ilce[i] is None]
        if self.il_tree is not None and missing:
            sub = pts[missing]
            for k, j in self._within(self.il_tree, sub):
                il[missing[k]] = j
            # Coast / border points just outside every polygon: nearest ilçe within ~3 km.
            still = [i for i in missing if il[i] is None]
            if still and self.ilce_tree is not None:
                idx, dist = self.ilce_tree.query_nearest(pts[still], max_distance=0.03, return_distance=True)
                for k, j in zip(idx[0].tolist(), idx[1].tolist()):
                    ilce[still[k]] = j
        out = []
        for i in range(len(pts)):
            a = None if ilce[i] is None else self.ilce_names[ilce[i]]
            p = il[i]
            if ilce[i] is not None and self.ilce_il[ilce[i]] is not None:
                p = self.ilce_il[ilce[i]]
            b = None if p is None else self.il_names[p]
            out.append((a, b))
        return out


def area_label(ilce, il):
    parts = [x for x in (ilce, il) if x]
    if len(parts) == 2 and norm(parts[0]) == norm(parts[1]):
        parts = parts[1:]
    return ", ".join(parts)


# ── Build ─────────────────────────────────────────────────────────────────────────────────
def cluster(points, radius_deg=0.0135):
    """Greedy clusters (~1.5 km); returns one representative point per cluster."""
    points = sorted(points)
    reps = []
    for la, lo in points:
        c = math.cos(math.radians(la))
        for r in reps[-60:]:
            if abs(r[0] - la) < radius_deg and abs(r[1] - lo) * c < radius_deg:
                break
        else:
            reps.append((la, lo))
    return reps


def build(col, out, date):
    areas = Areas(col.il, col.ilce)
    rows = []  # disp, ntext, label, rank, lat, lon
    # il / ilçe themselves.
    for (name, g), rank, label in [(x, 0, "İl") for x in col.il] + [(x, 2, "İlçe") for x in col.ilce]:
        p = g.representative_point()
        rows.append((name, norm(name), label, rank, p.y, p.x))
    # POIs/places, deduplicated (node + building outline of the same place, ~500 m grid).
    seen = set()
    for disp, nt, label, rank, la, lo in col.pois:
        key = (norm(disp), label, round(la * 200), round(lo * 200))
        if key in seen:
            continue
        seen.add(key)
        rows.append((disp, nt, label, rank, la, lo))
    n_poi = len(rows)
    log(f"{n_poi} il/ilçe/POI/place rows")
    # Streets: group by name, then by ilçe, then ~1.5 km clusters.
    flat = []
    for key, (disp, label, pts) in col.streets.items():
        for la, lo in pts:
            flat.append((key, la, lo))
    log(f"{len(flat)} named street segments; locating…")
    lab = areas.label([f[1] for f in flat], [f[2] for f in flat]) if flat else []
    groups, street_areas = {}, []
    for (key, la, lo), (a, b) in zip(flat, lab):
        groups.setdefault((key, a, b), []).append((la, lo))
    for (key, a, b), pts in groups.items():
        disp, label, _ = col.streets[key]
        for la, lo in cluster(pts):
            rows.append((disp, key, label, RANK_STREET, la, lo))
            street_areas.append((a, b))
    log(f"{len(rows) - n_poi} street points")
    labels_all = areas.label([r[4] for r in rows[:n_poi]], [r[5] for r in rows[:n_poi]]) + street_areas

    if os.path.exists(out):
        os.remove(out)
    db = sqlite3.connect(out)
    db.executescript("""
        PRAGMA page_size=4096; PRAGMA journal_mode=OFF; PRAGMA synchronous=OFF;
        CREATE TABLE meta(k TEXT PRIMARY KEY, v TEXT);
        CREATE TABLE kind(id INTEGER PRIMARY KEY, label TEXT NOT NULL);
        CREATE TABLE area(id INTEGER PRIMARY KEY, label TEXT NOT NULL);
        CREATE TABLE place(id INTEGER PRIMARY KEY, name TEXT NOT NULL, kind INTEGER NOT NULL,
                           rank INTEGER NOT NULL, lat INTEGER NOT NULL, lon INTEGER NOT NULL, area INTEGER NOT NULL);
        CREATE VIRTUAL TABLE s USING fts4(content="", name, area);
    """)
    kinds, area_ids = {}, {"": 0}
    db.execute("INSERT INTO area VALUES(0, '')")
    batch_p, batch_s = [], []
    for i, (r, al) in enumerate(zip(rows, labels_all), start=1):
        disp, nt, label, rank, la, lo = r
        kid = kinds.setdefault(label, len(kinds) + 1)
        alabel = area_label(*(al or (None, None)))
        aid = area_ids.get(alabel)
        if aid is None:
            aid = area_ids[alabel] = len(area_ids)
            db.execute("INSERT INTO area VALUES(?, ?)", (aid, alabel))
        batch_p.append((i, disp, kid, rank, round(la * 1e5), round(lo * 1e5), aid))
        batch_s.append((i, nt, norm(alabel)))
        if len(batch_p) >= 50_000:
            db.executemany("INSERT INTO place VALUES(?,?,?,?,?,?,?)", batch_p)
            db.executemany("INSERT INTO s(docid, name, area) VALUES(?,?,?)", batch_s)
            batch_p, batch_s = [], []
    db.executemany("INSERT INTO place VALUES(?,?,?,?,?,?,?)", batch_p)
    db.executemany("INSERT INTO s(docid, name, area) VALUES(?,?,?)", batch_s)
    db.executemany("INSERT INTO kind VALUES(?, ?)", [(v, k) for k, v in kinds.items()])
    db.executemany("INSERT INTO meta VALUES(?, ?)", [
        ("version", "1"), ("date", date), ("entries", str(len(rows))),
        ("streets", str(len(rows) - n_poi)), ("source", "OpenStreetMap (Geofabrik turkey-latest), ODbL"),
    ])
    db.commit()
    db.execute("INSERT INTO s(s) VALUES('optimize')")
    db.commit()
    db.execute("VACUUM")
    db.close()
    log(f"wrote {out}: {len(rows)} entries, {len(kinds)} kinds, {len(area_ids)} areas, "
        f"{os.path.getsize(out) / 1e6:.1f} MB")
    return len(rows)


def selftest(out):
    db = sqlite3.connect(out)
    for q in ("mamak devlet hastanesi", "kizilay", "1866 sokak", "ankara", "ataturk bulvari"):
        m = " ".join(f"name:{t}*" for t in norm(q).split())
        t = time.time()
        rs = db.execute(
            "SELECT p.name, k.label, a.label, p.lat, p.lon FROM s JOIN place p ON p.id = s.docid "
            "JOIN kind k ON k.id = p.kind JOIN area a ON a.id = p.area WHERE s MATCH ? "
            "ORDER BY ((p.lat - 3992000) * (p.lat - 3992000) + (p.lon - 3285000) * (p.lon - 3285000)) LIMIT 5",
            (m,)).fetchall()
        log(f"'{q}' ({(time.time() - t) * 1000:.0f} ms):", " | ".join(f"{r[0]} · {r[1]} · {r[2]}" for r in rs))
    db.close()


if __name__ == "__main__":
    src, out, date = sys.argv[1], sys.argv[2], sys.argv[3]
    c = read(src)
    build(c, out, date)
    selftest(out)
