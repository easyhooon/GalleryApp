package com.daangn.leejihun.gallery.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.daangn.leejihun.gallery.data.datasource.GalleryDataSource
import com.daangn.leejihun.gallery.data.mapper.toEntity
import com.daangn.leejihun.gallery.domain.entity.PhotoEntity
import com.daangn.leejihun.gallery.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val dataSource: GalleryDataSource,
) : GalleryRepository {
    override fun getPhotoList(): Flow<PagingData<PhotoEntity>> {
        return dataSource.getPhotoList().map { pagingData ->
            pagingData.map { track ->
                track.toEntity()
            }
        }
    }
}
