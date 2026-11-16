package com.id.tvstream.ads

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.UnityAds

/**
 * Konfigurasi untuk Unity Ads.
 * Sesuaikan GAME_ID dan BANNER_PLACEMENT_ID dengan akun Unity Ads Anda.
 */
object UnityAdsConfig {
    /**
     * Unity Game ID Anda (didapatkan dari Unity Dashboard -> Project Settings -> Monetization).
     * Contoh format: "5732156" (Android).
     */
    const val GAME_ID = "5732156"

    /**
     * Placement ID / Ad Unit ID untuk Banner.
     * Biasanya bernilai "Banner_Android" atau "banner" di Unity Dashboard.
     */
    const val BANNER_PLACEMENT_ID = "Banner_Android"

    /**
     * Mode Pengujian (Test Mode).
     * Set ke `true` saat proses development/testing agar iklan test muncul.
     * Ubah ke `false` sebelum merilis aplikasi ke Google Play Store untuk mendapatkan pendapatan riil.
     */
    const val TEST_MODE = true
}

/**
 * Pengelola inisialisasi dan status Unity Ads SDK.
 */
object UnityAdsManager {
    private const val TAG = "UnityAdsManager"

    private val _isInitialized = mutableStateOf(false)
    val isInitialized: State<Boolean> = _isInitialized

    /**
     * Menginisialisasi Unity Ads SDK.
     * Biasanya dipanggil sekali saat aplikasi mulai di `MainActivity.onCreate`.
     */
    fun initialize(
        context: Context,
        gameId: String = UnityAdsConfig.GAME_ID,
        testMode: Boolean = UnityAdsConfig.TEST_MODE
    ) {
        if (UnityAds.isInitialized) {
            _isInitialized.value = true
            return
        }

        if (gameId.isBlank()) {
            Log.w(TAG, "Unity Game ID belum diisi. Inisialisasi iklan dilewati.")
            return
        }

        Log.d(TAG, "Memulai inisialisasi Unity Ads (Game ID: $gameId, Test Mode: $testMode)...")
        UnityAds.initialize(
            context.applicationContext,
            gameId,
            testMode,
            object : IUnityAdsInitializationListener {
                override fun onInitializationComplete() {
                    Log.d(TAG, "Unity Ads berhasil diinisialisasi.")
                    _isInitialized.value = true
                }

                override fun onInitializationFailed(
                    error: UnityAds.UnityAdsInitializationError?,
                    message: String?
                ) {
                    Log.e(TAG, "Inisialisasi Unity Ads gagal: $error - $message")
                    _isInitialized.value = false
                }
            }
        )
    }
}
