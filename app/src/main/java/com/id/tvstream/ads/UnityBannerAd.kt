package com.id.tvstream.ads

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.id.tvstream.ui.theme.DarkBackground
import com.id.tvstream.ui.theme.DarkSurface
import com.unity3d.services.banners.BannerErrorInfo
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize

/**
 * Ekstensi helper untuk mendapatkan instance [Activity] dari [Context].
 */
fun Context.findActivity(): Activity? {
    var ctx = this
    while (ctx is ContextWrapper) {
        if (ctx is Activity) return ctx
        ctx = ctx.baseContext
    }
    return null
}

/**
 * Komponen Jetpack Compose untuk menampilkan Unity Banner Ad.
 *
 * @param modifier Modifier tambahan untuk kontainer iklan.
 * @param placementId ID Penempatan Banner dari Unity Dashboard.
 */
@Composable
fun UnityBannerAd(
    modifier: Modifier = Modifier,
    placementId: String = UnityAdsConfig.BANNER_PLACEMENT_ID
) {
    val context = LocalContext.current
    val activity = remember(context) { context.findActivity() }
    val isInitialized by UnityAdsManager.isInitialized
    val initError by UnityAdsManager.errorMessage

    // Jangan tampilkan jika dalam mode Picture-in-Picture
    val isInPiP = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        activity?.isInPictureInPictureMode == true
    } else {
        false
    }
    if (isInPiP || activity == null) {
        return
    }

    var isBannerLoaded by remember { mutableStateOf(false) }
    var bannerError by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isInitialized) {
            // Container berukuran pasti 320x50 untuk Banner Unity
            Box(
                modifier = Modifier
                    .size(width = 320.dp, height = 50.dp)
                    .background(DarkBackground),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(
                    modifier = Modifier.size(width = 320.dp, height = 50.dp),
                    factory = {
                        val bannerView = BannerView(activity, placementId, UnityBannerSize(320, 50))
                        bannerView.layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        bannerView.listener = object : BannerView.Listener() {
                            override fun onBannerLoaded(bannerAdView: BannerView?) {
                                super.onBannerLoaded(bannerAdView)
                                Log.d("UnityBannerAd", "Banner berhasil dimuat: $placementId")
                                isBannerLoaded = true
                                bannerError = null
                            }

                            override fun onBannerFailedToLoad(
                                bannerAdView: BannerView?,
                                errorInfo: BannerErrorInfo?
                            ) {
                                super.onBannerFailedToLoad(bannerAdView, errorInfo)
                                val msg = "${errorInfo?.errorMessage} (${errorInfo?.errorCode})"
                                Log.e("UnityBannerAd", "Banner gagal dimuat: $msg")
                                isBannerLoaded = false
                                bannerError = msg
                            }

                            override fun onBannerClick(bannerAdView: BannerView?) {
                                super.onBannerClick(bannerAdView)
                                Log.d("UnityBannerAd", "Banner diklik: $placementId")
                            }

                            override fun onBannerLeftApplication(bannerAdView: BannerView?) {
                                super.onBannerLeftApplication(bannerAdView)
                                Log.d("UnityBannerAd", "Pengguna berpindah aplikasi via banner")
                            }
                        }
                        bannerView.load()
                        bannerView
                    },
                    onRelease = { bannerView ->
                        Log.d("UnityBannerAd", "Menghapus instance BannerView")
                        bannerView.destroy()
                    }
                )

                // Jika dalam mode TEST dan banner belum muncul / ada error, tampilkan status informatif
                if (UnityAdsConfig.TEST_MODE && !isBannerLoaded) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkSurface, RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Unity Ads (Test Mode)",
                                color = Color.Yellow,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = bannerError ?: "Sedang memuat banner iklan...",
                                color = Color.White,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        } else if (UnityAdsConfig.TEST_MODE) {
            // Belum terinisialisasi dalam TEST MODE: tampilkan status inisialisasi / error Game ID
            Box(
                modifier = Modifier
                    .size(width = 320.dp, height = 50.dp)
                    .background(DarkSurface, RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFFE57373), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Unity Ads Belum Aktif",
                        color = Color(0xFFE57373),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = initError ?: "Masukkan Game ID asli di UnityAdsConfig.kt",
                        color = Color.LightGray,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
