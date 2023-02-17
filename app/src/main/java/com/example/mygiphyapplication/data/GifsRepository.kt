package com.example.mygiphyapplication.data

class GifsRepository(private val backend: GiphyApiService, private val query: String) {
    fun gifPagingSource() = GiphyPagingSource(backend, query)
}