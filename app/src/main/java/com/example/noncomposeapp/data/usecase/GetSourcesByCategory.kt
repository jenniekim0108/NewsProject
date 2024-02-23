package com.example.noncomposeapp.data.usecase

import android.util.Log
import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.SourceResponse

class GetSourcesByCategory {
    suspend fun getData(newsApiService: NewsApiService, category: String): SourceResponse {
        return try {
            val result = newsApiService.getSourcesByCategory(category).body()
            result!!
        } catch (e: Throwable) {
            Log.d("test", e.message ?: "Unknown error")
            SourceResponse("error", emptyList())
        }
    }
}
