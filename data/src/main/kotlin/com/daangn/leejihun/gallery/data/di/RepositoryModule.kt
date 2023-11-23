package com.daangn.leejihun.gallery.data.di

import com.daangn.leejihun.gallery.data.repository.GalleryRepositoryImpl
import com.daangn.leejihun.gallery.domain.repository.GalleryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGalleryRepository(galleryRepositoryImpl: GalleryRepositoryImpl): GalleryRepository
}
