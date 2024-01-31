package com.example.noncomposeapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noncomposeapp.data.network.ApiClient
import com.example.noncomposeapp.data.repository.NewsRepository
import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.data.response.Source
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.http.Query

class ViewModel: ViewModel() {
    private val _source = MutableLiveData<List<Source>>()
    private val _sourceByCategory = MutableLiveData<List<Source>>()
    private val _article = MutableLiveData<List<Article>>()
    private val _progress = MutableLiveData<Boolean>()

    val source: LiveData<List<Source>> = _source
    val sourceByCategory: LiveData<List<Source>> = _sourceByCategory
    val article: LiveData<List<Article>> = _article
    val progress: LiveData<Boolean> = _progress

    private val newsRepository = NewsRepository(ApiClient.newsApiService)

    fun setDataCategories(){
        viewModelScope.launch {
//            Log.d("tiara", result.toString())
            _source.postValue(newsRepository.getNewsCategories().sources)
        }
    }

    fun setDataSources(category: String){
        viewModelScope.launch {
            _sourceByCategory.postValue(newsRepository.getCategorySources(category).sources)
        }
    }

    fun setDataArticles(category: String, source: String){
        viewModelScope.launch {
            _article.postValue(newsRepository.getArticles(category, source).articles)
        }
    }

}