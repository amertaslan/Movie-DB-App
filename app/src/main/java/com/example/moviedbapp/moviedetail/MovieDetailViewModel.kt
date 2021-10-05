package com.example.moviedbapp.moviedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapp.Constants
import com.example.moviedbapp.database.MovieDataRepository
import com.example.moviedbapp.moviedetailcore.MovieDetailRepository
import com.example.moviedbapp.moviedetailcore.MovieDetailResponse
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieDataRepository) : ViewModel() {

    suspend fun insertMovie(item: MovieDataModel) = repository.insertMovie(item)

    fun getMovieDetailResponse(id: String) : MutableLiveData<MovieDetailResponse> {
        val result = MutableLiveData<MovieDetailResponse>()
        viewModelScope.launch {
            val getResult = MovieDetailRepository.retrofitService.getMovieDetailsAsync(id, Constants.API_KEY)
            try {
                val listResult = getResult.await()
                result.value = listResult
                Log.e("Success Detail", result.value!!.releaseDate)
            } catch (t: Throwable) {
                Log.e("Failure Detail", t.message.toString())
            }
        }
        return result
    }
}