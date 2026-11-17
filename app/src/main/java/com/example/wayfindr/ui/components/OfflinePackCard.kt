package com.example.wayfindr.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wayfindr.dash.offline.OfflinePack
import com.example.wayfindr.ui.WayfindrIcons
import com.example.wayfindr.ui.theme.*
import kotlinx.coroutines.delay

/**
 * "Türkiye çevrimdışı paketi" card (Ayarlar → Çevrimdışı harita): offline Turkey map, place search
 * index and BRouter routing data in one download. Not installed → size and "indir"; downloading → progress; installed → map date and
 * "Güncelle" (newer map / segments replace the old files only once fully downloaded).
 */
@Composable
fun OfflinePackCard(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val st by OfflinePack.status.collectAsState()

    LaunchedEffect(Unit) { OfflinePack.refresh(context); OfflinePack.checkForUpdate(context) }
    // Progress while visible; DownloadManager keeps going on its own when the screen is off.
    LaunchedEffect(st.downloading) {
        while (st.downloading) { delay(1_000); OfflinePack.refresh(context) }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Surf1)
            .border(1.dp, if (st.updateAvailable) GoldTint2 else Line, RoundedCornerShape(14.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(WayfindrIcons.Download, null, tint = if (st.installed) Gold else TextMid, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(11.dp))
            Column(Modifier.weight(1f)) {
                Text("Türkiye çevrimdışı paketi", color = TextHi, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
                val sub = when {
                    st.downloading -> "İndiriliyor… %" + (st.progress * 100).toInt() +
                        (if (st.wantBytes > 0) " · ${OfflinePack.gb(st.doneBytes)} / ${OfflinePack.gb(st.wantBytes)}" else "")
                    st.busy -> "Sunucu kontrol ediliyor…"
                    st.installed && !st.searchInstalled ->
                        "Kurulu · harita ${OfflinePack.prettyDate(st.mapDate)} · arama dizini yok, Güncelle ile ekleyin"
                    st.installed -> "Kurulu · harita ${OfflinePack.prettyDate(st.mapDate)} · internetsiz harita, arama ve rota"
                    st.mapInstalled || st.routingInstalled -> "Yarım kalmış · tamamlamak için indirin"
                    else -> "Kurulu değil · ~${OfflinePack.gb(st.totalBytes)} · harita, arama ve rota verisi"
                }
                Text(sub, color = TextLo, fontSize = 11.5.sp, modifier = Modifier.padding(top = 1.dp))
            }
            if (st.installed && st.updateAvailable && !st.downloading) {
                Text("Güncelleme var", color = Gold, fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(GoldTint).padding(horizontal = 8.dp, vertical = 3.dp))
            }
        }

        if (st.downloading) {
            Spacer(Modifier.height(10.dp))
            Box(Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)).background(Surf3)) {
                Box(Modifier.fillMaxWidth(st.progress).fillMaxHeight().background(Gold))
            }
            Spacer(Modifier.height(6.dp))
            Text("Ekran kapansa da indirme sürer. Eski paket, yenisi tamamen inene kadar kullanılır.",
                color = TextLo, fontSize = 11.sp)
            Spacer(Modifier.height(8.dp))
            WayfindrBtn("Durdur", onClick = { OfflinePack.cancel(context) },
                variant = BtnVariant.Ghost, size = BtnSize.Sm, modifier = Modifier.fillMaxWidth())
        } else {
            Spacer(Modifier.height(10.dp))
            if (st.installed) {
                WayfindrBtn(
                    if (st.updateAvailable) "Güncelle (${OfflinePack.prettyDate(st.latestMapDate)})" else "Güncelle",
                    onClick = { OfflinePack.download(context) },
                    icon = WayfindrIcons.Sync,
                    variant = if (st.updateAvailable) BtnVariant.Primary else BtnVariant.Secondary,
                    size = BtnSize.Sm, enabled = !st.busy, modifier = Modifier.fillMaxWidth(),
                )
            } else {
                WayfindrBtn(
                    "Türkiye çevrimdışı paketini indir",
                    onClick = { OfflinePack.download(context) },
                    icon = WayfindrIcons.Download,
                    size = BtnSize.Sm, enabled = !st.busy, modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(Modifier.height(6.dp))
            Text("Wi-Fi ile indirmeniz önerilir", color = TextLo, fontSize = 11.sp)
        }
        st.message?.let {
            Spacer(Modifier.height(4.dp))
            Text(it, color = if (it.startsWith("İndirme başarısız") || it.startsWith("Yer yetersiz")) Alert else TextMid, fontSize = 11.5.sp)
        }
    }
}
