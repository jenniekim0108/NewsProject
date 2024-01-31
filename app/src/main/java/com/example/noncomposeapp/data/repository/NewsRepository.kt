package com.example.noncomposeapp.data.repository

import android.util.Log
import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.data.response.ArticleResponse
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.data.response.SourceResponse
import retrofit2.Response

class NewsRepository(private val newsApiService: NewsApiService) {

    suspend fun getNewsCategories(): SourceResponse{
        val test = newsApiService.getCategoryFromSources().body()
        test?.let {
            Log.d("test", "Test api: $test")
            return test
        }
        return SourceResponse(
            status = "error",
            sources = emptyList()
        )
    }

    suspend fun getCategorySources(category: String): SourceResponse{
        val test1 = newsApiService.getSourcesByCategory(category).body()
        test1?.let {
            Log.d("test1", "Test api: $test1")
            return test1
        }
        return SourceResponse(
            status = "error",
            sources = emptyList()
        )
    }

    suspend fun getArticles(category: String, source: String): ArticleResponse{
        val test2 = newsApiService.getArticlesBySource(category, source).body()
        test2?.let {
            Log.d("test2", "Test api: $test2")
            return test2
        }
        return ArticleResponse(
            status = "error",
            totalResults = 0,
            articles = emptyList()
        )
    }
}
