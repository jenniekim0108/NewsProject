package com.example.noncomposeapp.data.response

import java.io.Serializable

data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
): Serializable
