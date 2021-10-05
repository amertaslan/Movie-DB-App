package com.example.moviedbapp.database

import androidx.lifecycle.LiveData
import com.example.moviedbapp.moviedetail.MovieDataModel

class MovieDataRepository(private val movieDatabase: MovieDatabase) {

    suspend fun insertMovie(item: MovieDataModel) = movieDatabase.getMovieDao().insert(item)

    suspend fun updateMovie(item: MovieDataModel) = movieDatabase.getMovieDao().update(item)

    suspend fun deleteMovie(item: MovieDataModel) = movieDatabase.getMovieDao().delete(item)

    fun getAllMovies(): LiveData<List<MovieDataModel>> = movieDatabase.getMovieDao().getAllMovies()

}