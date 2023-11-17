package com.sk.movieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sk.movieapp.model.Movie
import com.sk.movieapp.model.getMovies
import com.sk.movieapp.navigation.MovieScreens
import com.sk.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta),
            modifier = Modifier.shadow(elevation = 5.dp),
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            title = { Text(text = "Movies") }
        )
    }) {
        Column(modifier = Modifier.padding(it)) {
            MainContent(
                navController,
                getMovies()
            )
        }
    }
}


@Composable
fun MainContent(navController: NavController, movieList: List<Movie>) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(movieList) {
                MovieRow(it) { movie ->
                    navController.navigate(MovieScreens.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}
