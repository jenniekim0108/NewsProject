package com.example.noncomposeapp.data.network

import com.example.noncomposeapp.data.response.Source
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines/")
    suspend fun getSourcesByCategory(
        @Query("category") category: String,
        @Query("api_key") apiKey: String = "625e6ad1238841fc937c5b9dbf5badbd"
    ): Response<Source>

    @GET("top-headlines/")
    suspend fun getArticlesBySources(
        @Query("source") source: String,
        @Query("api_key") apiKey: String = "625e6ad1238841fc937c5b9dbf5badbd"
    )
}