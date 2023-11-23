package com.daangn.leejihun.gallery.domain.repository

import androidx.paging.PagingData
import com.daangn.leejihun.gallery.domain.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun getPhotoList(): Flow<PagingData<PhotoEntity>>
}
