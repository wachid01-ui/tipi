package com.id.tvstream.data

import com.id.tvstream.model.Channel

object SampleChannels {
    val defaultChannels = listOf(
        Channel(
            id = "tvri_nasional",
            name = "TVRI Nasional",
            category = "Nasional",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Nasional/hls/Nasional.m3u8",
            description = "Saluran televisi publik nasional Indonesia"
        ),
        Channel(
            id = "tvri_world",
            name = "TVRI World",
            category = "Internasional",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/World/hls/World.m3u8",
            description = "Saluran berita & budaya internasional Indonesia"
        ),
        Channel(
            id = "tvri_sport",
            name = "TVRI Sport",
            category = "Olahraga",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Sport/hls/Sport.m3u8",
            description = "Siaran olahraga nasional dan internasional"
        ),
        Channel(
            id = "tvri_dki",
            name = "TVRI Jakarta",
            category = "Daerah",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/DKI/hls/DKI.m3u8",
            description = "Siaran lokal DKI Jakarta dan sekitarnya"
        ),
        Channel(
            id = "tvri_jabar",
            name = "TVRI Jawa Barat",
            category = "Daerah",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Jabar/hls/Jabar.m3u8",
            description = "Siaran lokal Jawa Barat"
        ),
        Channel(
            id = "tvri_jateng",
            name = "TVRI Jawa Tengah",
            category = "Daerah",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Jateng/hls/Jateng.m3u8",
            description = "Siaran lokal Jawa Tengah"
        ),
        Channel(
            id = "tvri_jatim",
            name = "TVRI Jawa Timur",
            category = "Daerah",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Jatim/hls/Jatim.m3u8",
            description = "Siaran lokal Jawa Timur"
        ),
        Channel(
            id = "tvri_jogja",
            name = "TVRI Yogyakarta",
            category = "Daerah",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Jogja/hls/Jogja.m3u8",
            description = "Siaran lokal D.I. Yogyakarta"
        ),
        Channel(
            id = "tvri_bali",
            name = "TVRI Bali",
            category = "Daerah",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Bali/hls/Bali.m3u8",
            description = "Siaran lokal Pulau Dewata Bali"
        ),
        Channel(
            id = "tvri_sulut",
            name = "TVRI Sulawesi Utara",
            category = "Daerah",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Sulut/hls/Sulut.m3u8",
            description = "Siaran lokal Sulawesi Utara"
        )
    )

    /**
     * Helper sederhana untuk parse isi teks berkas M3U menjadi daftar objek Channel
     */
    fun parseM3U(content: String): List<Channel> {
        val channels = mutableListOf<Channel>()
        val lines = content.lines()
        var currentName = ""
        var currentLogo = ""
        var currentGroup = "Umum"

        for (line in lines) {
            val trimmed = line.trim()
            if (trimmed.startsWith("#EXTINF:")) {
                // Ekstrak nama channel (setelah koma terakhir)
                val commaIndex = trimmed.lastIndexOf(',')
                currentName = if (commaIndex != -1 && commaIndex < trimmed.length - 1) {
                    trimmed.substring(commaIndex + 1).trim()
                } else {
                    "Channel ${channels.size + 1}"
                }

                // Ekstrak logo tvg-logo="..."
                val logoMatch = Regex("""tvg-logo="([^"]+)"""").find(trimmed)
                currentLogo = logoMatch?.groupValues?.get(1) ?: ""

                // Ekstrak group-title="..."
                val groupMatch = Regex("""group-title="([^"]+)"""").find(trimmed)
                currentGroup = groupMatch?.groupValues?.get(1) ?: "Umum"
            } else if (trimmed.isNotEmpty() && !trimmed.startsWith("#")) {
                if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
                    channels.add(
                        Channel(
                            id = "m3u_${channels.size + 1}",
                            name = currentName.ifEmpty { "Channel ${channels.size + 1}" },
                            category = currentGroup,
                            logoUrl = currentLogo,
                            streamUrl = trimmed,
                            description = "Impor M3U"
                        )
                    )
                    currentName = ""
                    currentLogo = ""
                    currentGroup = "Umum"
                }
            }
        }
        return channels
    }
}
