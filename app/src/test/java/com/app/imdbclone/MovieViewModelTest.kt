package com.app.imdbclone

import com.app.imdbclone.model.Movie
import com.app.imdbclone.model.MovieResponse
import com.app.imdbclone.network.CoroutineDispatcherProvider
import com.app.imdbclone.network.MovieRepository
import com.app.imdbclone.ui.MovieViewModel
import com.app.imdbclone.ui.state.MovieUIState
import com.app.imdbclone.ui.state.MovieUiData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class MovieViewModelTest {

    private val movieRepository = mock<MovieRepository>()
    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()

    private val testDispatcher = CoroutineDispatcherProvider(
        IO = TestCoroutineDispatcher()
    )

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieRepository, CoroutineDispatcherProvider())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Test LOADED state`() {
        val mockList: List<Movie> = mutableListOf(Movie(
            title = "Movie",
            image = "img",
            fullTitle = "",
            year = "",
            crew = ""
        ))
      runBlocking {
          whenever(movieRepository.fetchMovies()).thenReturn(MovieResponse(items = mockList))
          movieViewModel = MovieViewModel(movieRepository, testDispatcher)
          Assert.assertEquals(MovieUIState.Content(mutableListOf(
              MovieUiData(imageUrl = "img", title = "Movie"))),movieViewModel.uiState.value
          )
      }
    }



}