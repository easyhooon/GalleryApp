package com.kenshi.gallery.data.datasource

import androidx.paging.PagingData
import com.kenshi.gallery.data.model.PhotoResponse
import kotlinx.coroutines.flow.Flow

interface GalleryDataSource {
    fun getPhotoList(): Flow<PagingData<PhotoResponse>>
    fun saveImageFile(fileName: String, byteArray: ByteArray): String
}
