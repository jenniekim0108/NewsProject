package com.example.noncomposeapp.data.repository

import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.SourceResponse
import com.example.noncomposeapp.data.usecase.GetNewsCategories
import com.example.noncomposeapp.data.usecase.GetSearchedSources
import com.example.noncomposeapp.data.usecase.GetSourcesByCategory

class NewsSourcesRepository(private val newsApiService: NewsApiService) {

    suspend fun getCategories(): SourceResponse {
        return GetNewsCategories().getData(newsApiService)
    }

    suspend fun getCategorySources(category: String): SourceResponse {
        return GetSourcesByCategory().getData(newsApiService, category)
    }

    suspend fun getSearchedSources(q: String): SourceResponse {
        return GetSearchedSources().getData(newsApiService, q)
    }
}
