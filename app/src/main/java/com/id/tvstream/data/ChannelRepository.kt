package com.id.tvstream.data

import android.content.Context
import com.id.tvstream.model.Channel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ChannelRepository {
    private const val PREFS_NAME = "tvstream_prefs"
    private const val KEY_CHANNELS = "saved_channels"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    fun loadChannels(context: Context): List<Channel> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val jsonString = prefs.getString(KEY_CHANNELS, null) ?: return SampleChannels.defaultChannels
        return try {
            val list = json.decodeFromString<List<Channel>>(jsonString)
            if (list.isNotEmpty()) list else SampleChannels.defaultChannels
        } catch (e: Exception) {
            SampleChannels.defaultChannels
        }
    }

    fun saveChannels(context: Context, channels: List<Channel>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        try {
            val jsonString = json.encodeToString(channels)
            prefs.edit().putString(KEY_CHANNELS, jsonString).apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun resetToDefaults(context: Context): List<Channel> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_CHANNELS).apply()
        return SampleChannels.defaultChannels
    }
}
