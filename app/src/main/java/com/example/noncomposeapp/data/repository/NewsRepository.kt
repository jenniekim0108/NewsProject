package com.example.noncomposeapp.data.repository

import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.Source
import retrofit2.Response

class NewsRepository(private val newsApiService: NewsApiService) {

//    suspend fun getNewsCategories(): Response<NewsModel> {
//        val result= newsApiService.getCategories()
//        return result
//    }

    suspend fun getNewsSources(query: String): Response<Source> {
        return newsApiService.getSourcesByCategory(query)
    }

}
