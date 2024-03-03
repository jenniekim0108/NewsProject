package com.example.noncomposeapp.data.usecase

import android.util.Log
import com.example.noncomposeapp.data.network.NewsApiService
import com.example.noncomposeapp.data.response.SourceResponse

class GetNewsCategories {
    suspend fun getData(newsApiService: NewsApiService): SourceResponse? {
//        val call = newsApiService.getCategoryFromSources()
//        call.enqueue(object : Callback<SourceResponse> {
//            override fun onResponse(
//                call: Call<SourceResponse>,
//                response: Response<SourceResponse>
//            ) {
//                if (response.isSuccessful) response.body()
//                else SourceResponse("", emptyList())
//            }
//
//            override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
//                Log.d("", "")
//            }
//
//        })
        return try {
            newsApiService.getCategoryFromSources().body()
        } catch (e: Throwable) {
            Log.e("GetNewsCategories", "Error fetching news categories", e)
            SourceResponse("error", emptyList())
        }
    }

}
