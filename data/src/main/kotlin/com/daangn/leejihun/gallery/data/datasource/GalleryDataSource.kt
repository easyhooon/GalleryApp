package com.daangn.leejihun.gallery.data.datasource

import androidx.paging.PagingData
import com.daangn.leejihun.gallery.data.model.PhotoResponse
import kotlinx.coroutines.flow.Flow

interface GalleryDataSource {
    fun getPhotoList(): Flow<PagingData<PhotoResponse>>
    fun saveImageFile(fileName: String, byteArray: ByteArray): String
}
