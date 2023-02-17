package com.example.mygiphyapplication.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mygiphyapplication.data.entities.GifObject

private const val STARTING_KEY = 1
const val PAGE_SIZE = 18

class GiphyPagingSource (
    val backend: GiphyApiService,
    val query: String
) : PagingSource<Int, GifObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifObject> {
        val start = params.key ?: STARTING_KEY
        Log.d("STARTING_KEY:", start.toString())
        var range = start.until(1)
        if (start==1) {
            range = start.until((start + params.loadSize)/3)
        } else {
            range = start.until(start + params.loadSize)
        }


        Log.d("GIF_COUNT", start.toString())

        return LoadResult.Page(
            data = backend.getDataPaging(
                "o3wHZDFtSrCp9ZPDf5Htfnl7Bj7XO0QJ",
                query,
                PAGE_SIZE,
                start
            ).data,

            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }

    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, GifObject>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun ensureValidKey(key: Int) = Integer.max(STARTING_KEY, key)
}