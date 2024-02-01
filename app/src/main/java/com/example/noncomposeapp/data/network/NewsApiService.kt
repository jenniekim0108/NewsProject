package com.example.noncomposeapp.data.network

import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.data.response.ArticleResponse
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.data.response.SourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private val api: String = "4888af1168554a48abf1e2a075e8ef00"
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
        @Query("category") category: String,
        @Query("source") source: String,
        @Query("apiKey") apiKey: String = api
    ): Response<ArticleResponse>



}