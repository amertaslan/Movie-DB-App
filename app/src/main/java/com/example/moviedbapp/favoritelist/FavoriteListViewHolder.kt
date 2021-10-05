package com.example.moviedbapp.favoritelist

import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.FavoriteListItemBinding
import com.example.moviedbapp.moviedetail.MovieDataModel

class FavoriteListViewHolder(private val binding: FavoriteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieDataModel) {
        binding.model = item
        binding.executePendingBindings()
    }
}