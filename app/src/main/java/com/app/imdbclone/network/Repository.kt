package com.app.imdbclone.network

import com.app.imdbclone.model.MovieResponse

interface Repository {
    suspend fun fetchMovies(): MovieResponse
}