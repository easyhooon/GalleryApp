package com.daangn.leejihun.gallery.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class PhotoEntity(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String
)
