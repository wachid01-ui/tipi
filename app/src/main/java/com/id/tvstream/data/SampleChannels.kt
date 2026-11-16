package com.id.tvstream.data

import com.id.tvstream.model.Channel

object SampleChannels {
    /**
     * Fallback saluran cadangan jika pembacaan berkas assets gagal.
     * Berisi saluran utama Indonesia.
     */
    val fallbackChannels = listOf(
        Channel(
            id = "metro_tv",
            name = "Metro TV",
            category = "🇮🇩 Indonesia",
            logoUrl = "https://i.imgur.com/QnU70NI.png",
            streamUrl = "https://edge.medcom.id/live-edge/smil:metro.smil/playlist.m3u8",
            description = "Saluran berita & informasi 24 jam terpercaya"
        ),
        Channel(
            id = "cnn_indonesia",
            name = "CNN Indonesia",
            category = "🇮🇩 Indonesia",
            logoUrl = "https://i.imgur.com/l0JyOrm.png",
            streamUrl = "https://video.detik.com/livecnn/smil:cnntv/chunklist_w268557525_b384000_sleng.m3u8",
            description = "Berita terkini nasional & internasional dari CNN"
        ),
        Channel(
            id = "cnbc_indonesia",
            name = "CNBC Indonesia",
            category = "🇮🇩 Indonesia",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/CNBC_Indonesia_2025.svg/960px-CNBC_Indonesia_2025.svg.png",
            streamUrl = "https://video.detik.com/livecnbc/smil:cnbctv/chunklist_w1279679883_b384000_sleng.m3u8",
            description = "Saluran berita ekonomi, pasar modal & bisnis"
        ),
        Channel(
            id = "btv",
            name = "BTV",
            category = "🇮🇩 Indonesia",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/BTV_%28Indonesia%29_logo_%282025%29.svg/960px-BTV_%28Indonesia%29_logo_%282025%29.svg.png",
            streamUrl = "https://lnd0t3b922.tenbytecdn.com/ta-sg1/90369cf5-6ac2-411d-b3d0-15fae10a2e2c/master.m3u8",
            description = "BTV Bersatu Menginspirasi"
        ),
        Channel(
            id = "garuda_tv",
            name = "Garuda TV",
            category = "🇮🇩 Indonesia",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Garuda_TV_logo_2023.svg/960px-Garuda_TV_logo_2023.svg.png",
            streamUrl = "https://hgmtv.com:19360/garudatvlivestreaming/garudatvlivestreaming.m3u8",
            description = "Saluran televisi nasional informasi & budaya"
        ),
        Channel(
            id = "vtv",
            name = "VTV",
            category = "🇮🇩 Indonesia",
            logoUrl = "https://i.postimg.cc/cJRJr3BR/2560px-VTV-Indonesia-White-2023-svg.png",
            streamUrl = "https://flv.intechmedia.net/live/ch107.m3u8",
            description = "Saluran animasi anak & hiburan keluarga"
        ),
        Channel(
            id = "tvri_nasional",
            name = "TVRI Nasional",
            category = "🇮🇩 Indonesia",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/69/TVRI_logo_2019.svg/960px-TVRI_logo_2019.svg.png",
            streamUrl = "https://hls.klikstream.net/tvri/live.m3u8",
            description = "Televisi Republik Indonesia - Saluran Pemersatu Bangsa"
        )
    )

    // Untuk kompatibilitas backward
    val defaultChannels: List<Channel> get() = fallbackChannels

    /**
     * Helper sederhana untuk parse isi teks berkas M3U menjadi daftar objek Channel
     */
    fun parseM3U(content: String): List<Channel> {
        val channels = mutableListOf<Channel>()
        val lines = content.lines()
        var currentName = ""
        var currentLogo = ""
        var currentGroup = "🇮🇩 Indonesia"

        for (line in lines) {
            val trimmed = line.trim()
            if (trimmed.startsWith("#EXTINF:")) {
                val commaIndex = trimmed.lastIndexOf(',')
                currentName = if (commaIndex != -1 && commaIndex < trimmed.length - 1) {
                    trimmed.substring(commaIndex + 1).trim()
                } else {
                    "Channel ${channels.size + 1}"
                }

                val logoMatch = Regex("""tvg-logo="([^"]+)"""").find(trimmed)
                currentLogo = logoMatch?.groupValues?.get(1) ?: ""

                val groupMatch = Regex("""group-title="([^"]+)"""").find(trimmed)
                currentGroup = groupMatch?.groupValues?.get(1) ?: "🇮🇩 Indonesia"
            } else if (trimmed.isNotEmpty() && !trimmed.startsWith("#")) {
                if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
                    channels.add(
                        Channel(
                            id = "m3u_${channels.size + 1}",
                            name = currentName.ifEmpty { "Channel ${channels.size + 1}" },
                            category = currentGroup.ifEmpty { "🇮🇩 Indonesia" },
                            logoUrl = currentLogo,
                            streamUrl = trimmed,
                            description = "Impor M3U"
                        )
                    )
                    currentName = ""
                    currentLogo = ""
                    currentGroup = "🇮🇩 Indonesia"
                }
            }
        }
        return channels
    }
}
