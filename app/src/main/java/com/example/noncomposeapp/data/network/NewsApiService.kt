package com.example.noncomposeapp.data.network

import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.data.response.ArticleResponse
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.data.response.SourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines/sources")
    suspend fun getCategoryFromSources(
        @Query("apiKey") apiKey: String = "625e6ad1238841fc937c5b9dbf5badbd"
    ): Response<SourceResponse>

    @GET("top-headlines/sources")
    suspend fun getSourcesByCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = "625e6ad1238841fc937c5b9dbf5badbd"
    ): Response<SourceResponse>

    @GET("top-headlines")
    suspend fun getArticlesBySource(
//        @Query("category") category: String,
        @Query("source") source: String,
        @Query("apiKey") apiKey: String = "625e6ad1238841fc937c5b9dbf5badbd"
    ): Response<ArticleResponse>

}