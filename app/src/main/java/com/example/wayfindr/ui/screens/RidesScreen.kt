package com.example.wayfindr.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wayfindr.data.Ride
import com.example.wayfindr.dash.nav.NavPlus
import com.example.wayfindr.dash.nav.PolylineCodec
import com.example.wayfindr.ui.WayfindrIcons
import com.example.wayfindr.ui.components.*
import com.example.wayfindr.ui.theme.*
import com.example.wayfindr.viewmodel.RidesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RidesScreen(ridesViewModel: RidesViewModel = viewModel()) {
    val rides by ridesViewModel.rides.collectAsState()

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
        } else {
            RideTotals(rides)
            Spacer(Modifier.height(14.dp))
            rides.forEach { ride ->
                RideCard(ride, onDelete = { ridesViewModel.deleteRide(ride) })
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun RideTotals(rides: List<Ride>) {
    val totalKm = rides.sumOf { it.distanceKm }
    val totalSec = rides.sumOf { it.durationSec }
    WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 18.dp) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Stat("${rides.size}", "sürüş")
            Stat("%.0f".format(totalKm), "toplam km")
            Stat(fmtDuration(totalSec), "süre")
        }
        // v34: summary / records / share card, opened through NavPlus (mode 2).
        SettingRow(
            WayfindrIcons.Chart,
            "Haftalık / aylık özet, rekorlar, paylaş",
            "Renkli sürüş izleri ve WhatsApp / Instagram için paylaşım kartı",
            control = {},
            onClick = { NavPlus.offMode = 2; NavPlus.openOffline() },
        )
    }
}

@Composable
private fun Stat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = Gold, fontSize = 22.sp, fontWeight = FontWeight.Bold, fontFamily = GeistFamily)
        Text(label, color = TextLo, fontSize = 12.sp, fontFamily = GeistFamily)
    }
}

@Composable
private fun RideCard(ride: Ride, onDelete: () -> Unit) {
    val track = remember(ride.trackPolyline) {
        if (ride.trackPolyline.isBlank()) emptyList()
        else PolylineCodec.decode(ride.trackPolyline)
    }
    WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 16.dp) {
        Column(Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                // Track sketch
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(64.dp).clip(RoundedCornerShape(12.dp)).background(Surf2),
                ) {
                    if (track.size >= 2) TrackSketch(track, Modifier.fillMaxSize().padding(8.dp))
                    else Icon(WayfindrIcons.Route, contentDescription = null, tint = TextLo, modifier = Modifier.size(22.dp))
                }
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(fmtDate(ride.startMs), color = TextHi, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
                    Text(
                        "${fmtTime(ride.startMs)} – ${fmtTime(ride.endMs)}",
                        color = TextLo, fontSize = 12.sp, fontFamily = GeistFamily,
                    )
                }
                Text("%.1f km".format(ride.distanceKm), color = Gold, fontSize = 18.sp, fontWeight = FontWeight.Bold, fontFamily = GeistFamily)
            }
            Spacer(Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                MiniStat(fmtDuration(ride.durationSec), "süre")
                MiniStat("%.0f km/sa".format(ride.avgSpeedKmh), "ortalama")
                MiniStat("%.0f km/sa".format(ride.maxSpeedKmh), "en yüksek")
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(34.dp).clip(CircleShape).background(Surf2)
                        .clickable { onDelete() },
                ) {
                    Icon(WayfindrIcons.X, contentDescription = "Sürüşü sil", tint = TextLo, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
private fun MiniStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(value, color = TextHi, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
        Text(label, color = TextLo, fontSize = 11.sp, fontFamily = GeistFamily)
    }
}

@Composable
private fun TrackSketch(points: List<com.example.wayfindr.dash.nav.GeoPoint>, modifier: Modifier) {
    Canvas(modifier) {
        // v34: the track is drawn by NavPlus (same look as the dash's route trace).
        val packedSize = (java.lang.Float.floatToRawIntBits(size.width).toLong() shl 32) or
            (java.lang.Float.floatToRawIntBits(size.height).toLong() and 0xFFFFFFFFL)
        try {
            NavPlus.trackDrawSz(drawContext.canvas.nativeCanvas, points, packedSize)
        } catch (_: Throwable) {
        }
    }
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

private fun fmtDate(ms: Long) = SimpleDateFormat("d MMM yyyy, EEEE", Locale.forLanguageTag("tr-TR")).format(Date(ms))
private fun fmtTime(ms: Long) = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(ms))
private fun fmtDuration(sec: Long): String {
    val h = sec / 3600; val m = (sec % 3600) / 60
    return if (h > 0) "$h sa $m dk" else "$m dk"
}
