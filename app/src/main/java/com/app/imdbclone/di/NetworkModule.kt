package com.app.imdbclone.di

import com.app.imdbclone.network.MovieRepository
import com.app.imdbclone.network.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkModule {
    @Binds
    abstract fun providesRepository(repository: MovieRepository): Repository
}