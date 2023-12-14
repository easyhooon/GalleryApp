package com.kenshi.gallery.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.kenshi.gallery.data.datasource.GalleryDataSource
import com.kenshi.gallery.data.mapper.toEntity
import com.kenshi.gallery.domain.entity.PhotoEntity
import com.kenshi.gallery.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val dataSource: GalleryDataSource,
) : GalleryRepository {
    override fun getPhotoList(): Flow<PagingData<PhotoEntity>> {
        return dataSource.getPhotoList().map { pagingData ->
            pagingData.map { photo ->
                photo.toEntity()
            }
        }
    }

    override fun saveImageFile(fileName: String, byteArray: ByteArray): String {
        return dataSource.saveImageFile(fileName, byteArray)
    }
}
