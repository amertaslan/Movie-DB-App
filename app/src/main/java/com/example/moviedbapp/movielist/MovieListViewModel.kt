package com.example.moviedbapp.movielist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.Constants
import com.example.moviedbapp.movielistcore.Movie
import com.example.moviedbapp.movielistcore.MovieListApiRepository
import com.example.moviedbapp.movielistcore.MovieListResponse
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private var dataList: List<MovieListModel> = listOf()

    private val _result = MutableLiveData<List<Movie>>()

    val result: LiveData<List<Movie>>
        get() = _result

    val itemCount: Int
        get() = _result.value?.size ?: 0

    init {
        getMovieListResponse()
    }

    private fun getMovieListResponse() {
        viewModelScope.launch {
            val getResult = MovieListApiRepository.retrofitService.getPopularMoviesAsync("ed9f01cc447dbb972f410bf5973eeac5", 1)
            try {
                val listResult = getResult.await()
                _result.value = listResult.results
                Log.e("Success", result.value?.size.toString())
            } catch (t: Throwable) {
                Log.e("Failure", t.message.toString())
            }
        }
    }

    fun getNextPagesResponses(page: Int) {
        viewModelScope.launch {
            val getResult = MovieListApiRepository.retrofitService.getPopularMoviesAsync(Constants.API_KEY, page)
            try {
                val listResult = getResult.await()
                _result.value = _result.value?.plus(listResult.results)
                Log.e("getNextPagesResponses: ", "Success" )
            } catch (t:Throwable) {
                Log.e("getNextPagesResponses: ", t.message.toString() )
            }
        }
    }

    fun getMovieList(response: MovieListResponse): List<MovieListModel> {
        response.results.forEach {
        }
        Log.e("Data size", dataList.size.toString())
        return dataList
    }
}