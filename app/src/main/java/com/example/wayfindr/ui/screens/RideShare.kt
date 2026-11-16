package com.example.wayfindr.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Landscape
import androidx.compose.material.icons.outlined.Terrain
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas as GCanvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.wayfindr.data.Ride
import com.example.wayfindr.data.RideStats
import com.example.wayfindr.ui.WayfindrIcons
import com.example.wayfindr.ui.components.RideThumbs
import com.example.wayfindr.ui.theme.*
import com.example.wayfindr.util.RouteQr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** "Rotayı paylaş": preview of the ride image + Instagram / WhatsApp / Facebook / Diğer. */
@Composable
internal fun RideShareSheet(ride: Ride, name: String, stats: RideStats, onDismiss: () -> Unit) {
    val ctx = LocalContext.current
    val card = rememberShareCard(ride, name, stats)
    val share = { pkg: String? -> card?.let { RouteQr.shareImage(ctx, it.asAndroidBitmap(), name, pkg) }; Unit }
    Dialog(onDismissRequest = onDismiss) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clip(RoundedCornerShape(28.dp)).background(Surf1)
                .verticalScroll(rememberScrollState()).padding(horizontal = 22.dp, vertical = 26.dp),
        ) {
            Text("Rotayı paylaş", color = TextHi, fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = GeistFamily)
            Spacer(Modifier.height(20.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(0.86f)
                    .aspectRatio(CARD_W.toFloat() / (card?.height ?: CARD_H_FULL))
                    .clip(RoundedCornerShape(6.dp)).background(CARD_BG),
            ) {
                if (card == null) CircularProgressIndicator(color = Gold, strokeWidth = 2.dp, modifier = Modifier.size(28.dp))
                else Image(card, contentDescription = "Paylaşılacak rota görseli", modifier = Modifier.fillMaxSize())
            }
            Spacer(Modifier.height(26.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                ShareTarget("Instagram", Brush.linearGradient(listOf(Color(0xFFFEDA75), Color(0xFFFA7E1E), Color(0xFFD62976), Color(0xFF962FBF), Color(0xFF4F5BD5)), start = Offset(0f, Float.POSITIVE_INFINITY), end = Offset(Float.POSITIVE_INFINITY, 0f)),
                    enabled = card != null, onClick = { share("com.instagram.android") }) { InstagramGlyph() }
                ShareTarget("WhatsApp", Brush.linearGradient(listOf(Color(0xFF25D366), Color(0xFF25D366))),
                    enabled = card != null, onClick = { share("com.whatsapp") }) { WhatsAppGlyph() }
                ShareTarget("Facebook", Brush.linearGradient(listOf(Color(0xFF1877F2), Color(0xFF1877F2))),
                    enabled = card != null, onClick = { share("com.facebook.katana") }) {
                    Text("f", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold, fontFamily = GeistFamily)
                }
                ShareTarget("Diğer", Brush.linearGradient(listOf(Surf3, Surf3)),
                    enabled = card != null, onClick = { share(null) }) {
                    Icon(WayfindrIcons.Share, contentDescription = null, tint = TextHi, modifier = Modifier.size(26.dp))
                }
            }
        }
    }
}

@Composable
private fun ShareTarget(label: String, bg: Brush, enabled: Boolean, onClick: () -> Unit, glyph: @Composable () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clip(RoundedCornerShape(12.dp)).clickable(enabled = enabled, onClickLabel = "$label ile paylaş", onClick = onClick)
            .padding(4.dp),
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(58.dp).clip(CircleShape).background(bg)) { glyph() }
        Spacer(Modifier.height(8.dp))
        Text(label, color = TextHi, fontSize = 13.sp, fontFamily = GeistFamily, maxLines = 1)
    }
}

@Composable
private fun InstagramGlyph() = Canvas(Modifier.size(28.dp)) {
    val w = 2.4.dp.toPx()
    drawRoundRect(Color.White, topLeft = Offset(w / 2, w / 2), size = Size(size.width - w, size.height - w),
        cornerRadius = CornerRadius(8.dp.toPx()), style = Stroke(w))
    drawCircle(Color.White, radius = size.width * 0.22f, style = Stroke(w))
    drawCircle(Color.White, radius = w * 0.75f, center = Offset(size.width * 0.76f, size.height * 0.24f))
}

