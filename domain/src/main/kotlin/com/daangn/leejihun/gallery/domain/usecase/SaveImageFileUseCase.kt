package com.daangn.leejihun.gallery.domain.usecase

import com.daangn.leejihun.gallery.domain.repository.GalleryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveImageFileUseCase @Inject constructor(
    private val repository: GalleryRepository,
) {
    operator fun invoke(fileName: String, byteArray: ByteArray): String {
        return repository.saveImageFile(fileName, byteArray)
    }
}
