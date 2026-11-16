package com.id.tvstream.ads

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.id.tvstream.ui.theme.DarkBackground
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

    // Jika Unity Ads belum siap atau Activity tidak ditemukan, jangan render
    if (!isInitialized || activity == null) {
        return
    }

    // Jangan tampilkan iklan jika sedang berada dalam mode Picture-in-Picture (PiP)
    val isInPiP = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        activity.isInPictureInPictureMode
    } else {
        false
    }
    if (isInPiP) {
        return
    }

    var isBannerLoaded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(DarkBackground)
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.wrapContentSize(),
            factory = {
                BannerView(activity, placementId, UnityBannerSize(320, 50)).apply {
                    listener = object : BannerView.Listener() {
                        override fun onBannerLoaded(bannerAdView: BannerView?) {
                            super.onBannerLoaded(bannerAdView)
                            Log.d("UnityBannerAd", "Banner berhasil dimuat: $placementId")
                            isBannerLoaded = true
                        }

                        override fun onBannerFailedToLoad(
                            bannerAdView: BannerView?,
                            errorInfo: BannerErrorInfo?
                        ) {
                            super.onBannerFailedToLoad(bannerAdView, errorInfo)
                            Log.e(
                                "UnityBannerAd",
                                "Banner gagal dimuat: ${errorInfo?.errorMessage} (${errorInfo?.errorCode})"
                            )
                            isBannerLoaded = false
                        }

                        override fun onBannerClick(bannerAdView: BannerView?) {
                            super.onBannerClick(bannerAdView)
                            Log.d("UnityBannerAd", "Banner diklik oleh pengguna: $placementId")
                        }

                        override fun onBannerLeftApplication(bannerAdView: BannerView?) {
                            super.onBannerLeftApplication(bannerAdView)
                            Log.d("UnityBannerAd", "Pengguna berpindah aplikasi melalui banner")
                        }
                    }
                    load()
                }
            },
            onRelease = { bannerView ->
                Log.d("UnityBannerAd", "Menghapus instance BannerView")
                bannerView.destroy()
            }
        )
    }
}
