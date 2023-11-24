package com.daangn.leejihun.mapper

import com.daangn.leejihun.gallery.domain.entity.PhotoEntity
import com.daangn.leejihun.gallery.presentation.model.Photo

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
