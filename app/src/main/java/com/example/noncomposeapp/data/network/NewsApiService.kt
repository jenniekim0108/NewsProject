package com.example.noncomposeapp.data.network

import com.example.noncomposeapp.data.response.ArticleResponse
import com.example.noncomposeapp.data.response.SourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private val api: String = "625e6ad1238841fc937c5b9dbf5badbd"

interface NewsApiService {

    @GET("top-headlines/sources")
    suspend fun getCategoryFromSources(
        @Query("apiKey") apiKey: String = api
    ): Response<SourceResponse>

    @GET("top-headlines/sources")
    suspend fun getSourcesByCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = api
    ): Response<SourceResponse>

    @GET("top-headlines")
    suspend fun getArticlesBySource(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String = api
    ): Response<ArticleResponse>

    @GET("everything")
    suspend fun getSearchedSourcesData(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = api
    ): Response<SourceResponse>

    @GET("everything")
    suspend fun getSearchedArticlesData(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = api
    ): Response<ArticleResponse>
}