@Composable
private fun WhatsAppGlyph() = Box(contentAlignment = Alignment.Center) {
    Canvas(Modifier.size(30.dp)) {
        val w = 2.4.dp.toPx(); val r = size.width / 2 - w
        drawCircle(Color.White, radius = r, style = Stroke(w))
        // speech-bubble tail, bottom left
        val tail = Path().apply {
            moveTo(size.width * 0.17f, size.height * 0.68f); lineTo(size.width * 0.06f, size.height * 0.96f)
            lineTo(size.width * 0.36f, size.height * 0.86f); close()
        }
        drawPath(tail, Color.White)
    }
    Icon(Icons.Filled.Call, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
}

// ── The shared image ──────────────────────────────────────────────────────

private const val CARD_W = 1080
private const val MAP_TOP = 250
private const val MAP_H = 900
private const val ROW1_Y = 1310
private const val ROW2_Y = 1490
private const val CARD_H_FULL = 1660
private const val CARD_H_SHORT = 1480   // old rides without altitude: no second stat row
private val CARD_BG = Color(0xFF0F1214)

private class Stat(val icon: VectorPainter, val tint: Color, val value: String, val label: String)

@Composable
private fun rememberShareCard(ride: Ride, name: String, stats: RideStats): ImageBitmap? {
    val ctx = LocalContext.current
    val measurer = rememberTextMeasurer()
    val pin = rememberVectorPainter(WayfindrIcons.LocationPin)
    val clock = rememberVectorPainter(WayfindrIcons.Clock)
    val gauge = rememberVectorPainter(WayfindrIcons.Gauge)
    val bolt = rememberVectorPainter(WayfindrIcons.Zap)
    val terrain = rememberVectorPainter(Icons.Outlined.Terrain)
    val land = rememberVectorPainter(Icons.Outlined.Landscape)
    val card by produceState<ImageBitmap?>(null, ride.sid, name, stats) {
        val map = withContext(Dispatchers.IO) { RideThumbs.render(ctx, ride, CARD_W, MAP_H, 3f) }
        val row1 = listOf(
            Stat(pin, Gold, fmtKm(ride.distanceKm), "MESAFE"),
            Stat(clock, Color(0xFF5AA9E6), fmtDuration(ride.durationSec), "SÜRE"),
            Stat(gauge, Color(0xFF3DDC84), fmtKmh(ride.avgSpeedKmh), "ORT. HIZ"),
            Stat(bolt, Alert, fmtKmh(ride.maxSpeedKmh), "MAKS. HIZ"),
        )
        val row2 = listOfNotNull(
            stats.climbM?.let { Stat(terrain, Gold, fmtClimb(it), "YÜKSEKLİK KAZANCI") },
            fmtAltRange(stats)?.let { Stat(land, Gold, it, "RAKIM") },
        )
        value = drawCard(measurer, map?.asImageBitmap(), name, "${fmtDate(ride.startMs)} · ${fmtTime(ride.startMs)}", row1, row2)
    }
    return card
}

private fun drawCard(
    tm: TextMeasurer, map: ImageBitmap?, name: String, date: String, row1: List<Stat>, row2: List<Stat>,
): ImageBitmap {
    val h = if (row2.isEmpty()) CARD_H_SHORT else CARD_H_FULL
    val out = ImageBitmap(CARD_W, h)
    // Density 1: 1.sp == 1 px of the 1080-wide image.
    CanvasDrawScope().draw(Density(1f), LayoutDirection.Ltr, GCanvas(out), Size(CARD_W.toFloat(), h.toFloat())) {
        drawRect(CARD_BG)
        // Wordmark: "Himalayan" in the app's display face, gold; "4 5 0" letter-spaced in the odometer red.
        centered(tm, "Himalayan", TextStyle(color = Gold, fontSize = 104.sp, fontFamily = GeistFamily,
            fontWeight = FontWeight.Bold, letterSpacing = (-3).sp), 48f)
        centered(tm, "4 5 0", TextStyle(color = Alert, fontSize = 30.sp, fontFamily = GeistMonoFamily,
            fontWeight = FontWeight.SemiBold, letterSpacing = 20.sp), 172f)

        if (map != null) drawImage(map, topLeft = Offset(0f, MAP_TOP.toFloat()))
        // Fade the map edges into the card.
        drawRect(Brush.verticalGradient(listOf(CARD_BG, Color.Transparent), MAP_TOP.toFloat(), MAP_TOP + 110f),
            topLeft = Offset(0f, MAP_TOP.toFloat()), size = Size(CARD_W.toFloat(), 110f))
        val fadeTop = MAP_TOP + MAP_H - 240f
        drawRect(Brush.verticalGradient(listOf(Color.Transparent, CARD_BG), fadeTop, MAP_TOP + MAP_H.toFloat()),
            topLeft = Offset(0f, fadeTop), size = Size(CARD_W.toFloat(), 240f))

        centered(tm, name, TextStyle(color = TextHi, fontSize = 64.sp, fontFamily = GeistFamily,
            fontWeight = FontWeight.Bold, letterSpacing = (-1).sp), 1110f)
        centered(tm, date, TextStyle(color = TextMid, fontSize = 32.sp, fontFamily = GeistFamily), 1200f)

        statRow(tm, row1, ROW1_Y.toFloat(), colW = 260f)
        if (row2.isNotEmpty()) statRow(tm, row2, ROW2_Y.toFloat(), colW = 420f)
    }
    return out
}

private fun DrawScope.statRow(tm: TextMeasurer, stats: List<Stat>, y: Float, colW: Float) {
    val left = (CARD_W - colW * stats.size) / 2
    stats.forEachIndexed { i, s ->
        val cx = left + colW * (i + 0.5f)
        translate(cx - 22f, y) { with(s.icon) { draw(Size(44f, 44f), colorFilter = ColorFilter.tint(s.tint)) } }
        centered(tm, s.value, TextStyle(color = TextHi, fontSize = 50.sp, fontFamily = GeistFamily,
            fontWeight = FontWeight.SemiBold, letterSpacing = (-1).sp), y + 58f, cx, colW - 12f)
        centered(tm, s.label, TextStyle(color = TextMid, fontSize = 22.sp, fontFamily = GeistMonoFamily,
            letterSpacing = 3.sp), y + 126f, cx, colW - 12f)
    }
}

/** Draw single-line [text] centred on [cx], ellipsised to [maxW]. */
private fun DrawScope.centered(tm: TextMeasurer, text: String, style: TextStyle, top: Float,
                               cx: Float = CARD_W / 2f, maxW: Float = CARD_W - 120f) {
    val r: TextLayoutResult = tm.measure(text, style, overflow = TextOverflow.Ellipsis, maxLines = 1,
        constraints = Constraints(maxWidth = maxW.toInt()), density = Density(1f))
    drawText(r, topLeft = Offset(cx - r.size.width / 2f, top))
}
