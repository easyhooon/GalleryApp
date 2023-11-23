package com.daangn.leejihun.gallery.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.daangn.leejihun.gallery.data.model.PhotoResponse
import com.daangn.leejihun.gallery.data.paging.GalleryPagingSource
import com.daangn.leejihun.gallery.data.service.GalleryService
import com.daangn.leejihun.gallery.data.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryDataSourceImpl @Inject constructor(
    private val service: GalleryService,
) : GalleryDataSource {
    override fun getPhotoList(): Flow<PagingData<PhotoResponse>> {
        val pagingSourceFactory = {
            GalleryPagingSource(service)
        }
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGING_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }
}
