package com.example.moviedbapp.moviedetailcore

import com.squareup.moshi.Json

data class MovieDetailResponse (
    val title: String,

    val id: String,

    @Json(name = "vote_average") val voteAverage: String,

    val genres: List<MovieDetailResponseCategory>,

    @Json(name = "poster_path") val posterPath: String,

    val overview: String,

    @Json(name = "release_date") val releaseDate: String,

    @Json(name = "runtime") val runTime: String,
)

data class MovieDetailResponseCategory(
    val name: String
)