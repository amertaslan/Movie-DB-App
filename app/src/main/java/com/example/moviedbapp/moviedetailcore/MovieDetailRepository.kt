package com.example.moviedbapp.moviedetailcore

import com.example.moviedbapp.Constants
import com.example.moviedbapp.movielistcore.MovieListApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.MOVIE_DETAIL_BASE_URL)
    .build()

object MovieDetailRepository {
    val retrofitService: MovieDetailApi by lazy {
        retrofit.create(MovieDetailApi::class.java)
    }
}