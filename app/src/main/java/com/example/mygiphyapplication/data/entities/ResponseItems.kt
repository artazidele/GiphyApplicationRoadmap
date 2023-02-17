package com.example.mygiphyapplication.data.entities

import com.squareup.moshi.Json

data class ResponseItems<T>(
    @field:Json(name = "results") val results: List<T>
)