package com.daangn.leejihun.gallery.data.mapper

import com.daangn.leejihun.gallery.data.model.PhotoResponse
import com.daangn.leejihun.gallery.domain.entity.PhotoEntity

internal fun PhotoResponse.toEntity(): PhotoEntity {
    return PhotoEntity(
        id = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
    )
}
