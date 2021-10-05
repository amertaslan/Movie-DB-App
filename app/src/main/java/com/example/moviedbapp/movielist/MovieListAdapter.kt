package com.example.moviedbapp.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.MovieListItemBinding
import com.example.moviedbapp.movielistcore.Movie

class MovieListAdapter(private var onClickListener: MovieListListener) : ListAdapter<Movie, RecyclerView.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieListViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieListViewHolder).bind(getItem(position))
        (holder as MovieListViewHolder).itemView.setOnClickListener {
            onClickListener.onClick(getItem(position).id)
        }
    }

    /*override fun getItemCount(): Int {
        return dataList.size
    }*/
}