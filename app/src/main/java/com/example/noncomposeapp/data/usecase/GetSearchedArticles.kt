package com.example.noncomposeapp.data.usecase

import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.ArticleResponse

class GetSearchedArticles {
    suspend fun getData(newsApiService: NewsApiService, q: String): ArticleResponse {
        return try {
            val result = newsApiService.getSearchedArticles(q).body()
            result!!
        } catch (e: Throwable) {
            ArticleResponse("error", 0, emptyList())
        }
    }
}
