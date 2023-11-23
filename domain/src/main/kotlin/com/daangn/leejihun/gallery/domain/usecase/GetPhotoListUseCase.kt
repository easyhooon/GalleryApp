package com.daangn.leejihun.gallery.domain.usecase

import androidx.paging.PagingData
import com.daangn.leejihun.gallery.domain.entity.PhotoEntity
import com.daangn.leejihun.gallery.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTrackListUseCase @Inject constructor(
    private val repository: GalleryRepository,
) {
    operator fun invoke(): Flow<PagingData<PhotoEntity>> {
        return repository.getPhotoList()
    }
}
