package com.app.imdbclone.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "items") val items: List<Movie>
)

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "title") val title: String,
    @Json(name = "image") val image: String,
    @Json(name = "fullTitle") val fullTitle: String,
    @Json(name = "year") val year: String,
    @Json(name = "crew") val crew: String
)