package com.example.moviedbapp.favoritelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviedbapp.database.MovieDataRepository
import com.example.moviedbapp.moviedetail.MovieDataModel

class FavoriteListViewModel(private val repository: MovieDataRepository) : ViewModel() {

    fun getAllMovies(): LiveData<List<MovieDataModel>> = repository.getAllMovies()

}