package com.app.imdbclone.network

import com.app.imdbclone.model.MovieResponse
import retrofit2.http.GET

//https://imdb-api.com/en/API/Top250Movies/k_79xcolcq

interface MovieService {
    @GET("Top250Movies/k_79xcolcq")
    suspend fun getTopMovies(): MovieResponse
}