package com.kenshi.gallery.data.service

import com.kenshi.gallery.data.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryService {
    @GET("list")
    suspend fun getPhotoList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<PhotoResponse>
}
