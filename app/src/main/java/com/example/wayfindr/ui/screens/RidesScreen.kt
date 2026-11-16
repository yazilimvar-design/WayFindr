package com.example.wayfindr.ui.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wayfindr.dash.nav.NavPlus
import com.example.wayfindr.dash.nav.PolylineCodec
import com.example.wayfindr.data.Ride
import com.example.wayfindr.ui.WayfindrIcons
import com.example.wayfindr.ui.components.*
import com.example.wayfindr.ui.theme.*
import com.example.wayfindr.viewmodel.RidesViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private val TR = Locale.forLanguageTag("tr-TR")
private const val DAY_MS = 86_400_000L

@Composable
fun RidesScreen(ridesViewModel: RidesViewModel = viewModel()) {
    val rides by ridesViewModel.rides.collectAsState()
    val names by ridesViewModel.names.collectAsState()
    var showAll by rememberSaveable { mutableStateOf(false) }
    var detailSid by rememberSaveable { mutableStateOf<String?>(null) }
    val nameOf: (Ride) -> String = { names[it.sid] ?: (SimpleDateFormat("d MMMM", TR).format(Date(it.startMs)) + " sürüşü") }

    BackHandler(enabled = showAll || detailSid != null) {
        if (detailSid != null) detailSid = null else showAll = false
    }

    val detail = detailSid?.let { sid -> rides.firstOrNull { it.sid == sid } }
    when {
        detail != null -> RideDetail(
            detail, nameOf(detail),
            onBack = { detailSid = null },
            onDelete = { ridesViewModel.deleteRide(detail); detailSid = null },
        )
        showAll -> AllRides(rides, nameOf, onBack = { showAll = false }, onOpen = { detailSid = it.sid })
        else -> RidesHome(
            rides, nameOf,
            onOpen = { detailSid = it.sid },
            onShowAll = { showAll = true },
            onClear = { ridesViewModel.deleteAll() },
        )
    }
}

// ── Home: recent-ride carousel + summary ─────────────────────────────────

@Composable
private fun RidesHome(
    rides: List<Ride>,
    nameOf: (Ride) -> String,
    onOpen: (Ride) -> Unit,
    onShowAll: () -> Unit,
    onClear: () -> Unit,
) {
    var confirmClear by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(18.dp)
            .padding(bottom = 24.dp),
    ) {
        ScreenHeader(eyebrow = "Telemetri", title = "Sürüş geçmişi",
            hint = "Bağlantı kurulan her sürüş burada kaydedilir: mesafe, süre ve izlediğiniz rota.")

        if (rides.isEmpty()) {
            EmptyRides()
            return@Column
        }

        SectionTitle("SON ROTALAR", action = "Temizle", onAction = { confirmClear = true })
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(rides.take(10), key = { it.sid.ifEmpty { it.startMs.toString() } }) { ride ->
                RecentCard(ride, nameOf(ride), onOpen = { onOpen(ride) })
            }
        }

        SectionTitle("GEZİ ÖZETİ")
        RideSummary(rides, onShowAll)
    }

    if (confirmClear) ConfirmDialog(
        title = "Tüm sürüşler silinsin mi?",
        text = "${rides.size} sürüş kalıcı olarak silinir. Bu işlem geri alınamaz.",
        onConfirm = { confirmClear = false; onClear() },
        onDismiss = { confirmClear = false },
    )
}

@Composable
private fun SectionTitle(text: String, action: String? = null, onAction: (() -> Unit)? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(top = 22.dp, bottom = 12.dp).heightIn(min = 44.dp),
    ) {
        Box(Modifier.weight(1f).height(1.dp).background(GoldTint2))
        Text(
            text, color = Gold, fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
            fontFamily = GeistMonoFamily, letterSpacing = 3.sp,
            modifier = Modifier.padding(horizontal = 14.dp),
        )
        Box(Modifier.weight(1f).height(1.dp).background(GoldTint2))
        if (action != null && onAction != null) {
            Text(
                action, color = TextMid, fontSize = 13.sp, fontFamily = GeistFamily,
                modifier = Modifier.clip(RoundedCornerShape(8.dp)).clickable(onClick = onAction)
                    .padding(start = 14.dp, end = 4.dp, top = 12.dp, bottom = 12.dp),
            )
        }
    }
}

