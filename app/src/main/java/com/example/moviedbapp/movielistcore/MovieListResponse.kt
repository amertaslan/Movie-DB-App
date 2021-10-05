package com.example.moviedbapp.movielistcore

import com.squareup.moshi.Json

data class MovieListResponse (
    val results: List<Movie>
)

data class Movie (

    @Json(name = "poster_path")var posterPath: String,

    var id: String,

    var title: String,

    @Json(name = "vote_average")var voteAverage: String
)