package com.app.imdbclone.ui.state

sealed class MovieUIState {

    data class Content(
        val movieList: List<MovieUiData>
    ): MovieUIState()

    object MovieApiError : MovieUIState()

    object Loading: MovieUIState()

    object NoData : MovieUIState()
}

data class MovieUiData(
    val imageUrl: String,
    val title: String
)

data class MovieDetail(
  val fullTitle: String,
  val year: String,
  val crew: String
)
