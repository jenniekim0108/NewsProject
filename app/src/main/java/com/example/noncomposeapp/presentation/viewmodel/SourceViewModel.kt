package com.example.noncomposeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noncomposeapp.data.network.ApiClient
import com.example.noncomposeapp.data.repository.NewsSourcesRepository
import com.example.noncomposeapp.data.response.Source
import kotlinx.coroutines.launch

class SourceViewModel : ViewModel() {
    private val _source = MutableLiveData<List<Source>>()
    private val _sourceByCategory = MutableLiveData<List<Source>>()
    private val _progress = MutableLiveData<Boolean>()

    private var originalList: List<Source> = listOf()

    val source: LiveData<List<Source>> = _source
    val sourceByCategory: LiveData<List<Source>> = _sourceByCategory
    val progress: LiveData<Boolean> = _progress

    private val newsSourcesRepository = NewsSourcesRepository(ApiClient.newsApiService)

    fun setDataCategories() {
        viewModelScope.launch {
            _source.postValue(newsSourcesRepository.getCategories()?.sources)
        }
    }

    fun setDataSources(category: String) {
        viewModelScope.launch {
            originalList = newsSourcesRepository.getCategorySources(category).sources
            _sourceByCategory.postValue(originalList)
        }
    }

    fun setDataSearchedSources(list: List<Source>, q: String) {
        viewModelScope.launch {
            _sourceByCategory.postValue(
                list.filter {
                    it.name.contains(q, true)
                }
            )
        }
    }

    fun resetSource() {
        _sourceByCategory.postValue(originalList)
    }

}
