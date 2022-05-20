package com.app.imdbclone.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.imdbclone.ui.state.MovieUIState
import com.app.imdbclone.ui.state.MovieUiData

typealias OnItemClick = () -> Unit

@Composable
internal fun MovieLoadedView(movieUIState: MovieUIState.Content, onItemClick: OnItemClick) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp), modifier = Modifier) {
       items(movieUIState.movieList, key = null) {movieData ->
           MovieTile(movieTileData = movieData, onItemClick = onItemClick)
       }
    }
}

@Composable
internal fun MovieTile(modifier: Modifier = Modifier, movieTileData: MovieUiData, onItemClick: OnItemClick /* = () -> kotlin.Unit */) {
    Card(modifier = modifier.padding(20.dp).background(color = Color.Gray).clickable {
        onItemClick()
    }) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(movieTileData.imageUrl),
                contentDescription = null,
                modifier = modifier
                    .size(100.dp)
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(text = movieTileData.title, modifier = modifier.fillMaxWidth())
        }
    }
}

@Composable
internal fun SearchView(text: String, onValueChanged: (text: String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = text,
        onValueChange = onValueChanged
    )
}


