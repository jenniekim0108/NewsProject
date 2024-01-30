package com.example.noncomposeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noncomposeapp.data.network.ApiClient
import com.example.noncomposeapp.data.repository.NewsRepository
import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.data.response.Source
import kotlinx.coroutines.launch
import retrofit2.http.Query

class ViewModel: ViewModel() {
    private val _source = MutableLiveData<List<String>>()
    private val _article = MutableLiveData<List<Article>>()
    private val _progress = MutableLiveData<Boolean>()

    val source: LiveData<List<String>> = _source
    val article: LiveData<List<Article>> = _article
    val progress: LiveData<Boolean> = _progress

    private val newsRepository = NewsRepository(ApiClient.newsApiService)

    fun sourceByCategory(query: String){
        viewModelScope.launch {
//            _source.postValue(newsRepository.getNewsSources(query).body()?.name)
        }
    }

}