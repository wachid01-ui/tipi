package com.id.tvstream.model

import kotlinx.serialization.Serializable

@Serializable
data class Channel(
    val id: String,
    val name: String,
    val category: String,
    val logoUrl: String = "",
    val streamUrl: String,
    val description: String = ""
)
