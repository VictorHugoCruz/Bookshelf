package com.devtor.bookshelf.network

import com.devtor.bookshelf.model.Response
import retrofit2.http.GET

interface BookshelfApiService{
    @GET("volumes?q=jazz+history")
    suspend fun getResponse(): Response
}