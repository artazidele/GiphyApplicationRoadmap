package com.example.mygiphyapplication.ui.ui_elements

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygiphyapplication.data.entities.GifObject
import com.example.mygiphyapplication.databinding.GifsPagingRowBinding

class GifViewHolder(
    private val binding: GifsPagingRowBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(gif: GifObject) {
        binding.apply {
            val imageView = binding.gifUrlIv
            val urlUrl = gif.images.original.url
            Glide.with(imageView.context)
                .asGif()
                .load(urlUrl)
                .into(imageView)
        }
    }
}