package com.app.imdbclone.network

import com.app.imdbclone.model.MovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService): Repository {
    override suspend fun fetchMovies(): MovieResponse = movieService.getTopMovies()
}