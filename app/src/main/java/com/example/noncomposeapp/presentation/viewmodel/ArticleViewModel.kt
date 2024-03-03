package com.example.noncomposeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noncomposeapp.data.network.ApiClient
import com.example.noncomposeapp.data.repository.NewsArticlesRepository
import com.example.noncomposeapp.data.response.Article
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    private val _article = MutableLiveData<List<Article>>()
    private val _progress = MutableLiveData<Boolean>()

    val article: LiveData<List<Article>> = _article
    val progress: LiveData<Boolean> = _progress

    private val newsArticlesRepository = NewsArticlesRepository(ApiClient.newsApiService)

    fun setDataArticles(source: String) {
        viewModelScope.launch {
            _article.postValue(newsArticlesRepository.getArticles(source).articles)
        }
    }

    fun setDataSearchedArticles(q: String) {
        viewModelScope.launch {
            _article.postValue(newsArticlesRepository.getSearchedArticles(q).articles)
        }
    }
}
