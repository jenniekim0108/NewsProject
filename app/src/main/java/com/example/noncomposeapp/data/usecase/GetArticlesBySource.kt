package com.example.noncomposeapp.data.usecase

import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.ArticleResponse

class GetArticlesBySource {
    suspend fun getData(newsApiService: NewsApiService, source: String): ArticleResponse {
        return try {
            val result = newsApiService.getArticlesBySource(source).body()
            return result!!
        } catch (e: Throwable) {
            ArticleResponse("error", 0, emptyList())
        }
    }
}
