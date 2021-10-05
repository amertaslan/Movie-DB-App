package com.example.moviedbapp.moviedetailcore

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailApi {
    @GET("{movie_id}?language=en-US")
    fun getMovieDetailsAsync(@Path("movie_id") movie_id: String, @Query("api_key") api_key: String): Deferred<MovieDetailResponse>
}