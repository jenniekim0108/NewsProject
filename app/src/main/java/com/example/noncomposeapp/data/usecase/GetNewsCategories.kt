package com.example.noncomposeapp.data.usecase

import android.util.Log
import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.SourceResponse

class GetNewsCategories {
    suspend fun getData(newsApiService: NewsApiService): SourceResponse {
        return try {
            val result = newsApiService.getCategoryFromSources().body()
            result ?: SourceResponse("error", emptyList())
        } catch (e: Throwable) {
            Log.e("GetNewsCategories", "Error fetching news categories", e)
            SourceResponse("error", emptyList())
        }
    }
}
