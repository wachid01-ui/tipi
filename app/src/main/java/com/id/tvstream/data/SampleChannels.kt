package com.id.tvstream.data

import com.id.tvstream.model.Channel

object SampleChannels {
    val defaultChannels = listOf(
        // ==========================================
        // KATEGORI: TVLOKAL (100% TERVERIFIKASI 200 OK)
        // ==========================================

        // --- Berita & Swasta Nasional ---
        Channel(
            id = "metro_tv",
            name = "Metro TV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/QnU70NI.png",
            streamUrl = "https://edge.medcom.id/live-edge/smil:metro.smil/playlist.m3u8",
            description = "Saluran berita & informasi 24 jam terpercaya"
        ),
        Channel(
            id = "cnn_indonesia",
            name = "CNN Indonesia",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/l0JyOrm.png",
            streamUrl = "https://video.detik.com/livecnn/smil:cnntv/chunklist_w268557525_b384000_sleng.m3u8",
            description = "Berita terkini nasional & internasional dari CNN"
        ),
        Channel(
            id = "cnbc_indonesia",
            name = "CNBC Indonesia",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/CNBC_Indonesia_2025.svg/960px-CNBC_Indonesia_2025.svg.png",
            streamUrl = "https://video.detik.com/livecnbc/smil:cnbctv/chunklist_w1279679883_b384000_sleng.m3u8",
            description = "Saluran berita ekonomi, pasar modal & bisnis"
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
            id = "garuda_tv",
            name = "Garuda TV",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Garuda_TV_logo_2023.svg/960px-Garuda_TV_logo_2023.svg.png",
            streamUrl = "https://hgmtv.com:19360/garudatvlivestreaming/garudatvlivestreaming.m3u8",
            description = "Saluran televisi nasional informasi & budaya"
        ),
        Channel(
            id = "vtv",
            name = "VTV",
            category = "TVLokal",
            logoUrl = "https://i.postimg.cc/cJRJr3BR/2560px-VTV-Indonesia-White-2023-svg.png",
            streamUrl = "https://flv.intechmedia.net/live/ch107.m3u8",
            description = "Saluran animasi anak & hiburan keluarga"
        ),
        Channel(
            id = "jawapos_tv",
            name = "Jawa Pos TV",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Jawa_Pos_TV_logo_2021.svg/960px-Jawa_Pos_TV_logo_2021.svg.png",
            streamUrl = "http://122.248.43.242:1935/JAWAPOSTVJKT/_definst_/myStream/playlist.m3u8",
            description = "Saluran berita & informasi Jawa Pos Group"
        ),
        Channel(
            id = "jtv_surabaya",
            name = "JTV Surabaya",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/JTV_Logo_2013.svg/960px-JTV_Logo_2013.svg.png",
            streamUrl = "http://122.248.43.242:1935/JTVSURABAYA/_definst_/myStream/playlist.m3u8",
            description = "Jawa Timur Televisi - Rekk!"
        ),
        Channel(
            id = "daai_tv",
            name = "DAAI TV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/YC7JCHo.png",
            streamUrl = "https://pull.daaiplus.com/live-DAAIPLUS/live-DAAIPLUS_HD.m3u8",
            description = "Televisi cinta kasih & kemanusiaan"
        ),

        // --- Religi & Budaya ---
        Channel(
            id = "tv9_nusantara",
            name = "TV 9 Nusantara",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/TV9_Nusantara_logo_2018.svg/960px-TV9_Nusantara_logo_2018.svg.png",
            streamUrl = "https://5bf7b725107e5.streamlock.net/tv9/tv9/playlist.m3u8",
            description = "Saluran dakwah Islam ramah santun & santri"
        ),
        Channel(
            id = "rodja_tv",
            name = "Rodja TV",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Rodja_TV.png/960px-Rodja_TV.png",
            streamUrl = "https://rodjatv.com/rodjatv/live.m3u8",
            description = "Saluran dakwah Islam Ahlus Sunnah wal Jamaah"
        ),
        Channel(
            id = "mqtv_bandung",
            name = "MQTV Bandung",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/MQTV.png/960px-MQTV.png",
            streamUrl = "https://5bf7b725107e5.streamlock.net/mqtv/mqtv/playlist.m3u8",
            description = "Manajemen Qolbu TV Bandung - Inspirasi Keluarga"
        ),
        Channel(
            id = "indonesiana_tv",
            name = "Indonesiana.TV",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/Indonesiana_TV_logo.svg/960px-Indonesiana_TV_logo.svg.png",
            streamUrl = "https://tvstreamcast.com/indonesiana.m3u8",
            description = "Saluran seni & budaya Kementerian Kebudayaan RI"
        ),

        // --- Televisi Daerah Seluruh Indonesia ---
        Channel(
            id = "bandung_tv",
            name = "Bandung TV",
            category = "TVLokal",
            logoUrl = "https://i.postimg.cc/JzqSzcj9/200px-Logo-bdg-tv-2016.png",
            streamUrl = "http://202.150.153.254:65500/bandungtvWEBSITE.m3u8",
            description = "Saluran televisi daerah Kota Bandung & sekitarnya"
        ),
        Channel(
            id = "padang_tv",
            name = "Padang TV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/EL2BfxY.png",
            streamUrl = "http://122.248.43.242:1935/PADANGTV/_definst_/myStream/playlist.m3u8",
            description = "Saluran televisi daerah Sumatera Barat"
        ),
        Channel(
            id = "batam_tv",
            name = "Batam TV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/lo7YXnW.png",
            streamUrl = "http://122.248.43.242:1935/BATAMTV/_definst_/myStream/playlist.m3u8",
            description = "Saluran televisi daerah Kepulauan Riau & Batam"
        ),
        Channel(
            id = "jambi_tv",
            name = "Jambi TV",
            category = "TVLokal",
            logoUrl = "https://static.wikia.nocookie.net/logopedia/images/1/15/Jambi_TV.png",
            streamUrl = "https://jambitv.globaldigitalcore.com/hls/sah5tu7Eiveepohyieyie3OoteL2anga.m3u8",
            description = "Saluran televisi daerah Provinsi Jambi"
        ),
        Channel(
            id = "riau_tv",
            name = "Riau TV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/4a0LpDq.png",
            streamUrl = "http://122.248.43.242:1935/RIAUTV/_definst_/myStream/playlist.m3u8",
            description = "Saluran televisi daerah Provinsi Riau"
        ),
        Channel(
            id = "balikpapan_tv",
            name = "Balikpapan TV",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/P3whqna.png",
            streamUrl = "http://122.248.43.242:1935/BALIKPAPANTV/_definst_/myStream/playlist.m3u8",
            description = "Saluran televisi daerah Kalimantan Timur"
        ),
        Channel(
            id = "suara_surabaya",
            name = "Suara Surabaya Visual",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/id/f/f8/Suara_Surabaya.svg",
            streamUrl = "https://ssfm.siar.us/ssfm/live/playlist.m3u8",
            description = "Visual Radio & Live Streaming Suara Surabaya"
        ),
        Channel(
            id = "indonesia_channel",
            name = "The Indonesia Channel",
            category = "TVLokal",
            logoUrl = "https://i.imgur.com/dZdUbYd.png",
            streamUrl = "https://restreamer.indonusa.id:8443/d5f4ad43-1b5a-4b0f-8bef-5b37224ed221.m3u8",
            description = "Indonesian international English TV channel"
        ),

        // --- Jaringan TVRI Resmi Indonesia ---
        Channel(
            id = "tvri_nasional",
            name = "TVRI Nasional",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Nasional/hls/Nasional.m3u8",
            description = "Saluran televisi publik nasional Indonesia"
        ),
        Channel(
            id = "tvri_world",
            name = "TVRI World",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/TVRIWorld/hls/TVRIWorld.m3u8",
            description = "Saluran berita & diplomasi internasional Indonesia"
        ),
        Channel(
            id = "tvri_dki",
            name = "TVRI Jakarta",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/DKI/hls/DKI.m3u8",
            description = "Siaran lokal DKI Jakarta & Jabodetabek"
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
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Jogjakarta/hls/Jogjakarta.m3u8",
            description = "Siaran lokal D.I. Yogyakarta"
        ),
        Channel(
            id = "tvri_bali",
            name = "TVRI Bali",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Bali/hls/Bali.m3u8",
            description = "Siaran lokal Pulau Dewata Bali"
        ),
        Channel(
            id = "tvri_sumut",
            name = "TVRI Sumatera Utara",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Sumut/hls/Sumut.m3u8",
            description = "Siaran lokal Sumatera Utara"
        ),
        Channel(
            id = "tvri_sulsel",
            name = "TVRI Sulawesi Selatan",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Sulsel/hls/Sulsel.m3u8",
            description = "Siaran lokal Sulawesi Selatan"
        ),
        Channel(
            id = "tvri_sulut",
            name = "TVRI Sulawesi Utara",
            category = "TVLokal",
            logoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/TVRI_logo_2019.svg/512px-TVRI_logo_2019.svg.png",
            streamUrl = "https://ott-balancer.tvri.go.id/live/eds/Sulut/hls/Sulut.m3u8",
            description = "Siaran lokal Sulawesi Utara"
        ),

        // ==========================================
        // KATEGORI: UFC & COMBAT SPORTS (200 OK)
        // ==========================================
        Channel(
            id = "dazn_combat",
            name = "DAZN Combat (UFC / MMA)",
            category = "UFC",
            logoUrl = "https://i.postimg.cc/VsW3Jsrz/logo-DAZN-Combat.png",
            streamUrl = "https://jmp2.uk/plu-64d626ac9b414d000820e2fc.m3u8",
            description = "Siaran langsung pertarungan MMA, UFC Highlights & Tinju"
        ),
        Channel(
            id = "swerve_combat",
            name = "Swerve Combat",
            category = "UFC",
            logoUrl = "https://i.imgur.com/eFZh7aG.png",
            streamUrl = "https://linear-253.frequency.stream/mt/roku/253/hls/master/playlist.m3u8",
            description = "Saluran pertarungan laga MMA, Kickboxing & Combat Sports"
        ),
        Channel(
            id = "mma_tv",
            name = "MMA-TV",
            category = "UFC",
            logoUrl = "https://i.imgur.com/QhdxNsB.png",
            streamUrl = "https://streams2.sofast.tv/vglive-sk-462904/playlist.m3u8",
            description = "Pertarungan Mixed Martial Arts 24/7"
        ),
        Channel(
            id = "persiana_fight",
            name = "Persiana Fight",
            category = "UFC",
            logoUrl = "https://i.imgur.com/mRsnztA.png",
            streamUrl = "https://fighthls.persiana.live/hls/stream.m3u8",
            description = "Siaran laga bela diri, MMA, dan Muay Thai dunia"
        ),
        Channel(
            id = "tvs_boxing",
            name = "TVS Boxing & Combat",
            category = "UFC",
            logoUrl = "https://i.imgur.com/30ZoF75.png",
            streamUrl = "https://rpn.bozztv.com/gusa/gusa-tvsboxing/index.m3u8",
            description = "Kejuaraan tinju internasional dan pertarungan pro"
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
