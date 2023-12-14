package com.kenshi.gallery.domain.repository

import androidx.paging.PagingData
import com.kenshi.gallery.domain.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun getPhotoList(): Flow<PagingData<PhotoEntity>>
    fun saveImageFile(fileName: String, byteArray: ByteArray): String
}
