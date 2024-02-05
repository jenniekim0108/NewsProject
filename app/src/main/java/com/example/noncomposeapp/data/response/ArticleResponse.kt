package com.example.noncomposeapp.data.response

import java.io.Serializable

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) : Serializable
