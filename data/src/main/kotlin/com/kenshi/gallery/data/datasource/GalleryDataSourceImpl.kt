package com.kenshi.gallery.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kenshi.gallery.data.model.PhotoResponse
import com.kenshi.gallery.data.paging.GalleryPagingSource
import com.kenshi.gallery.data.service.GalleryService
import com.kenshi.gallery.data.util.Constants
import com.kenshi.gallery.data.util.FileUtil
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryDataSourceImpl @Inject constructor(
    private val service: GalleryService,
    private val fileUtil: FileUtil,
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

    override fun saveImageFile(fileName: String, byteArray: ByteArray): String {
        return fileUtil.saveImageFile(fileName, byteArray)
    }
}
