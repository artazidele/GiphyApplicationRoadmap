package com.example.mygiphyapplication

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.mygiphyapplication.data.GifsRepository
import com.example.mygiphyapplication.data.GiphyApi
import com.example.mygiphyapplication.ui.state_holders.ViewModelFactory
import com.example.mygiphyapplication.ui.ui_elements.QUERY

object Injection {

    private val backend = GiphyApi.retrofitService
    private fun provideArticleRepository(): GifsRepository = GifsRepository(backend, QUERY)

    fun provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return ViewModelFactory(owner, provideArticleRepository())
    }
}