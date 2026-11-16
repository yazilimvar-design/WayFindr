package com.example.wayfindr.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wayfindr.dash.DashConfig
import com.example.wayfindr.dash.MapTheme
import com.example.wayfindr.dash.RoutingSource
import com.example.wayfindr.dash.nav.NavPlus
import com.example.wayfindr.dash.nav.OfflineRouter
import com.example.wayfindr.dash.nav.PlaceSearch
import com.example.wayfindr.dash.nav.Route
import com.example.wayfindr.dash.nav.VoiceManager
import com.example.wayfindr.dash.nav.VoiceMode
import com.example.wayfindr.ui.WayfindrIcons
import com.example.wayfindr.ui.components.*
import com.example.wayfindr.ui.theme.*
import com.example.wayfindr.viewmodel.RouteViewModel
import com.example.wayfindr.data.SharedLocation
import java.util.Locale

@Composable
fun RouteScreen(
    onBack: () -> Unit,
    /** The stops plus the route the rider picked (null = let the dash route them itself). */
    onSentToDash: (List<SharedLocation>, Route?) -> Unit,
    routeViewModel: RouteViewModel = viewModel(),
) {
    val routeState by routeViewModel.state.collectAsState()
    val dest       = routeState.destination
    val sourceDestName = dest?.name
    val destName   = when (val name = dest?.name?.ifBlank { "" } ?: "") {
        "", "Shared location" -> "Paylaşılan konum"
        "Dropped pin"         -> "Haritadan seçilen konum"
        else                  -> name
    }
    val destSub    = when {
        dest?.lat != null && dest.lng != null ->
            "%.5f, %.5f".format(dest.lat, dest.lng)
        dest?.url != null -> "Harita bağlantısı"
        else              -> ""
    }

    val savedList by routeViewModel.saved.collectAsState()
    val ctx = LocalContext.current
    val dashConfig = remember { DashConfig.get(ctx) }
    val voiceManager = remember { VoiceManager.get(ctx) }
    val voiceMode by voiceManager.mode.collectAsState()
    val voice = when (voiceMode) {
        VoiceMode.OFF   -> "Kapalı"
        VoiceMode.CHIME -> "Yalnızca uyarı sesi"
        VoiceMode.FULL  -> "Tam sesli yönlendirme"
    }
    var sent by remember { mutableStateOf(false) }
    var showSave by remember { mutableStateOf(false) }
    var editing by remember { mutableStateOf<com.example.wayfindr.data.SavedLocation?>(null) }
    var sharing by remember { mutableStateOf<com.example.wayfindr.data.SavedLocation?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var showSavedDestinations by remember { mutableStateOf(true) }
    // Place suggestions under the search box, fetched as the rider types.
    var suggestions by remember { mutableStateOf<List<PlaceSearch.Hit>>(emptyList()) }
    var suggestionsLoading by remember { mutableStateOf(false) }
    var suggestionsStatus by remember { mutableStateOf<String?>(null) }
    /** True right after the query was filled in for the rider (picked hit, new destination): no lookup. */
    var suggestionsSuppressed by remember { mutableStateOf(false) }

    // Sync search query with destination name when it changes (except when resolving)
    LaunchedEffect(destName) {
        if (!routeState.isResolving && !sourceDestName.isNullOrBlank() &&
            sourceDestName != "Shared location" && sourceDestName != "Dropped pin"
        ) {
            suggestionsSuppressed = true
            suggestions = emptyList()
            searchQuery = destName
        }
    }

    // Debounced place suggestions for what the rider is typing.
    LaunchedEffect(searchQuery, suggestionsSuppressed) {
        val query = searchQuery.trim()
        suggestions = emptyList()
        suggestionsLoading = false
        suggestionsStatus = null
        if (suggestionsSuppressed || query.length < 2) return@LaunchedEffect
        kotlinx.coroutines.delay(350)
        suggestionsLoading = true
        val found = PlaceSearch.suggestions(ctx, query, routeState.currentLocation)
        if (!suggestionsSuppressed && searchQuery.trim() == query) {
            suggestions = found
            if (found.isEmpty()) {
                suggestionsStatus = if (PlaceSearch.lastError == "no match") {
                    "Eşleşme bulunamadı."
                } else if ((PlaceSearch.lastError ?: "").contains("no internet", ignoreCase = true)) {
                    "İnternet bağlantısı yok; yer önerileri alınamadı."
                } else {
                    "Yer arama hizmetine ulaşılamadı. Lütfen daha sonra tekrar deneyin."
                }
            }
        }
        suggestionsLoading = false
    }

    val selectSuggestion: (PlaceSearch.Hit) -> Unit = remember(routeViewModel) {
        { hit: PlaceSearch.Hit ->
            suggestionsSuppressed = true
            suggestions = emptyList()
            suggestionsLoading = false
            suggestionsStatus = null
            searchQuery = hit.name
            showSavedDestinations = false
            routeViewModel.selectPlaceSuggestion(hit)
        }
    }

    // Nav needs GPS (+ NEARBY_WIFI_DEVICES on 13+ to reach the dash). The Dash screen
    // requests these, but a rider can go straight Share → Route → Start navigation
    // without ever opening Dash — so request them here too. Without this the ride
    // starts with no GPS (and previously crashed the foreground service on 14+).
    val navPermissions = remember {
        buildList {
            add(Manifest.permission.ACCESS_FINE_LOCATION)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                add(Manifest.permission.NEARBY_WIFI_DEVICES)
        }.toTypedArray()
    }
    fun hasLocationPermission() =
        ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
    // Whether or not the grant succeeds, proceed (the service start is now defensive and
    // the rider can still preview); denying just means no live GPS until they grant it.
    val navPermLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { sent = true }

    // Photo picker: no storage permission needed. v34 still registers it but no longer shows
    // the "load route from QR image" button that launched it.
    @Suppress("UNUSED_VARIABLE")
    val qrPicker = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) routeViewModel.importQrImage(uri)
    }

    val locationPermLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { routeViewModel.startLocationUpdates() }

    LaunchedEffect(Unit) {
        if (hasLocationPermission()) {
            routeViewModel.startLocationUpdates()
        } else {
            locationPermLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    LaunchedEffect(sent) {
        if (sent) {
            kotlinx.coroutines.delay(650)
            onSentToDash(routeState.waypoints, routeState.route)
        }
    }

    Column(Modifier.fillMaxSize()) {
        // Map preview (top 46%)
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.46f)
                .background(MapBase),
        ) {
            // Real map preview — destination pin + route line (+ the alternatives, if any).
            WayfindrMap(
                riderLat = routeState.currentLocation?.first,
                riderLng = routeState.currentLocation?.second,
                riderBearing = routeState.currentBearing,
                recenterKey = routeState.locationRefreshKey,
                followRider = false,
                centerOnRiderInitially = true,
                dest = dest?.let { d -> if (d.lat != null && d.lng != null) d.lat to d.lng else null },
                destinations = routeState.waypoints.mapNotNull { stop ->
                    if (stop.lat != null && stop.lng != null) stop.lat to stop.lng else null
                },
                routePoints = routeState.route?.geometry.orEmpty(),
                trafficIntervals = routeState.route?.traffic.orEmpty(),
                routeAlternatives = routeState.routeAlternatives.takeIf { it.size > 1 }.orEmpty(),
                selectedRouteIndex = routeState.selectedRouteIndex,
                hasLocationPermission = true,
                fitRoute = routeState.route != null && !routeState.routing,
                onMapClick = { routeViewModel.selectLocation(it.latitude, it.longitude) },
                modifier = Modifier.fillMaxSize(),
            )

            // Top bar overlay
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(listOf(Color(0xD9080C0C), Color.Transparent))
                    )
                    .padding(horizontal = 16.dp, vertical = 14.dp),
            ) {
                WayfindrIconBtn(
                    WayfindrIcons.ChevronLeft,
                    onClick = onBack,
                    size = 40.dp,
                    modifier = Modifier.background(Color(0xB30D0F11), IconBtnShape),
                )
                Spacer(Modifier.width(12.dp))
                WayfindrChip(
                    when {
                        routeState.isResolving -> "Bağlantı çözümleniyor…"
                        routeState.fromWayfindr -> "Wayfindr'dan paylaşıldı"
                        else -> "Google Haritalar'dan paylaşıldı"
                    },
                    tone = if (routeState.isResolving) ChipTone.Gold else ChipTone.Neutral,
                    icon = WayfindrIcons.Share,
                    modifier = Modifier.background(Color(0xB30D0F11), CircleShape),
                )
                WayfindrIconBtn(
                    WayfindrIcons.Recenter,
                    onClick = routeViewModel::refreshCurrentLocation,
                    size = 40.dp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .background(Color(0xB30D0F11), IconBtnShape),
                )
            }

            if (routeState.routeAlternatives.size > 1) {
                RouteMapLegend(
                    routes = routeState.routeAlternatives,
                    selectedIndex = routeState.selectedRouteIndex,
                    onSelect = routeViewModel::selectRoute,
                    modifier = Modifier.align(Alignment.BottomStart),
                )
            }
        }

        // Detail sheet (overlapping bottom portion)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))
                .background(Bg1)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 8.dp)
                .padding(bottom = 20.dp),
        ) {
            // Drag handle
            Box(
                Modifier
                    .width(40.dp).height(4.dp)
                    .clip(CircleShape).background(Line3)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            Spacer(Modifier.height(12.dp))

            // Search Bar (+ live place suggestions)
            WayfindrCard(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), padding = 4.dp) {
                Column {
                    androidx.compose.material3.TextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            suggestionsSuppressed = false
                            suggestionsStatus = null
                        },
                        placeholder = {
                            Text((if (routeState.waypoints.isEmpty()) "Yer ara" else "Durak ekle") +
                                " · OpenStreetMap", color = TextLo,
                                maxLines = 1, overflow = TextOverflow.Ellipsis)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(WayfindrIcons.Search, null, tint = Gold, modifier = Modifier.size(20.dp)) },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                Icon(
                                    WayfindrIcons.Cross, "Aramayı temizle",
                                    tint = TextLo,
                                    modifier = Modifier.size(18.dp).clickable {
                                        searchQuery = ""
                                        suggestionsSuppressed = false
                                        suggestions = emptyList()
                                        suggestionsStatus = null
                                    }
                                )
                            }
                        },
                        singleLine = true,
                        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                            imeAction = androidx.compose.ui.text.input.ImeAction.Search
                        ),
                        keyboardActions = androidx.compose.foundation.text.KeyboardActions(
                            onSearch = {
                                val first = suggestions.firstOrNull()
                                if (first != null) selectSuggestion(first)
                                else routeViewModel.search(searchQuery)
                            }
                        ),
                        colors = androidx.compose.material3.TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    if (suggestionsLoading || suggestions.isNotEmpty() || suggestionsStatus != null) {
                        Box(Modifier.fillMaxWidth().height(1.dp).background(Line))
                        if (suggestionsLoading) {
                            Text("OpenStreetMap'te aranıyor…", color = TextLo, fontSize = 12.sp,
                                fontFamily = GeistFamily,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp))
                        }
                        suggestionsStatus?.let { status ->
                            Text(status, color = TextLo, fontSize = 12.sp, fontFamily = GeistFamily,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp))
                        }
                        suggestions.forEach { hit ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { selectSuggestion(hit) }
                                    .padding(horizontal = 14.dp, vertical = 11.dp),
                            ) {
                                Icon(WayfindrIcons.LocationPin, null, tint = Gold, modifier = Modifier.size(19.dp))
                                Spacer(Modifier.width(12.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(hit.name, color = TextHi, fontSize = 14.sp, fontWeight = FontWeight.Medium,
                                        fontFamily = GeistFamily, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                    if (hit.subtitle.isNotBlank()) {
                                        Text(hit.subtitle, color = TextLo, fontSize = 12.sp, fontFamily = GeistFamily,
                                            maxLines = 1, overflow = TextOverflow.Ellipsis)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            routeState.searchNote?.let { note ->
                Text(
                    when {
                        note.contains("no match", ignoreCase = true) -> "Eşleşme bulunamadı."
                        note.contains("no internet", ignoreCase = true) -> "İnternet bağlantısı yok; arama yapılamadı."
                        else -> "Arama hizmetine ulaşılamadı. Lütfen bağlantınızı kontrol edip tekrar deneyin."
                    },
                    color = TextLo, fontSize = 12.sp, modifier = Modifier.padding(start = 6.dp, bottom = 12.dp),
                )
            }
            // v34: the QR-image button opens the offline map / dash photo screen instead.
            WayfindrBtn(
                "Çevrimdışı harita / konsol fotoğrafı",
                onClick = { NavPlus.openOffline() },
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                icon = WayfindrIcons.QrScan,
                variant = BtnVariant.Ghost,
                size = BtnSize.Sm,
            )

            // A route another Wayfindr rider shared, not saved here yet → offer to keep it.
            routeState.sharedRouteName?.let { sharedName ->
                WayfindrCard(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), padding = 14.dp) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(WayfindrIcons.Share, contentDescription = null, tint = Gold, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(sharedName, color = TextHi, fontSize = 15.sp, fontWeight = FontWeight.SemiBold,
                                fontFamily = GeistFamily, maxLines = 1)
                            Text("Paylaşılan rota · ${routeState.waypoints.size} durak",
                                color = TextMid, fontSize = 12.sp, fontFamily = GeistFamily)
                        }
                        WayfindrBtn(
                            label = "Kaydet",
                            onClick = { showSave = true },
                            icon = WayfindrIcons.Pin,
                            variant = BtnVariant.Secondary,
                            size = BtnSize.Sm,
                        )
                    }
                }
            }

            // No destination yet → tell the rider exactly how to start one.
            if (routeState.waypoints.isNotEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
                ) {
                    Eyebrow("Rota durakları · ${routeState.waypoints.size}")
                    WayfindrBtn(
                        label = "Temizle",
                        onClick = routeViewModel::clearDestinations,
                        icon = WayfindrIcons.X,
                        variant = BtnVariant.Ghost,
                        size = BtnSize.Sm,
                    )
                }
                WayfindrCard(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), padding = 6.dp) {
                    routeState.waypoints.forEachIndexed { index, stop ->
                        if (index > 0) WayfindrDivider(Modifier.padding(horizontal = 4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 9.dp)) {
                            Text(if (index == routeState.waypoints.lastIndex) "Son" else "${index + 1}", color = Gold, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(38.dp))
                            Text(stop.name, color = TextHi, fontSize = 14.sp, fontFamily = GeistFamily, modifier = Modifier.weight(1f), maxLines = 1)
                            if (routeState.waypoints.size > 1) {
                                Icon(WayfindrIcons.MoveUp, "Durağı öne al", tint = if (index > 0) TextMid else TextLo,
                                    modifier = Modifier.size(19.dp).clickable(enabled = index > 0) { routeViewModel.moveWaypoint(index, index - 1) })
                                Icon(WayfindrIcons.MoveDown, "Durağı geriye al", tint = if (index < routeState.waypoints.lastIndex) TextMid else TextLo,
                                    modifier = Modifier.size(19.dp).clickable(enabled = index < routeState.waypoints.lastIndex) { routeViewModel.moveWaypoint(index, index + 1) })
                                Icon(WayfindrIcons.Cross, "Durağı kaldır", tint = TextLo,
                                    modifier = Modifier.padding(start = 4.dp).size(18.dp).clickable { routeViewModel.removeWaypoint(index) })
                            }
                        }
                    }
                }
            }

            if ((dest?.lat == null || dest.lng == null) && !routeState.isResolving) {
                WayfindrCard(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), padding = 16.dp) {
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(WayfindrIcons.Share, contentDescription = null, tint = Gold, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text("Google Haritalar'dan rota başlatın", color = TextHi, fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
                            Text(
                                "Google Haritalar'ı açın → bir yer seçin → Paylaş'a dokunun → Wayfindr'ı seçin. " +
                                    "Hedef burada görünür; ardından gösterge ekranına göndermek için Navigasyonu başlat'a dokunun.",
                                color = TextMid, fontSize = 13.sp, fontFamily = GeistFamily,
                                modifier = Modifier.padding(top = 4.dp),
                            )
                        }
                    }
                }
            }

            Eyebrow(if (routeState.waypoints.size > 1) "Son hedef" else "Hedef", Modifier.padding(bottom = 6.dp))

            Row(verticalAlignment = Alignment.Top) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(GoldTint),
                ) {
                    Icon(WayfindrIcons.LocationPin, contentDescription = null, tint = Gold, modifier = Modifier.size(22.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    if (routeState.isResolving) {
                        Text("Çözümleniyor…", color = TextLo, fontSize = 19.sp, fontWeight = FontWeight.Bold, fontFamily = GeistFamily, letterSpacing = (-0.38).sp)
                    } else {
                        Text(destName, color = TextHi, fontSize = 19.sp, fontWeight = FontWeight.Bold, fontFamily = GeistFamily, letterSpacing = (-0.38).sp)
                    }
                    if (destSub.isNotBlank()) {
                        Text(destSub, color = TextMid, fontSize = 13.sp, fontFamily = GeistMonoFamily, modifier = Modifier.padding(top = 2.dp))
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            // ── Route alternatives (when the router offered more than one) ──
            if (routeState.routeAlternatives.size > 1) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Eyebrow("OSM rotaları · ${routeState.routeAlternatives.size} seçenek", Modifier.padding(bottom = 2.dp))
                    routeState.routeAlternatives.forEachIndexed { index, route ->
                        val selected = index == routeState.selectedRouteIndex
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (selected) Surf3 else Surf1)
                                .border(1.dp, if (selected) Gold else Line, RoundedCornerShape(12.dp))
                                .clickable(enabled = !routeState.routing) { routeViewModel.selectRoute(index) }
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                        ) {
                            Box(
                                Modifier.width(4.dp).height(34.dp).clip(CircleShape)
                                    .background(RouteAlternativeStyle.composeColor(index))
                            )
                            Spacer(Modifier.width(10.dp))
                            Column(Modifier.weight(1f)) {
                                Text(if (index == 0) "Ana rota" else "Alternatif $index",
                                    color = if (selected) TextHi else TextMid, fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily)
                                Text(formatAlternativeDistance(route.totalMeters) + " · " +
                                    formatAlternativeDuration(route.totalSeconds),
                                    color = TextLo, fontSize = 12.sp, fontFamily = GeistFamily,
                                    modifier = Modifier.padding(top = 3.dp))
                                if (index > 0) {
                                    val main = routeState.routeAlternatives.first()
                                    Text("Ana rotaya göre " + routeDifferenceLabel(
                                            route.totalMeters, route.totalSeconds, main.totalMeters, main.totalSeconds),
                                        color = TextLo, fontSize = 10.sp, fontFamily = GeistFamily,
                                        modifier = Modifier.padding(top = 2.dp))
                                }
                            }
                            if (selected) {
                                Icon(WayfindrIcons.Check, "Seçili rota", tint = Gold, modifier = Modifier.size(20.dp))
                            } else {
                                Text("Seç", color = Gold, fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
                                    fontFamily = GeistFamily)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            // Route stats (real values once routing completes)
            val routing = routeState.routing
            val statsList = listOf(
                Triple(if (routing) "…" else (routeState.distanceText ?: "—"), "", "Mesafe"),
                Triple(if (routing) "…" else (routeState.durationText ?: "—"), "", "Süre"),
                Triple(if (routing) "…" else (routeState.etaText ?: "—"), "", "Varış"),
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                statsList.forEach { (v, u, k) ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Surf1)
                            .border(1.dp, Line, RoundedCornerShape(14.dp))
                            .padding(13.dp),
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(v, color = TextHi, fontSize = 22.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistMonoFamily)
                            Spacer(Modifier.width(4.dp))
                            Text(u, color = TextLo, fontSize = 11.sp, fontFamily = GeistMonoFamily, modifier = Modifier.padding(bottom = 2.dp))
                        }
                        Eyebrow(k, Modifier.padding(top = 4.dp))
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // ── Routing + map theme (moved here from Settings) ──
            val routingSource by dashConfig.routingSource.collectAsState()
            val mapTheme by dashConfig.mapTheme.collectAsState()
            val brouterInstalled = remember { OfflineRouter.isInstalled(ctx) }
            ChoiceHeader("Rota sağlayıcısı", when (routingSource) {
                RoutingSource.GOOGLE -> "Motosiklet · canlı trafik"
                RoutingSource.OSM -> "Ücretsiz sunucu · internet gerekir"
                RoutingSource.BROUTER ->
                    if (brouterInstalled) "Telefonda · internet gerekmez" else "BRouter uygulaması yüklü değil"
            })
            WayfindrSegmented(
                options = RoutingSource.available.map { it.label },
                selected = routingSource.label,
                onSelect = { label ->
                    val src = RoutingSource.values().first { it.label == label }
                    if (src != routingSource) { dashConfig.setRoutingSource(src); routeViewModel.recomputeRoute() }
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            )
            ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası")
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 22.dp),
            ) {
                MapTheme.values().toList().chunked(2).forEach { rowThemes ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        rowThemes.forEach { theme ->
                            val selected = theme == mapTheme
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (selected) Surf3 else Surf1)
                                    .border(1.dp, if (selected) Gold else Line, RoundedCornerShape(12.dp))
                                    .clickable { dashConfig.setMapTheme(theme) }
                                    .padding(horizontal = 12.dp, vertical = 11.dp),
                            ) {
                                Box(
                                    Modifier.size(8.dp).clip(CircleShape)
                                        .background(if (selected) Gold else TextLo)
                                )
                                Spacer(Modifier.width(9.dp))
                                Text(theme.label, color = if (selected) TextHi else TextMid, fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily,
                                    maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                        }
                        if (rowThemes.size == 1) Spacer(Modifier.weight(1f))
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(bottom = 9.dp),
            ) {
                Eyebrow("Sesli yönlendirme")
                Icon(
                    if (voiceMode == VoiceMode.OFF) WayfindrIcons.SpeakerOff else WayfindrIcons.Speaker,
                    contentDescription = null,
                    tint = if (voiceMode == VoiceMode.OFF) TextLo else Gold,
                    modifier = Modifier.size(18.dp),
                )
            }

            WayfindrSegmented(
                options = listOf("Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"),
                selected = voice,
                onSelect = {
                    voiceManager.setMode(when (it) {
                        "Kapalı" -> VoiceMode.OFF
                        "Tam sesli yönlendirme" -> VoiceMode.FULL
                        else -> VoiceMode.CHIME
                    })
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 22.dp),
            )

            val canStart = dest?.lat != null && dest.lng != null && !routeState.isResolving && !routeState.routing
            WayfindrBtn(
                label = when {
                    sent                   -> "Navigasyon başlatılıyor…"
                    routeState.isResolving -> "Hedef çözümleniyor…"
                    routeState.routing     -> "Rota aranıyor…"
                    else                   -> "Navigasyonu başlat"
                },
                onClick = { if (hasLocationPermission()) sent = true else navPermLauncher.launch(navPermissions) },
                icon = if (sent) WayfindrIcons.Check else WayfindrIcons.Navi,
                variant = if (sent) BtnVariant.Secondary else BtnVariant.Primary,
                size = BtnSize.Lg,
                enabled = !sent && canStart,
                modifier = Modifier.fillMaxWidth(),
            )

            if (canStart) {
                Spacer(Modifier.height(10.dp))
                WayfindrBtn(
                    label = "Bu hedefi kaydet",
                    onClick = { showSave = true },
                    icon = WayfindrIcons.Pin,
                    variant = BtnVariant.Ghost,
                    size = BtnSize.Md,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            // ── Saved destinations (collapsible) ──
            if (savedList.isNotEmpty()) {
                Spacer(Modifier.height(22.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp, start = 4.dp),
                ) {
                    Eyebrow("Kayıtlı hedefler · ${savedList.size}")
                    Text(
                        if (showSavedDestinations) "Gizle" else "Göster",
                        color = Gold, fontSize = 12.sp,
                        modifier = Modifier
                            .clickable { showSavedDestinations = !showSavedDestinations }
                            .padding(6.dp),
                    )
                }
                if (showSavedDestinations) {
                    WayfindrCard(modifier = Modifier.fillMaxWidth(), padding = 6.dp) {
                        savedList.forEachIndexed { i, loc ->
                            if (i > 0) WayfindrDivider(Modifier.padding(horizontal = 4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable {
                                        routeViewModel.selectSaved(loc)
                                        showSavedDestinations = false
                                    }
                                    .padding(horizontal = 6.dp, vertical = 12.dp),
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.size(40.dp).clip(RoundedCornerShape(11.dp)).background(GoldTint),
                                ) { Icon(WayfindrIcons.LocationPin, null, tint = Gold, modifier = Modifier.size(20.dp)) }
                                Spacer(Modifier.width(13.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(loc.name, color = TextHi, fontSize = 14.5.sp, fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily, maxLines = 1)
                                    Text(
                                        if (loc.note.isNotBlank()) loc.note else "%.4f, %.4f".format(loc.lat, loc.lng),
                                        color = TextLo, fontSize = 12.sp, fontFamily = GeistMonoFamily,
                                        modifier = Modifier.padding(top = 2.dp), maxLines = 1,
                                    )
                                }
                                Icon(
                                    WayfindrIcons.Share, "${loc.name} konumunu paylaş", tint = TextLo,
                                    modifier = Modifier.padding(end = 14.dp).size(18.dp)
                                        .clickable { sharing = loc },
                                )
                                Icon(
                                    WayfindrIcons.Edit, "Düzenle", tint = TextLo,
                                    modifier = Modifier.size(18.dp).clickable { editing = loc },
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showSave) SaveLocationDialog(
        defaultName = routeState.sharedRouteName ?: destName,
        onSave = { name, note -> routeViewModel.saveCurrentDestination(name, note); showSave = false },
        onDismiss = { showSave = false },
    )
    sharing?.let { loc -> RouteQrDialog(loc, onDismiss = { sharing = null }) }
    editing?.let { loc ->
        EditLocationDialog(
            loc = loc,
            onSave = { name, note -> routeViewModel.renameSaved(loc, name, note); editing = null },
            onDelete = { routeViewModel.deleteSaved(loc); editing = null },
            onDismiss = { editing = null },
        )
    }
}

@Composable
private fun SaveLocationDialog(defaultName: String, onSave: (String, String) -> Unit, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf(defaultName) }
    var note by remember { mutableStateOf("") }
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { androidx.compose.material3.TextButton(enabled = name.isNotBlank(), onClick = { onSave(name.trim(), note.trim()) }) { Text("Kaydet", color = if (name.isNotBlank()) Gold else TextLo) } },
        dismissButton = { androidx.compose.material3.TextButton(onClick = onDismiss) { Text("İptal", color = TextMid) } },
        title = { Text("Hedefi kaydet", color = TextHi) },
        text = {
            Column {
                androidx.compose.material3.OutlinedTextField(name, { name = it }, label = { Text("Ad") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                androidx.compose.material3.OutlinedTextField(note, { note = it }, label = { Text("Not (isteğe bağlı)") }, singleLine = true, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
            }
        },
        containerColor = Surf1,
    )
}

@Composable
private fun EditLocationDialog(loc: com.example.wayfindr.data.SavedLocation, onSave: (String, String) -> Unit, onDelete: () -> Unit, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf(loc.name) }
    var note by remember { mutableStateOf(loc.note) }
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { androidx.compose.material3.TextButton(enabled = name.isNotBlank(), onClick = { onSave(name.trim(), note.trim()) }) { Text("Kaydet", color = if (name.isNotBlank()) Gold else TextLo) } },
        dismissButton = { androidx.compose.material3.TextButton(onClick = onDelete) { Text("Sil", color = Alert) } },
        title = { Text("Hedefi düzenle", color = TextHi) },
        text = {
            Column {
                androidx.compose.material3.OutlinedTextField(name, { name = it }, label = { Text("Ad") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                androidx.compose.material3.OutlinedTextField(note, { note = it }, label = { Text("Not") }, singleLine = true, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
            }
        },
        containerColor = Surf1,
    )
}

/** "850 m" / "12,4 km" for a route alternative. */
private fun formatAlternativeDistance(meters: Double): String =
    if (meters < 1000.0) "${meters.toInt()} m"
    else String.format(Locale.forLanguageTag("tr-TR"), "%.1f km", meters / 1000.0)

/** "45 dk" / "2 sa 5 dk" for a route alternative. */
private fun formatAlternativeDuration(seconds: Double): String {
    val minutes = (seconds / 60.0).toInt().coerceAtLeast(0)
    return if (minutes >= 60) "${minutes / 60} sa ${minutes % 60} dk" else "$minutes dk"
}

/** Strip along the bottom of the map: one chip per alternative, in its line colour, with its difference. */
@Composable
private fun RouteMapLegend(
    routes: List<Route>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (routes.size <= 1) return
    val main = routes.first()
    Column(
        modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xE80B0E12))))
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Text("ANA ROTAYA GÖRE · MESAFE / SÜRE FARKI", color = Color.White.copy(alpha = 0.82f), fontSize = 9.sp,
            fontWeight = FontWeight.Bold, fontFamily = GeistMonoFamily)
        Spacer(Modifier.height(6.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        ) {
            routes.take(3).forEachIndexed { index, route ->
                val selected = index == selectedIndex
                val shape = RoundedCornerShape(10.dp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(shape)
                        .background(Color(if (selected) 0xE5262B32 else 0xCF11151A))
                        .border(1.dp, if (selected) Gold else Color.White.copy(alpha = 0.18f), shape)
                        .clickable { onSelect(index) }
                        .padding(horizontal = 9.dp, vertical = 6.dp),
                ) {
                    Box(
                        Modifier.width(4.dp).height(27.dp).clip(CircleShape)
                            .background(RouteAlternativeStyle.composeColor(index))
                    )
                    Spacer(Modifier.width(7.dp))
                    Column {
                        Text(if (index == 0) "Ana rota" else "Alternatif $index", color = TextHi, fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold, fontFamily = GeistFamily, maxLines = 1)
                        Text(
                            routeDifferenceLabel(route.totalMeters, route.totalSeconds, main.totalMeters, main.totalSeconds),
                            color = Color.White.copy(alpha = 0.86f), fontSize = 9.sp, fontFamily = GeistMonoFamily,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    }
}

/** Small header above a segmented picker: label on the left, what the choice means on the right. */
@Composable
private fun ChoiceHeader(label: String, detail: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(bottom = 9.dp),
    ) {
        Eyebrow(label)
        Text(detail, color = TextLo, fontSize = 11.5.sp, fontFamily = GeistFamily, maxLines = 1,
            modifier = Modifier.padding(start = 12.dp))
    }
}
