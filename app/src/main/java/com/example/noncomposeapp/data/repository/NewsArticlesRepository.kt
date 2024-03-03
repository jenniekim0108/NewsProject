package com.example.noncomposeapp.data.repository

import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.ArticleResponse
import com.example.noncomposeapp.data.usecase.GetArticlesBySource
import com.example.noncomposeapp.data.usecase.GetSearchedArticles

class NewsArticlesRepository(
    private val newsApiService: NewsApiService
) {

    suspend fun getArticles(source: String): ArticleResponse {
        return GetArticlesBySource().getData(newsApiService, source)
    }

    suspend fun getSearchedArticles(q: String): ArticleResponse {
        return GetSearchedArticles().getData(newsApiService, q)
    }
}
