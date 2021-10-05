package com.example.moviedbapp.favoritelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.databinding.FavoriteListItemBinding
import com.example.moviedbapp.moviedetail.MovieDataModel

class FavoriteListAdapter(private val dataList: List<MovieDataModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoriteListViewHolder(FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FavoriteListViewHolder).bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}