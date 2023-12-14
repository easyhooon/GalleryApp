package com.kenshi.gallery.data.di

import com.kenshi.gallery.data.datasource.GalleryDataSource
import com.kenshi.gallery.data.datasource.GalleryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindGalleryDataSource(galleryDataSourceImpl: GalleryDataSourceImpl): GalleryDataSource
}
