package com.example.moviedbapp.favoritelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.FavoriteListItemBinding
import com.example.moviedbapp.moviedetail.MovieDataModel
import com.example.moviedbapp.movielist.MovieListViewHolder

class FavoriteListAdapter(private val dataList: List<MovieDataModel>, private var onClickListener: FavoriteListListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoriteListViewHolder(FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FavoriteListViewHolder).bind(dataList[position])
        (holder as FavoriteListViewHolder).itemView.setOnClickListener {
            onClickListener.onClick(dataList[position].movieId)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}