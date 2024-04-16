package com.eslamaped.movieapp.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eslamaped.movieapp.model.Movie
import com.eslamaped.movieapp.model.getMovies
import com.eslamaped.movieapp.navigations.MovieScreens
import com.eslamaped.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController:NavController){

    Scaffold(topBar = {

        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent

            ), title = { Text("Movies") })
    }
    )
    {

MainContent(navController)
    }

}


@Composable
fun MainContent(navController: NavController,
    movieList: List<Movie> =
     getMovies()
) {

    Column( modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.height(54.dp))
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) {

                        movie ->

                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
                    //run and open logcat and choose debug and search on  TAG
                    Log.d("TAG", "MainContetn: $movie")
                }
            }
        }
    }


}