package com.app.imdbclone.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class CoroutineDispatcherProvider (
    val IO: CoroutineDispatcher = Dispatchers.IO,
    val DEFAULT: CoroutineDispatcher = Dispatchers.Default,
    val  MAIN: CoroutineDispatcher = Dispatchers.Main
)