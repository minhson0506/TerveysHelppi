package com.example.terveyshelppi.service.youTubeService

import com.example.terveyshelppi.BuildConfig.API_KEY
import retrofit2.Call
import retrofit2.http.*

interface SearchApiService {
    @GET("search/")
    fun search(
        @Query("q") searchString: String,
        @Query("key") apiKey: String = API_KEY,
    ): Call<SearchResponse>
}

interface SearchTitleApiService {
    @GET
    fun searchTitle(
        @Url url: String,
    ): Call<TitleResponse>
}