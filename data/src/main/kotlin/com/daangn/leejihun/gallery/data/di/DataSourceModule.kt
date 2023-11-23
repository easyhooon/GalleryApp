package com.daangn.leejihun.gallery.data.di

import com.daangn.leejihun.gallery.data.datasource.GalleryDataSource
import com.daangn.leejihun.gallery.data.datasource.GalleryDataSourceImpl
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
