package com.app.imdbclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.imdbclone.ui.MovieViewModel
import com.app.imdbclone.ui.theme.ImdbCloneTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.imdbclone.ui.composables.MovieLoadedView
import com.app.imdbclone.ui.composables.SearchView
import com.app.imdbclone.ui.state.MovieDetail
import com.app.imdbclone.ui.state.MovieUIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImdbCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navHostController = rememberNavController()
                    NavigationComponent(navHostController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home"){
            val viewModel: MovieViewModel = hiltViewModel()
            MovieScreen(navHostController = navController, viewModel = viewModel)
        }
        composable("Detail") {
            MovieDetailScreen()
        }

    }
}


@Composable
fun MovieDetailScreen() {
    Scaffold(
        // below line we are
        // creating a top bar.
        topBar = {
            TopAppBar(
                // in below line we are
                // adding title to our top bar.
                title = {
                    // inside title we are
                    // adding text to our toolbar.
                    Text(
                        text = "Details",
                        // below line is use
                        // to give text color.
                        color = Color.White
                    )
                },
                navigationIcon = {
                    // navigation icon is use
                    // for drawer icon.
                    IconButton(onClick = {

                    }) {
                        // below line is use to
                        // specify navigation icon.
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },
                // below line is use to give background color
                backgroundColor = colorResource(id = R.color.purple_200),

                // content color is use to give
                // color to our content in our toolbar.
                contentColor = Color.White,
                elevation = 12.dp
            )
        }) {
        Column {
            Text(text = "Movie Detail Screen")
        }
    }
}




@Composable
fun MovieScreen(navHostController: NavHostController, viewModel: MovieViewModel) {
    Column {
        var text by remember { mutableStateOf("") }
        SearchView(text = text, onValueChanged = {
            text = it
            viewModel.filterMovies(it)
        })
        MovieList(navController = navHostController)
    }
}

@Composable
fun MovieList(viewModel: MovieViewModel = viewModel(), navController: NavHostController) {
    when(val state = viewModel.uiState.collectAsState().value) {
        is MovieUIState.Content -> MovieLoadedView(movieUIState = state, onItemClick = {
            navController.navigate("Detail")
        })
        is MovieUIState.MovieApiError -> Snackbar() {
            Text(text = "Something went wrong")
        }
        is MovieUIState.Loading -> Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }
        is MovieUIState.NoData ->  Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("No results found")
        }
    }
}