@Composable
private fun RecentCard(ride: Ride, name: String, onOpen: () -> Unit) {
    Column(
        Modifier.width(284.dp).clip(CardShape).background(Surf1).border(1.dp, Line, CardShape)
            .clickable(onClick = onOpen),
    ) {
        RideThumb(ride, Modifier.fillMaxWidth().height(150.dp))
        Column(Modifier.padding(horizontal = 18.dp, vertical = 14.dp)) {
            Text(name, color = TextHi, fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = GeistFamily,
                maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(2.dp))
            Text(fmtDayTime(ride.startMs), color = TextLo, fontSize = 13.sp, fontFamily = GeistFamily)
        }
        WayfindrDivider()
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 12.dp),
        ) {
            IconValue(WayfindrIcons.LocationPin, fmtKm(ride.distanceKm))
            IconValue(WayfindrIcons.Clock, fmtDuration(ride.durationSec))
        }
        WayfindrDivider()
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(14.dp).fillMaxWidth().height(46.dp).clip(BtnShape)
                .border(1.dp, Gold.copy(alpha = 0.4f), BtnShape).clickable(onClick = onOpen),
        ) {
            Text("AYRINTILARI GÖRÜNTÜLE", color = Gold, fontSize = 12.5.sp, fontWeight = FontWeight.SemiBold,
                fontFamily = GeistMonoFamily, letterSpacing = 2.sp, maxLines = 1)
        }
    }
}

@Composable
private fun IconValue(icon: ImageVector, value: String, size: Int = 15) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = TextMid, modifier = Modifier.size((size + 3).dp))
        Spacer(Modifier.width(6.dp))
        Text(value, color = TextHi, fontSize = size.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
    }
}

private val PERIODS = listOf(7 to "Son 7 gün", 30 to "Son 30 gün", 365 to "Son 1 yıl", 0 to "Tümü")

@Composable
private fun RideSummary(rides: List<Ride>, onShowAll: () -> Unit) {
    var periodIdx by rememberSaveable { mutableIntStateOf(1) }
    val (days, label) = PERIODS[periodIdx]
    val since = if (days == 0) 0L else System.currentTimeMillis() - days * DAY_MS
    val inPeriod = rides.filter { it.startMs >= since }
    val km = inPeriod.sumOf { it.distanceKm }
    val sec = inPeriod.sumOf { it.durationSec }

    WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 20.dp) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("TOPLAM KM", color = TextMid, fontSize = 12.sp, fontFamily = GeistMonoFamily, letterSpacing = 2.5.sp,
                modifier = Modifier.weight(1f))
            // v34: summary / records / share card, opened through NavPlus (mode 2).
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(44.dp).clip(CircleShape)
                    .clickable { NavPlus.offMode = 2; NavPlus.openOffline() },
            ) {
                Icon(WayfindrIcons.Chart, contentDescription = "Haftalık / aylık özet, rekorlar, paylaş",
                    tint = TextMid, modifier = Modifier.size(22.dp))
            }
            Spacer(Modifier.width(4.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.heightIn(min = 36.dp).clip(ChipShape).background(GoldTint)
                    .border(1.dp, GoldTint2, ChipShape)
                    .clickable { periodIdx = (periodIdx + 1) % PERIODS.size }
                    .padding(horizontal = 14.dp),
            ) {
                Text(label, color = Gold, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
            }
        }
        Spacer(Modifier.height(18.dp))
        Odometer(km)
        Spacer(Modifier.height(22.dp))
        Row(Modifier.fillMaxWidth()) {
            SummaryStat(WayfindrIcons.Route, "Geziler", "${inPeriod.size}", Modifier.weight(1f))
            SummaryStat(WayfindrIcons.Clock, "Süre", fmtDuration(sec), Modifier.weight(1f))
        }
        Spacer(Modifier.height(18.dp))
        Row(Modifier.fillMaxWidth()) {
            SummaryStat(WayfindrIcons.LocationPin, "Mesafe", fmtKm(km), Modifier.weight(1f))
            SummaryStat(WayfindrIcons.Gauge, "Ort. hız", fmtKmh(if (sec > 0) km / (sec / 3600.0) else 0.0), Modifier.weight(1f))
        }
        Spacer(Modifier.height(14.dp))
        Text(
            "TÜM ROTALARI GÖRÜNTÜLE  ›", color = Gold, fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
            fontFamily = GeistMonoFamily, letterSpacing = 2.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally).clip(RoundedCornerShape(8.dp))
                .clickable(onClick = onShowAll).padding(horizontal = 12.dp, vertical = 14.dp),
        )
    }
}

