package com.example.mygiphyapplication.ui.state_holders

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.mygiphyapplication.data.GifsRepository

class ViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: GifsRepository
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(GifsPagingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GifsPagingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}