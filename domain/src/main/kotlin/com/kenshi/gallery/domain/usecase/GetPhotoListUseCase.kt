package com.kenshi.gallery.domain.usecase

import androidx.paging.PagingData
import com.kenshi.gallery.domain.entity.PhotoEntity
import com.kenshi.gallery.domain.repository.GalleryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhotoListUseCase @Inject constructor(
    private val repository: GalleryRepository,
) {
    operator fun invoke(): Flow<PagingData<PhotoEntity>> {
        return repository.getPhotoList()
    }
}
