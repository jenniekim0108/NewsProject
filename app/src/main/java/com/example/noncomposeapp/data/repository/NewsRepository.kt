package com.example.noncomposeapp.data.repository

import android.util.Log
import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.ArticleResponse
import com.example.noncomposeapp.data.response.SourceResponse

class NewsRepository(private val newsApiService: NewsApiService) {

    suspend fun getNewsCategories(): SourceResponse {
        return try {
            val result = newsApiService.getCategoryFromSources().body()
            result!!
        } catch (e: Throwable) {
            Log.d("test", e.message ?: "Unknown error")
            SourceResponse("error", emptyList())
        }
    }

    suspend fun getCategorySources(category: String): SourceResponse {
        return try {
            val result = newsApiService.getSourcesByCategory(category).body()
            result!!
        } catch (e: Throwable) {
            Log.d("test", e.message ?: "Unknown error")
            SourceResponse("error", emptyList())
        }
    }

    suspend fun getArticles(source: String): ArticleResponse {
        return try {
            val result = newsApiService.getArticlesBySource(source).body()
            return result!!
        } catch (e: Throwable) {
            ArticleResponse("error", 0, emptyList())
        }
    }

    suspend fun getSearchedSources(q: String): SourceResponse {
        return try {
            val result = newsApiService.getSearchedSourcesData(q).body()
            result!!
        } catch (e: Throwable) {
            SourceResponse("error", emptyList())
        }
    }

    suspend fun getSearchedArticles(q: String): ArticleResponse {
        return try {
            val result = newsApiService.getSearchedArticlesData(q).body()
            result!!
        } catch (e: Throwable) {
            ArticleResponse("error", 0, emptyList())
        }
    }
}
