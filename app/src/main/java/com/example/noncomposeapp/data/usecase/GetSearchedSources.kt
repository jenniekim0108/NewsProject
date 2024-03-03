package com.example.noncomposeapp.data.usecase

import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.SourceResponse

class GetSearchedSources {
    suspend fun getData(newsApiService: NewsApiService, q: String): SourceResponse {
        return try {
            val result = newsApiService.getSearchedSources(q).body()
            result!!
        } catch (e: Throwable) {
            SourceResponse("error", emptyList())
        }
    }
}
