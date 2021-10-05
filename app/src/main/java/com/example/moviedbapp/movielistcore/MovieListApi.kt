package com.example.moviedbapp.movielistcore

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListApi {
    @GET("?language=en-US")
    fun getPopularMoviesAsync(@Query("api_key") api_key: String, @Query("page") page: Int): Deferred<MovieListResponse>
}