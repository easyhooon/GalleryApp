package com.daangn.leejihun.gallery.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    @SerialName("id")
    val id: String,

    @SerialName("author")
    val author: String,

    @SerialName("width")
    val width: Int,

    @SerialName("height")
    val height: Int,

    @SerialName("url")
    val url: String,

    @SerialName("download_url")
    val downloadUrl: String
)
