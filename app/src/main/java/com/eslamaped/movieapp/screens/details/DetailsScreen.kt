package com.eslamaped.movieapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eslamaped.movieapp.model.Movie
import com.eslamaped.movieapp.model.getMovies
import com.eslamaped.movieapp.widgets.MovieRow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieId: String?){
val newMovieList= getMovies().filter { movie ->
    movie.id==movieId
}

    Scaffold(topBar = {

        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,

            ), actions = {

                             Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
                                 modifier = Modifier.padding(end = 5.dp).clickable { navController.popBackStack() })


            },title = { Text("Movies") })

    }
    ){

    Surface(modifier = Modifier.fillMaxSize()) {
Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
MovieRow(movie = newMovieList.first())
Spacer(modifier = Modifier.height(8.dp))
Divider()
    Text(text = "Movie Images")
    HorizScrolIV(newMovieList)
}
    }
    }


//
//    Surface(modifier = Modifier.fillMaxSize()) {
//Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//
//    Text(text = moviedata.toString(), style = MaterialTheme.typography.headlineMedium)
//
//    Spacer(modifier = Modifier.height(23.dp))
//Button(onClick = {navController.popBackStack()}) {
//    Text(text = "Go Back")
//}
//}
//    }

}

@Composable
private fun HorizScrolIV(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp).wrapContentSize(align = Alignment.Center,unbounded = false)
                    ,
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                AsyncImage(
                    model = image,

                    //movie.images[0],builder={},
                    contentDescription = "Movie Poster",
                    //         modifier = Modifier.clip(CircleShape)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
   DetailsScreen(navController = rememberNavController(), movieId ="Ss" )
}