package com.id.tvstream

import android.app.PictureInPictureParams
import android.app.UiModeManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.id.tvstream.ads.UnityAdsManager
import com.id.tvstream.ui.HomeScreen
import com.id.tvstream.ui.theme.TVStreamTheme

class MainActivity : ComponentActivity() {

    private var isFullscreenState by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Jika dijalankan di Android TV, kunci ke orientasi landscape
        if (isTvMode()) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // Inisialisasi Unity Ads SDK
        UnityAdsManager.initialize(this)

        setContent {
            TVStreamTheme {
                // Tangani tombol kembali HP: jika sedang layar penuh, kembalikan ke portrait
                BackHandler(enabled = isFullscreenState) {
                    toggleFullscreen(false)
                }

                HomeScreen(
                    isFullscreen = isFullscreenState,
                    onToggleFullscreen = {
                        toggleFullscreen(!isFullscreenState)
                    },
                    onEnterPiP = {
                        enterPipMode()
                    }
                )
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        isFullscreenState = (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        applySystemBarVisibility(isFullscreenState)
    }

    private fun isTvMode(): Boolean {
        val uiModeManager = getSystemService(Context.UI_MODE_SERVICE) as? UiModeManager
        return uiModeManager?.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION ||
                !packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)
    }

    private fun toggleFullscreen(enable: Boolean) {
        isFullscreenState = enable
        if (!isTvMode()) {
            requestedOrientation = if (enable) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
        applySystemBarVisibility(enable)
    }

    private fun applySystemBarVisibility(fullscreen: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                if (fullscreen) {
                    controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                    controller.systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                } else {
                    controller.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                }
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = if (fullscreen) {
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            } else {
                View.SYSTEM_UI_FLAG_VISIBLE
            }
        }
    }

    private fun enterPipMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val params = PictureInPictureParams.Builder()
                .setAspectRatio(Rational(16, 9))
                .build()
            enterPictureInPictureMode(params)
        }
    }
}