/** Odometer-style digit boxes; the last digit is the red "tenths wheel" accent. */
@Composable
private fun Odometer(km: Double) {
    val digits = Math.round(km).toString().padStart(5, '0')
    Row(horizontalArrangement = Arrangement.spacedBy(7.dp), modifier = Modifier.fillMaxWidth().wrapContentWidth()) {
        digits.forEachIndexed { i, d ->
            val last = i == digits.lastIndex
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(width = 42.dp, height = 54.dp).clip(RoundedCornerShape(9.dp))
                    .background(Bg0).border(1.dp, if (last) Alert.copy(alpha = 0.45f) else Line2, RoundedCornerShape(9.dp)),
            ) {
                Text("$d", color = if (last) Alert else TextHi, fontSize = 28.sp, fontFamily = GeistMonoFamily)
            }
        }
    }
}

@Composable
private fun SummaryStat(icon: ImageVector, label: String, value: String, modifier: Modifier) {
    Column(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = TextMid, modifier = Modifier.size(17.dp))
            Spacer(Modifier.width(7.dp))
            Text(label, color = TextMid, fontSize = 13.sp, fontFamily = GeistFamily)
        }
        Spacer(Modifier.height(4.dp))
        Text(value, color = TextHi, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
    }
}

// ── All rides, grouped by day / month ────────────────────────────────────

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AllRides(rides: List<Ride>, nameOf: (Ride) -> String, onBack: () -> Unit, onOpen: (Ride) -> Unit) {
    val groups = remember(rides) { rides.groupBy { sectionOf(it.startMs) } }
    LazyColumn(
        contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 42.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            ScreenHeader(title = "Tüm rotalar", onBack = onBack,
                hint = "${rides.size} rota · ${fmtKm(rides.sumOf { it.distanceKm })}")
        }
        groups.forEach { (section, list) ->
            item(key = "h_$section") {
                Text(section, color = Gold, fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
                    fontFamily = GeistMonoFamily, letterSpacing = 2.sp,
                    modifier = Modifier.padding(top = 12.dp, bottom = 2.dp))
            }
            items(list, key = { "r_" + it.sid.ifEmpty { it.startMs.toString() } }) { ride ->
                val recent = section == "BUGÜN" || section == "DÜN"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().height(124.dp).clip(CardShape).background(Surf1)
                        .border(1.dp, Line, CardShape).clickable { onOpen(ride) },
                ) {
                    RideThumb(ride, Modifier.width(124.dp).fillMaxHeight())
                    Column(Modifier.weight(1f).padding(horizontal = 14.dp, vertical = 12.dp)) {
                        Text(nameOf(ride), color = TextHi, fontSize = 17.sp, fontWeight = FontWeight.Bold,
                            fontFamily = GeistFamily, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(if (recent) fmtTime(ride.startMs) else fmtDayTime(ride.startMs),
                            color = TextLo, fontSize = 13.sp, fontFamily = GeistFamily)
                        Spacer(Modifier.height(8.dp))
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            IconValue(WayfindrIcons.LocationPin, fmtKm(ride.distanceKm), 14)
                            IconValue(WayfindrIcons.Clock, fmtDuration(ride.durationSec), 14)
                            IconValue(WayfindrIcons.Gauge, fmtKmh(ride.avgSpeedKmh), 14)
                        }
                    }
                    Icon(WayfindrIcons.ChevronRight, contentDescription = null, tint = TextLo,
                        modifier = Modifier.padding(end = 10.dp).size(22.dp))
                }
            }
        }
    }
}

/** "BUGÜN" / "DÜN" / "KASIM 2026". */
private fun sectionOf(ms: Long): String {
    val day = { t: Long -> Calendar.getInstance().apply { timeInMillis = t; set(Calendar.HOUR_OF_DAY, 12) }.let { it.get(Calendar.YEAR) * 1000 + it.get(Calendar.DAY_OF_YEAR) } }
    val today = day(System.currentTimeMillis())
    return when (day(ms)) {
        today -> "BUGÜN"
        day(System.currentTimeMillis() - DAY_MS) -> "DÜN"
        else -> SimpleDateFormat("LLLL yyyy", TR).format(Date(ms)).uppercase(TR)
    }
}

// ── Detail ────────────────────────────────────────────────────────────────

