package com.id.tvstream.data

import com.id.tvstream.model.Channel

object SampleChannels {
    val defaultChannels = listOf(
        // === TV SWASTA NASIONAL ===
        Channel(
            id = "rcti_hd",
            name = "RCTI",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/iLFx8ia.png",
            streamUrl = "http://202.150.172.59/RCTIHD/index.m3u8",
            description = "Rajawali Citra Televisi Indonesia"
        ),
        Channel(
            id = "sctv",
            name = "SCTV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/EMmOvnQ.png",
            streamUrl = "http://202.150.172.59/SCTV/tracks-v1a1/mono.m3u8",
            description = "Surya Citra Televisi"
        ),
        Channel(
            id = "indosiar_hd",
            name = "Indosiar",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Indosiar_2015.svg/960px-Indosiar_2015.svg.png",
            streamUrl = "http://202.150.172.59/Indosiar/index.m3u8",
            description = "Saluran hiburan & drama nasional"
        ),
        Channel(
            id = "gtv_hd",
            name = "GTV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/jjuKnYs.png",
            streamUrl = "http://202.150.172.59/GTVHD/index.m3u8",
            description = "Global TV Indonesia"
        ),
        Channel(
            id = "metro_tv",
            name = "Metro TV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/QnU70NI.png",
            streamUrl = "https://edge.medcom.id/live-edge/smil:metro.smil/playlist.m3u8",
            description = "Saluran berita & informasi 24 jam"
        ),
        Channel(
            id = "btv",
            name = "BTV",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/BTV_%28Indonesia%29_logo_%282025%29.svg/960px-BTV_%28Indonesia%29_logo_%282025%29.svg.png",
            streamUrl = "https://lnd0t3b922.tenbytecdn.com/ta-sg1/90369cf5-6ac2-411d-b3d0-15fae10a2e2c/master.m3u8",
            description = "BTV Bersatu Menginspirasi"
        ),
        Channel(
            id = "magna_channel",
            name = "Magna Channel",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/tfJo0SO.png",
            streamUrl = "http://202.150.172.59/MagnaChannel/index.m3u8",
            description = "Saluran edukasi, gaya hidup & hiburan"
        ),
        Channel(
            id = "vtv_hd",
            name = "VTV",
            category = "TVLokal",
            logoUrl = "https://i.postimg.cc/cJRJr3BR/2560px-VTV-Indonesia-White-2023-svg.png",
            streamUrl = "http://202.150.172.59/VTVHD/index.m3u8",
            description = "Saluran animasi anak & hiburan keluarga"
        ),
        Channel(
            id = "daai_tv",
            name = "DAAI TV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/YC7JCHo.png",
            streamUrl = "https://pull.daaiplus.com/live-DAAIPLUS/live-DAAIPLUS_HD.m3u8",
            description = "Televisi cinta kasih & kemanusiaan"
        ),

        // === TV PUBLIK & DAERAH ===
        Channel(
            id = "tvri_nasional",
            name = "TVRI Nasional",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Nasional/hls/Nasional.m3u8",
            description = "Saluran televisi publik nasional Indonesia"
        ),
        Channel(
            id = "tvri_sport",
            name = "TVRI Sport",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Sport/hls/Sport.m3u8",
            description = "Siaran olahraga nasional dan internasional"
        ),
        Channel(
            id = "tvri_world",
            name = "TVRI World",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/World/hls/World.m3u8",
            description = "Saluran berita & budaya internasional Indonesia"
        ),
        Channel(
            id = "tvri_dki",
            name = "TVRI Jakarta",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/DKI/hls/DKI.m3u8",
            description = "Siaran lokal DKI Jakarta dan sekitarnya"
        ),
        Channel(
            id = "tvri_jabar",
            name = "TVRI Jawa Barat",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Jabar/hls/Jabar.m3u8",
            description = "Siaran lokal Jawa Barat"
        ),
        Channel(
            id = "tvri_jateng",
            name = "TVRI Jawa Tengah",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Jateng/hls/Jateng.m3u8",
            description = "Siaran lokal Jawa Tengah"
        ),
        Channel(
            id = "tvri_jatim",
            name = "TVRI Jawa Timur",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Jatim/hls/Jatim.m3u8",
            description = "Siaran lokal Jawa Timur"
        ),
        Channel(
            id = "tvri_jogja",
            name = "TVRI Yogyakarta",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Jogja/hls/Jogja.m3u8",
            description = "Siaran lokal D.I. Yogyakarta"
        ),
        Channel(
            id = "tvri_bali",
            name = "TVRI Bali",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Bali/hls/Bali.m3u8",
            description = "Siaran lokal Pulau Dewata Bali"
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
        var currentGroup = "TVLokal"

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
                currentGroup = groupMatch?.groupValues?.get(1) ?: "TVLokal"
            } else if (trimmed.isNotEmpty() && !trimmed.startsWith("#")) {
                if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
                    channels.add(
                        Channel(
                            id = "m3u_${channels.size + 1}",
                            name = currentName.ifEmpty { "Channel ${channels.size + 1}" },
                            category = currentGroup.ifEmpty { "TVLokal" },
                            logoUrl = currentLogo,
                            streamUrl = trimmed,
                            description = "Impor M3U"
                        )
                    )
                    currentName = ""
                    currentLogo = ""
                    currentGroup = "TVLokal"
                }
            }
        }
        return channels
    }
}
