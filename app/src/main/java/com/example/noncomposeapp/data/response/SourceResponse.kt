package com.example.noncomposeapp.data.response

import java.io.Serializable

data class SourceResponse (
    val status: String,
    val sources: List<Source>
):Serializable
