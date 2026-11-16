package com.id.tvstream.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.id.tvstream.ads.UnityBannerAd
import com.id.tvstream.data.ChannelRepository
import com.id.tvstream.model.Channel
import com.id.tvstream.player.VideoPlayer
import com.id.tvstream.ui.theme.DarkBackground
import com.id.tvstream.ui.theme.DarkSurface
import com.id.tvstream.ui.theme.DarkSurfaceVariant
import com.id.tvstream.ui.theme.LiveRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    isFullscreen: Boolean,
    onToggleFullscreen: () -> Unit,
    onEnterPiP: () -> Unit
) {
    val context = LocalContext.current
    var channelList by remember { mutableStateOf(ChannelRepository.loadChannels(context)) }
    var selectedChannel by remember {
        mutableStateOf(
            channelList.firstOrNull() ?: Channel(
                id = "empty",
                name = "Tidak Ada Saluran",
                category = "Semua",
                streamUrl = ""
            )
        )
    }
    var selectedCategory by remember {
        mutableStateOf(channelList.firstOrNull()?.category ?: "Semua")
    }
    var searchQuery by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }
    var showResetConfirmDialog by remember { mutableStateOf(false) }
    var offlineChannelIds by remember { mutableStateOf(setOf<String>()) }
    var hideOfflineChannels by remember { mutableStateOf(false) }

    // State untuk Aksi Tekan Lama (Edit & Delete)
    var channelToManage by remember { mutableStateOf<Channel?>(null) }
    var showActionDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteConfirmDialog by remember { mutableStateOf(false) }

    // Pastikan selectedChannel tetap sinkron jika diperbarui
    LaunchedEffect(channelList) {
        if (channelList.isNotEmpty()) {
            if (channelList.none { it.id == selectedChannel.id }) {
                selectedChannel = channelList.first()
            }
        }
    }

    // Daftar kategori dinamis dari channel
    val categories = remember(channelList) {
        listOf("Semua") + channelList.map { it.category }.distinct()
    }

    // Filter daftar channel berdasarkan kategori, pencarian, dan status aktif
    val filteredChannels = remember(channelList, selectedCategory, searchQuery, hideOfflineChannels, offlineChannelIds) {
        val q = searchQuery.trim()
        val list = if (hideOfflineChannels) {
            channelList.filter { it.id !in offlineChannelIds }
        } else {
            channelList
        }

        if (q.isNotEmpty()) {
            list.filter { channel ->
                channel.name.contains(q, ignoreCase = true) ||
                channel.category.contains(q, ignoreCase = true)
            }
        } else {
            if (selectedCategory == "Semua") {
                list
            } else {
                list.filter { it.category == selectedCategory }
            }
        }
    }

    // Aksi lompat ke saluran berikutnya
    val selectNextChannel = {
        val currentIndex = filteredChannels.indexOfFirst { it.id == selectedChannel.id }
        if (currentIndex != -1 && currentIndex < filteredChannels.size - 1) {
            selectedChannel = filteredChannels[currentIndex + 1]
        } else if (filteredChannels.isNotEmpty()) {
            selectedChannel = filteredChannels.first()
        }
    }

    if (isFullscreen) {
        // Mode Layar Penuh: Hanya tampilkan Video Player
        VideoPlayer(
            streamUrl = selectedChannel.streamUrl,
            isFullscreen = true,
            onToggleFullscreen = onToggleFullscreen,
            onPlaybackError = {
                offlineChannelIds = offlineChannelIds + selectedChannel.id
            },
            onPlaybackSuccess = {
                offlineChannelIds = offlineChannelIds - selectedChannel.id
            },
            onNextChannel = selectNextChannel,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        // Mode Portrait: Tampilan Normal HP
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Tv,
                                contentDescription = null,
                                tint = LiveRed
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Tiviku",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    },
                    actions = {
                        // Tombol Picture-in-Picture
                        IconButton(onClick = onEnterPiP) {
                            Icon(
                                imageVector = Icons.Default.PictureInPictureAlt,
                                contentDescription = "PiP Mode",
                                tint = Color.White
                            )
                        }
                        // Tombol Tambah Channel / M3U
                        IconButton(onClick = { showAddDialog = true }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Tambah Channel",
                                tint = Color.White
                            )
                        }
                        // Menu Opsi Tambahan (Reset)
                        Box {
                            IconButton(onClick = { showMenu = !showMenu }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Menu Lainnya",
                                    tint = Color.White
                                )
                            }
                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = {
                                        Text(if (hideOfflineChannels) "Tampilkan Semua Saluran" else "Sembunyikan Saluran Mati")
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = if (hideOfflineChannels) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                            contentDescription = null
                                        )
                                    },
                                    onClick = {
                                        showMenu = false
                                        hideOfflineChannels = !hideOfflineChannels
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Reset ke Saluran Bawaan") },
                                    leadingIcon = {
                                        Icon(Icons.Default.Refresh, contentDescription = null)
                                    },
                                    onClick = {
                                        showMenu = false
                                        showResetConfirmDialog = true
                                    }
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
                )
            },
            bottomBar = {
                UnityBannerAd()
            },
            containerColor = DarkBackground
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // 1. Pemutar Video di Bagian Atas
                VideoPlayer(
                    streamUrl = selectedChannel.streamUrl,
                    isFullscreen = false,
                    onToggleFullscreen = onToggleFullscreen,
                    onPlaybackError = {
                        offlineChannelIds = offlineChannelIds + selectedChannel.id
                    },
                    onPlaybackSuccess = {
                        offlineChannelIds = offlineChannelIds - selectedChannel.id
                    },
                    onNextChannel = selectNextChannel
                )

                // Info Saluran yang Sedang Ditonton
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkSurface)
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = selectedChannel.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = selectedChannel.description.ifEmpty { "Kategori: ${selectedChannel.category}" },
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    AssistChip(
                        onClick = {},
                        label = { Text(selectedChannel.category) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = DarkSurfaceVariant,
                            labelColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Kolom Pencarian
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Cari saluran TV atau negara...", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Cari", tint = Color.Gray) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Hapus", tint = Color.Gray)
                            }
                        }
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkSurface,
                        unfocusedContainerColor = DarkSurface,
                        focusedBorderColor = LiveRed,
                        unfocusedBorderColor = DarkSurfaceVariant,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Baris Kategori
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(categories) { category ->
                        val isSelected = (category == selectedCategory)
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedCategory = category },
                            label = { Text(category) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = LiveRed,
                                selectedLabelColor = Color.White,
                                containerColor = DarkSurfaceVariant,
                                labelColor = Color.LightGray
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 2. Daftar Channel TV
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (filteredChannels.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 40.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Default.TvOff,
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(48.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Tidak ada saluran ditemukan",
                                        color = Color.Gray,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    } else {
                        items(filteredChannels, key = { it.id }) { channel ->
                            val isPlaying = (channel.id == selectedChannel.id)
                            val isOffline = channel.id in offlineChannelIds
                            ChannelCard(
                                channel = channel,
                                isPlaying = isPlaying,
                                isOffline = isOffline,
                                onClick = { selectedChannel = channel },
                                onLongClick = {
                                    channelToManage = channel
                                    showActionDialog = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    // Dialog Pilihan Aksi (Edit / Hapus) saat Tekan Lama
    if (showActionDialog && channelToManage != null) {
        val target = channelToManage!!
        ChannelActionDialog(
            channel = target,
            onDismiss = {
                showActionDialog = false
            },
            onEdit = {
                showActionDialog = false
                showEditDialog = true
            },
            onDelete = {
                showActionDialog = false
                showDeleteConfirmDialog = true
            }
        )
    }

    // Dialog Edit Saluran
    if (showEditDialog && channelToManage != null) {
        val target = channelToManage!!
        EditChannelDialog(
            channel = target,
            onDismiss = { showEditDialog = false },
            onSave = { updatedChannel ->
                val updatedList = channelList.map {
                    if (it.id == updatedChannel.id) updatedChannel else it
                }
                channelList = updatedList
                if (selectedChannel.id == updatedChannel.id) {
                    selectedChannel = updatedChannel
                }
                ChannelRepository.saveChannels(context, updatedList)
                showEditDialog = false
            }
        )
    }

    // Dialog Konfirmasi Hapus Saluran
    if (showDeleteConfirmDialog && channelToManage != null) {
        val target = channelToManage!!
        DeleteConfirmDialog(
            channel = target,
            onDismiss = { showDeleteConfirmDialog = false },
            onConfirm = {
                val updatedList = channelList.filter { it.id != target.id }
                channelList = updatedList
                if (selectedChannel.id == target.id) {
                    selectedChannel = updatedList.firstOrNull() ?: Channel(
                        id = "empty",
                        name = "Tidak Ada Saluran",
                        category = "Semua",
                        streamUrl = ""
                    )
                }
                ChannelRepository.saveChannels(context, updatedList)
                showDeleteConfirmDialog = false
            }
        )
    }

    // Dialog Tambah Saluran / URL Baru
    if (showAddDialog) {
        AddChannelDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { newChannel ->
                val updatedList = listOf(newChannel) + channelList
                channelList = updatedList
                selectedChannel = newChannel
                ChannelRepository.saveChannels(context, updatedList)
                showAddDialog = false
            }
        )
    }

    // Dialog Konfirmasi Reset ke Saluran Bawaan
    if (showResetConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showResetConfirmDialog = false },
            title = { Text("Reset Saluran") },
            text = { Text("Apakah Anda yakin ingin mengembalikan seluruh daftar saluran ke pengaturan awal?") },
            confirmButton = {
                Button(
                    onClick = {
                        val defaults = ChannelRepository.resetToDefaults(context)
                        channelList = defaults
                        selectedChannel = defaults.first()
                        selectedCategory = defaults.first().category
                        showResetConfirmDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = LiveRed)
                ) {
                    Text("Reset")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirmDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChannelCard(
    channel: Channel,
    isPlaying: Boolean,
    isOffline: Boolean = false,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .then(
                if (isPlaying) Modifier.border(2.dp, LiveRed, RoundedCornerShape(12.dp))
                else Modifier
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isPlaying) DarkSurfaceVariant else DarkSurface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo Channel
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                if (channel.logoUrl.isNotBlank()) {
                    AsyncImage(
                        model = channel.logoUrl,
                        contentDescription = channel.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(44.dp)
                            .padding(4.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.LiveTv,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Nama & Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = channel.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = channel.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isPlaying) LiveRed else Color.Gray
                )
            }

            // Indikator Aktif / Status
            if (isPlaying) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(LiveRed.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = LiveRed,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Diputar",
                        color = LiveRed,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else if (isOffline) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.Red.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Mati",
                        color = Color(0xFFFF6E6E),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ChannelActionDialog(
    channel: Channel,
    onDismiss: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = channel.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Pilih tindakan untuk saluran ini:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Tombol Edit
                OutlinedButton(
                    onClick = onEdit,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Edit Saluran")
                }
                // Tombol Hapus
                Button(
                    onClick = onDelete,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LiveRed)
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Hapus Saluran")
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}

@Composable
fun EditChannelDialog(
    channel: Channel,
    onDismiss: () -> Unit,
    onSave: (Channel) -> Unit
) {
    var name by remember { mutableStateOf(channel.name) }
    var streamUrl by remember { mutableStateOf(channel.streamUrl) }
    var category by remember { mutableStateOf(channel.category) }
    var logoUrl by remember { mutableStateOf(channel.logoUrl) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Saluran") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nama Saluran") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = streamUrl,
                    onValueChange = { streamUrl = it },
                    label = { Text("URL Streaming (.m3u8)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Kategori") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = logoUrl,
                    onValueChange = { logoUrl = it },
                    label = { Text("URL Logo (Opsional)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && streamUrl.isNotBlank()) {
                        onSave(
                            channel.copy(
                                name = name.trim(),
                                streamUrl = streamUrl.trim(),
                                category = category.trim().ifEmpty { "Kustom" },
                                logoUrl = logoUrl.trim()
                            )
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = LiveRed)
            ) {
                Text("Simpan Perubahan")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}

@Composable
fun DeleteConfirmDialog(
    channel: Channel,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Konfirmasi Hapus") },
        text = {
            Text("Apakah Anda yakin ingin menghapus saluran \"${channel.name}\"?")
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = LiveRed)
            ) {
                Text("Hapus")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}

@Composable
fun AddChannelDialog(
    onDismiss: () -> Unit,
    onAdd: (Channel) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var streamUrl by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Kustom") }
    var logoUrl by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Tambah Saluran Kustom") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nama Saluran") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = streamUrl,
                    onValueChange = { streamUrl = it },
                    label = { Text("URL Streaming (.m3u8)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Kategori") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = logoUrl,
                    onValueChange = { logoUrl = it },
                    label = { Text("URL Logo (Opsional)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && streamUrl.isNotBlank()) {
                        onAdd(
                            Channel(
                                id = "custom_${System.currentTimeMillis()}",
                                name = name.trim(),
                                category = category.trim().ifEmpty { "Kustom" },
                                logoUrl = logoUrl.trim(),
                                streamUrl = streamUrl.trim(),
                                description = "Saluran Kustom"
                            )
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = LiveRed)
            ) {
                Text("Simpan & Putar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}
