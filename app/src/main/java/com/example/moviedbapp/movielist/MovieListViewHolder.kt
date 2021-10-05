package com.example.moviedbapp.movielist

import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.bindImage
import com.example.moviedbapp.databinding.MovieListItemBinding
import com.example.moviedbapp.movielistcore.Movie

class MovieListViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root)  {
    fun bind(item: Movie) {
        val posterPath = "https://image.tmdb.org/t/p/original/" + item.posterPath
        bindImage(binding.movieListItemImage, posterPath)
        binding.movieListItemTitleText.text = item.title
        binding.movieListItemPointText.text = item.voteAverage
        binding.executePendingBindings()
    }
}