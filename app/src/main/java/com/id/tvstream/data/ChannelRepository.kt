package com.id.tvstream.data

import android.content.Context
import android.util.Log
import com.id.tvstream.model.Channel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object ChannelRepository {
    private const val TAG = "ChannelRepository"
    private const val USER_CHANNELS_FILE = "user_channels.json"
    private const val ASSETS_CHANNELS_FILE = "channels.json"
    private const val OLD_PREFS_NAME = "tvstream_prefs"
    private const val OLD_KEY_CHANNELS = "saved_channels"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    @Volatile
    private var cachedChannels: List<Channel>? = null

    /**
     * Memuat daftar saluran televisi.
     * Prioritas:
     * 1. Cache di memori
     * 2. Berkas kustom pengguna (user_channels.json di filesDir) jika ada
     * 3. Berkas bawaan dari assets/channels.json (13.000+ saluran seluruh dunia)
     * 4. Fallback jika berkas tidak dapat diakses
     */
    fun loadChannels(context: Context): List<Channel> {
        cachedChannels?.let { return it }

        // Bersihkan cache lama di SharedPreferences agar saluran seluruh dunia langsung aktif
        cleanOldPreferences(context)

        // 1. Cek penyimpanan lokal internal (jika pengguna pernah edit/hapus/tambah)
        val userFile = File(context.filesDir, USER_CHANNELS_FILE)
        if (userFile.exists()) {
            try {
                val content = userFile.readText()
                val list = json.decodeFromString<List<Channel>>(content)
                if (list.isNotEmpty()) {
                    cachedChannels = list
                    return list
                }
            } catch (e: Exception) {
                Log.e(TAG, "Gagal membaca user_channels.json: ${e.message}")
            }
        }

        // 2. Muat dari berkas assets/channels.json bawaan aplikasi
        val defaultList = loadFromAssets(context)
        cachedChannels = defaultList
        return defaultList
    }

    /**
     * Menyimpan perubahan daftar saluran (saat pengguna menambah, mengedit, atau menghapus saluran).
     */
    fun saveChannels(context: Context, channels: List<Channel>) {
        cachedChannels = channels
        try {
            val userFile = File(context.filesDir, USER_CHANNELS_FILE)
            userFile.writeText(json.encodeToString(channels))
        } catch (e: Exception) {
            Log.e(TAG, "Gagal menyimpan saluran ke user_channels.json: ${e.message}")
        }
    }

    /**
     * Mengembalikan daftar saluran ke bawaan seluruh dunia dari assets/channels.json.
     */
    fun resetToDefaults(context: Context): List<Channel> {
        try {
            val userFile = File(context.filesDir, USER_CHANNELS_FILE)
            if (userFile.exists()) {
                userFile.delete()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Gagal menghapus user_channels.json: ${e.message}")
        }
        val defaultList = loadFromAssets(context)
        cachedChannels = defaultList
        return defaultList
    }

    private fun loadFromAssets(context: Context): List<Channel> {
        return try {
            context.assets.open(ASSETS_CHANNELS_FILE).bufferedReader().use { reader ->
                val content = reader.readText()
                json.decodeFromString<List<Channel>>(content)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Gagal memuat assets/$ASSETS_CHANNELS_FILE: ${e.message}")
            SampleChannels.fallbackChannels
        }
    }

    private fun cleanOldPreferences(context: Context) {
        try {
            val prefs = context.getSharedPreferences(OLD_PREFS_NAME, Context.MODE_PRIVATE)
            if (prefs.contains(OLD_KEY_CHANNELS)) {
                prefs.edit().remove(OLD_KEY_CHANNELS).apply()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Gagal membersihkan SharedPreferences lama: ${e.message}")
        }
    }
}
