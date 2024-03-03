package com.example.noncomposeapp.data.response

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
