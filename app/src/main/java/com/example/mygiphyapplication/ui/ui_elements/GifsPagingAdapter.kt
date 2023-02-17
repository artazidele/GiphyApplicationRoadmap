package com.example.mygiphyapplication.ui.ui_elements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.mygiphyapplication.data.entities.GifObject
import com.example.mygiphyapplication.databinding.GifsPagingRowBinding

class GifsPagingAdapter : PagingDataAdapter<GifObject, GifViewHolder>(GIF_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder =
        GifViewHolder(
            GifsPagingRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gif = getItem(position)
        if (gif != null) {
            holder.bind(gif)
        }
    }

    companion object {
        private val GIF_DIFF_CALLBACK = object : DiffUtil.ItemCallback<GifObject>() {
            override fun areItemsTheSame(oldItem: GifObject, newItem: GifObject): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GifObject, newItem: GifObject): Boolean =
                oldItem == newItem
        }
    }
}