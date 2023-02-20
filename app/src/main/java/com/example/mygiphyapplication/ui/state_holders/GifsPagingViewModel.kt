package com.example.mygiphyapplication.ui.state_holders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mygiphyapplication.data.GifsRepository
import com.example.mygiphyapplication.data.entities.GifObject
import kotlinx.coroutines.flow.Flow

class GifsPagingViewModel (
    private val repository: GifsRepository,
) : ViewModel() {

    val items: Flow<PagingData<GifObject>> = Pager(
        config = PagingConfig(pageSize = 18, enablePlaceholders = false),
        pagingSourceFactory = { repository.gifPagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}