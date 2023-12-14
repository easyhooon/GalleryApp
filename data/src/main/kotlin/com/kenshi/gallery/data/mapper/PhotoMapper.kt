package com.kenshi.gallery.data.mapper

import com.kenshi.gallery.data.model.PhotoResponse
import com.kenshi.gallery.domain.entity.PhotoEntity

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
