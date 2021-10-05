package com.example.moviedbapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviedbapp.moviedetail.MovieDataModel

@Dao
interface MovieDatabaseDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: MovieDataModel)

    @Update (onConflict = OnConflictStrategy.REPLACE)
    fun update(item: MovieDataModel)

    @Delete
    fun delete(item: MovieDataModel)

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieDataModel>>

}