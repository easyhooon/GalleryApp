package com.kenshi.gallery.presentation.mapper

import com.kenshi.gallery.domain.entity.PhotoEntity
import com.kenshi.gallery.presentation.model.Photo

internal fun PhotoEntity.toUiModel(): Photo {
    return Photo(
        id = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl,
    )
}