@Composable
private fun RideDetail(ride: Ride, name: String, onBack: () -> Unit, onDelete: () -> Unit) {
    val activity = LocalContext.current as? Activity
    var confirmDelete by remember { mutableStateOf(false) }
    val hasSpeed = remember(ride.trackPolyline) {
        try {
            val pts = PolylineCodec.decode(ride.trackPolyline)
            pts.size >= 2 && NavPlus.rsLoad(pts[0])?.size == pts.size
        } catch (_: Throwable) { false }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(18.dp)
            .padding(bottom = 24.dp),
    ) {
        ScreenHeader(title = name, onBack = onBack,
            hint = "${fmtDate(ride.startMs)} · ${fmtTime(ride.startMs)}–${fmtTime(ride.endMs)}")
        RideThumb(ride, Modifier.fillMaxWidth().height(260.dp).clip(CardShape).border(1.dp, Line, CardShape))
        if (hasSpeed) {
            FlowRowLegend()
        }
        Spacer(Modifier.height(14.dp))
        WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 20.dp) {
            Row(Modifier.fillMaxWidth()) {
                SummaryStat(WayfindrIcons.LocationPin, "Mesafe", fmtKm(ride.distanceKm), Modifier.weight(1f))
                SummaryStat(WayfindrIcons.Clock, "Süre", fmtDuration(ride.durationSec), Modifier.weight(1f))
            }
            Spacer(Modifier.height(18.dp))
            Row(Modifier.fillMaxWidth()) {
                SummaryStat(WayfindrIcons.Gauge, "Ort. hız", fmtKmh(ride.avgSpeedKmh), Modifier.weight(1f))
                SummaryStat(WayfindrIcons.Trend, "En yüksek", fmtKmh(ride.maxSpeedKmh), Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            WayfindrBtn(
                "Paylaş", icon = WayfindrIcons.Share, variant = BtnVariant.Secondary, modifier = Modifier.weight(1f),
                onClick = {
                    // Same share card as v34's summary screen: row = start, metres, seconds, max m/s, track.
                    activity?.let { NavPlus.rideShare(it, arrayOf<Any>(ride.startMs, ride.distanceMeters, ride.durationSec, ride.maxSpeedMps, ride.trackPolyline)) }
                },
            )
            WayfindrBtn("Sil", icon = WayfindrIcons.X, variant = BtnVariant.Danger, modifier = Modifier.weight(1f),
                onClick = { confirmDelete = true })
        }
    }
    if (confirmDelete) ConfirmDialog(
        title = "Sürüş silinsin mi?",
        text = "Bu sürüş kalıcı olarak silinir.",
        onConfirm = { confirmDelete = false; onDelete() },
        onDismiss = { confirmDelete = false },
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowLegend() {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(top = 10.dp),
    ) {
        listOf(0 to "0–30", 30 to "30–60", 60 to "60–90", 90 to "90–120", 120 to "120+ km/sa").forEach { (kmh, label) ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(8.dp).clip(CircleShape).background(Color(NavPlus.spColor(kmh))))
                Spacer(Modifier.width(5.dp))
                Text(label, color = TextMid, fontSize = 12.sp, fontFamily = GeistFamily)
            }
        }
    }
}

@Composable
private fun ConfirmDialog(title: String, text: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { TextButton(onClick = onConfirm) { Text("Sil", color = Alert) } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("İptal", color = TextMid) } },
        title = { Text(title, color = TextHi) },
        text = { Text(text, color = TextMid) },
        containerColor = Surf1,
    )
}

@Composable
private fun EmptyRides() {
    WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 28.dp) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(56.dp).clip(CircleShape).background(Surf2),
            ) {
                Icon(WayfindrIcons.Route, contentDescription = null, tint = TextLo, modifier = Modifier.size(26.dp))
            }
            Spacer(Modifier.height(14.dp))
            Text("Henüz sürüş kaydedilmedi", color = TextHi, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
            Spacer(Modifier.height(6.dp))
            Text(
                "Sürüşe başlamak için konsol ekranınıza bağlanın — bağlantıyı kestiğinizde sürüş otomatik olarak kaydedilir.",
                color = TextLo, fontSize = 13.sp,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
        }
    }
}

private fun fmtDate(ms: Long) = SimpleDateFormat("d MMMM yyyy, EEEE", TR).format(Date(ms))
private fun fmtTime(ms: Long) = SimpleDateFormat("HH:mm", TR).format(Date(ms))
private fun fmtDayTime(ms: Long) = SimpleDateFormat("dd/MM · HH:mm", TR).format(Date(ms))
private fun fmtKm(km: Double) = String.format(TR, "%.1f km", km)
private fun fmtKmh(kmh: Double) = String.format(TR, "%.0f km/sa", kmh)
private fun fmtDuration(sec: Long): String {
    val h = sec / 3600; val m = (sec % 3600) / 60
    return if (h > 0) "$h sa $m dk" else "$m dk"
}
