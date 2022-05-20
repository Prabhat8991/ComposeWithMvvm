package com.app.imdbclone.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.imdbclone.network.CoroutineDispatcherProvider
import com.app.imdbclone.network.MovieRepository
import com.app.imdbclone.ui.state.MovieUIState
import com.app.imdbclone.ui.state.MovieUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<MovieUIState>(MovieUIState.NoData)
    val uiState: StateFlow<MovieUIState> = _uiState

    private var cacheFullList: List<MovieUiData> = mutableListOf()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {
                val response = movieRepository.fetchMovies()
                cacheFullList = response.items.map {
                    MovieUiData(imageUrl = it.image, title = it.title)
                }
                _uiState.value = MovieUIState.Content(
                    movieList = cacheFullList
                )
            } catch (exception: Exception) {
                _uiState.value = MovieUIState.MovieApiError
            }

        }
    }

    fun filterMovies(searchText: String) {
        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            flow {
                emit(searchText)
            }.debounce(3000).collect { target ->
                if (target.isEmpty()) {
                    _uiState.value = MovieUIState.Content(
                        movieList = cacheFullList
                    )
                }
                val filteredMovies = (_uiState.value as? MovieUIState.Content)?.movieList?.filter {
                    it.title.contains(target, true)
                }?: mutableListOf()

                if (filteredMovies.isEmpty()) {
                    _uiState.value = MovieUIState.NoData
                } else {
                    _uiState.value = MovieUIState.Content(
                        movieList = filteredMovies
                    )
                }
            }
        }

    }

}