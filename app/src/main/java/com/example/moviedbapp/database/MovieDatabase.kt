package com.example.moviedbapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviedbapp.moviedetail.MovieDataModel

@Database(entities = [MovieDataModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun getMovieDao(): MovieDatabaseDao

    companion object {
        private const val DB_NAME = "movie_database.db"
        @Volatile private var instance: MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            DB_NAME
        ).build()
    }
}