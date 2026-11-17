package com.example.wayfindr.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wayfindr.dash.DashConfig
import com.example.wayfindr.dash.nav.NavPlus
import com.example.wayfindr.ui.WayfindrIcons
import com.example.wayfindr.ui.components.*
import com.example.wayfindr.ui.theme.*
import com.example.wayfindr.viewmodel.ConnectionState

@Composable
fun SettingsScreen(
    conn: ConnectionState,
    onConnChange: (ConnectionState) -> Unit,
    onBack: () -> Unit,
) {
    val ctx = LocalContext.current
    val autoConfig = remember { DashConfig.get(ctx) }
    val autoConnect by autoConfig.autoConnectOnStart.collectAsState()
    var screenOff   by remember { mutableStateOf(true) }
    var keepAwake   by remember { mutableStateOf(true) }
    var units       by remember { mutableStateOf("Kilometre") }

    // Real voice setting, shared with RouteScreen via the VoiceManager singleton.
    val voiceManager = remember { com.example.wayfindr.dash.nav.VoiceManager.get(ctx) }
    val voiceMode by voiceManager.mode.collectAsState()
    val voice = when (voiceMode) {
        com.example.wayfindr.dash.nav.VoiceMode.OFF   -> "Kapalı"
        com.example.wayfindr.dash.nav.VoiceMode.CHIME -> "Uyarı sesi"
        com.example.wayfindr.dash.nav.VoiceMode.FULL  -> "Tam TTS"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(18.dp)
            .padding(bottom = 24.dp),
    ) {
        ScreenHeader(title = "Ayarlar", onBack = onBack,
            hint = "Bağlantı, çevrimdışı harita, ekran kapalıyken aktarım, sesli yönlendirme, birimler ve medya/arama izinleri.")

        SectionLabel("Bağlantı")
        WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 6.dp) {
            SettingRow(WayfindrIcons.Bt, "Konsol ekranı",
                sub = when (conn) { ConnectionState.Connected -> "Bağlı"; ConnectionState.Searching -> "Bağlanıyor…"; ConnectionState.Offline -> "Bağlı değil" },
                control = { WayfindrChip(if (conn == ConnectionState.Connected) "Bağlı" else "Kapalı", if (conn == ConnectionState.Connected) ChipTone.Gold else ChipTone.Off, dot = true) })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Sync, "Başlangıçta otomatik bağlan", "Motosiklet yakındayken bağlan",
                control = { WayfindrToggle(autoConnect) { autoConfig.setAutoConnectOnStart(it) } })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            val streamCfg = remember { DashConfig.get(ctx) }
            val hqVideo by streamCfg.highQualityVideo.collectAsState()
            SettingRow(WayfindrIcons.Zap, "Daha yüksek video kalitesi",
                if (hqVideo) "400 kbps · daha net görüntü, konsola daha fazla yük" else "Dengeli · 200 kbps",
                control = { WayfindrToggle(hqVideo) { streamCfg.setHighQualityVideo(it) } }, last = true)
        }

        SectionLabel("Çevrimdışı harita")
        OfflinePackCard()

        SectionLabel("Sürüş sırasında")
        val dashConfig = remember { DashConfig.get(ctx) }
        val mapDark by dashConfig.mapDarkMode.collectAsState()
        val mapDarkAuto by dashConfig.mapDarkAuto.collectAsState()
        val invertZoom by dashConfig.invertZoom.collectAsState()
        val sharperMap by dashConfig.sharperMap.collectAsState()
        val pickerDoubleTap by dashConfig.pickerDoubleTap.collectAsState()
        val showJoystickTrail by dashConfig.showJoystickTrail.collectAsState()
        val showPhoneBattery by dashConfig.showPhoneBattery.collectAsState()
        WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 6.dp) {
            SettingRow(WayfindrIcons.Power, "Telefon ekranını kapat", "Harita konsola aktarılmaya devam eder",
                control = { WayfindrToggle(screenOff) { screenOff = it } })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Dash, "Konsolu uyanık tut", "Konsolun uykuya geçmesini önle",
                control = { WayfindrToggle(keepAwake) { keepAwake = it } })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Moon, "Haritada otomatik karanlık mod",
                if (mapDarkAuto) "Saat 19.00–05.00 arasında karanlık (telefon saatine göre)" else "Kapalı: aşağıdaki anahtarla elle ayarlayın",
                control = { WayfindrToggle(mapDarkAuto) { dashConfig.setMapDarkAuto(it) } })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            // Shows the current state while auto; flipping it switches to manual.
            val darkNow = com.example.wayfindr.dash.DarkSchedule.effective(mapDarkAuto, mapDark)
            SettingRow(WayfindrIcons.Moon, "Konsol haritası: karanlık mod",
                if (mapDarkAuto) "Saati izliyor · elle ayarlamak için dokunun" else "Elle · aynı önbelleğe alınmış harita kutucuklarında gece görünümü",
                control = { WayfindrToggle(darkNow) { dashConfig.setMapDarkMode(it); dashConfig.setMapDarkAuto(false) } })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Sync, "Joystick yakınlaştırmasını tersine çevir",
                if (invertZoom) "Sağa: yakınlaştır · sola: uzaklaştır" else "Sola: yakınlaştır · sağa: uzaklaştır",
                control = { WayfindrToggle(invertZoom) { dashConfig.setInvertZoom(it) } })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Pin, "Sola/sağa iki kez basınca hedef listesi açılsın",
                if (pickerDoubleTap) "Açık: sola/sağa iki kez dokunun veya aşağıya iki kez basın; kayıtlı liste açılır"
                else "Kapalı: aşağıya 3 sn içinde iki kez basınca kayıtlı liste açılır",
                control = { WayfindrToggle(pickerDoubleTap) { dashConfig.setPickerDoubleTap(it) } })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Target, "Joystick basışlarını göster",
                "Konsolun alt ortasında son 4 basışı D / R / L olarak, her biri 2 sn göster",
                control = { WayfindrToggle(showJoystickTrail) { dashConfig.setShowJoystickTrail(it) } })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Target, "Telefon pilini konsolda göster",
                "Tahmini varış süresinin üstünde pil yüzdesi; şarj olurken şimşek simgesi",
                control = { WayfindrToggle(showPhoneBattery) { dashConfig.setShowPhoneBattery(it) } })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Target, "Daha keskin harita",
                if (sharperMap) "2× çizim, yüksek çözünürlüklü harita kutucukları · daha fazla bellek" else "Standart harita kutucukları",
                control = { WayfindrToggle(sharperMap) { dashConfig.setSharperMap(it) } }, last = true)
        }

        SectionLabel("Konsolda medya ve aramalar")
        // Re-check the grant on ON_RESUME so the chip flips to "On" the moment the user comes back
        // from the system notification-access screen (the Settings value isn't observable on its own).
        val lifecycleOwner = LocalLifecycleOwner.current
        fun answerGranted() = androidx.core.content.ContextCompat.checkSelfPermission(
            ctx, android.Manifest.permission.ANSWER_PHONE_CALLS,
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        var mediaGranted by remember { mutableStateOf(com.example.wayfindr.media.MediaInfoProvider.isAccessGranted(ctx)) }
        var callAnswerGranted by remember { mutableStateOf(answerGranted()) }
        DisposableEffect(lifecycleOwner) {
            val obs = LifecycleEventObserver { _, e ->
                if (e == Lifecycle.Event.ON_RESUME) {
                    mediaGranted = com.example.wayfindr.media.MediaInfoProvider.isAccessGranted(ctx)
                    callAnswerGranted = answerGranted()
                }
            }
            lifecycleOwner.lifecycle.addObserver(obs)
            onDispose { lifecycleOwner.lifecycle.removeObserver(obs) }
        }
        val callPermLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
            callAnswerGranted = it
        }
        // v34 always shows the media/call rows (whatever the edition), followed by the console
        // wallpaper and offline-map entries, which open NavPlus's screens (offMode 1 and 0).
        WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 6.dp) {
            SettingRow(
                WayfindrIcons.Zap,
                "Çalan medya ve aramalar konsolda",
                if (mediaGranted) "Etkin · sürüş sırasında şarkı bilgisi ve arayan gösterilir"
                else "Bildirim erişimine izin vermek için dokunun",
                control = {
                    WayfindrChip(
                        if (mediaGranted) "Açık" else "Etkinleştir",
                        if (mediaGranted) ChipTone.Gold else ChipTone.Off, dot = true,
                    )
                },
                onClick = if (mediaGranted) null else {
                    { runCatching { ctx.startActivity(com.example.wayfindr.media.MediaInfoProvider.accessSettingsIntent()) } }
                },
            )
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(
                WayfindrIcons.Bt,
                "Aramaları joystick ile yanıtla",
                if (callAnswerGranted) "Etkin · YUKARI yanıtlar, AŞAĞI reddeder"
                else "Aramaları yanıtlamak/reddetmek için dokunun",
                control = {
                    WayfindrChip(
                        if (callAnswerGranted) "Açık" else "Etkinleştir",
                        if (callAnswerGranted) ChipTone.Gold else ChipTone.Off, dot = true,
                    )
                },
                last = true,
                onClick = if (callAnswerGranted) null else {
                    { callPermLauncher.launch(android.Manifest.permission.ANSWER_PHONE_CALLS) }
                },
            )
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Dash, "Konsol duvar kağıdı",
                "Beklemedeyken konsolda fotoğraf, müzik şeridi, albüm kapağı",
                control = {}, onClick = { NavPlus.offMode = 1; NavPlus.openOffline() })
            WayfindrDivider(Modifier.padding(horizontal = 6.dp))
            SettingRow(WayfindrIcons.Download, "Çevrimdışı harita",
                "Harita dosyası yükle, aç/kapat, sil",
                control = {}, onClick = { NavPlus.offMode = 0; NavPlus.openOffline() })
        }

        SectionLabel("Ses ve yönlendirme")
        WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 14.dp) {
            WayfindrSegmented(listOf("Kapalı", "Uyarı sesi", "Tam TTS"), voice, {
                voiceManager.setMode(when (it) {
                    "Kapalı"  -> com.example.wayfindr.dash.nav.VoiceMode.OFF
                    "Tam TTS" -> com.example.wayfindr.dash.nav.VoiceMode.FULL
                    else      -> com.example.wayfindr.dash.nav.VoiceMode.CHIME
                })
            }, Modifier.fillMaxWidth())
        }

        SectionLabel("Birimler")
        WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 14.dp) {
            WayfindrSegmented(listOf("Kilometre", "Mil"), units, { units = it }, Modifier.fillMaxWidth())
        }

        Spacer(Modifier.height(22.dp))

        val appVersion = remember { com.example.wayfindr.data.UpdateChecker.currentVersionName(ctx) }
        Text(
            "Wayfindr v$appVersion",
            color = TextDis, fontSize = 11.sp, fontFamily = GeistMonoFamily,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp),
        )
    }
}

@Composable
private fun SectionLabel(label: String) {
    Eyebrow(label, Modifier.padding(top = 22.dp, bottom = 9.dp, start = 4.dp))
}

// internal: RidesScreen reuses this row (v34).
@Composable
internal fun SettingRow(
    icon: ImageVector,
    title: String,
    sub: String? = null,
    control: @Composable () -> Unit,
    last: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .let { if (onClick != null) it.clickable(onClick = onClick) else it }
            .padding(horizontal = 6.dp, vertical = 13.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(38.dp).clip(RoundedCornerShape(11.dp)).background(Surf2).border(1.dp, Line, RoundedCornerShape(11.dp)),
        ) {
            Icon(icon, contentDescription = null, tint = TextMid, modifier = Modifier.size(19.dp))
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = TextHi, fontSize = 14.5.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
            if (sub != null) Text(sub, color = TextLo, fontSize = 12.sp, modifier = Modifier.padding(top = 2.dp))
        }
        control()
    }
}
