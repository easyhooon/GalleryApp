package com.kenshi.gallery.data.di

import com.kenshi.gallery.data.service.GalleryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Singleton
    @Provides
    internal fun provideGalleryService(retrofit: Retrofit): GalleryService {
        return retrofit.create(GalleryService::class.java)
    }
}
