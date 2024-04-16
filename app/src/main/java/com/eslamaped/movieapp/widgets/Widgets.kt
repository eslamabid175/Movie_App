package com.eslamaped.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.eslamaped.movieapp.model.Movie
import com.eslamaped.movieapp.model.getMovies

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {

  var expanded by remember {
      mutableStateOf(false)
  }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.height(130.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp), shape = RectangleShape, tonalElevation = 4.dp,
                shadowElevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)

                        .build(),

                    //movie.images[0],builder={},
                    contentDescription = "Movie Poster",
                    modifier = Modifier.clip(CircleShape)
                )
            //  Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie Image")
            }

            Column(modifier = Modifier.padding(4.dp)) {

                Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
               // Text(text = "Director :${movie.director}", style = MaterialTheme.typography.bodyMedium)
                Text(text ="Released : ${movie.year}", style = MaterialTheme.typography.bodyMedium)
AnimatedVisibility(visible = expanded) {
    Column {
Text(
    buildAnnotatedString {
        withStyle(style = SpanStyle( fontSize = 13.sp)){append("Plot: ")}
        withStyle(style = SpanStyle(fontSize = 13.sp,fontWeight = FontWeight.Light)){ append(movie.plot)}
    }
, modifier = Modifier.padding(6.dp)
)

        Divider(modifier = Modifier.padding(3.dp))
        Text(text = "Director :${movie.director}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Actors :${movie.actors}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Rating :${movie.rating}", style = MaterialTheme.typography.bodyMedium)

    }
}
                Icon(imageVector = if (!   expanded)Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { expanded = !expanded }
                    //,tint= Color.DarkGray
                )
            }
        }

    }

}
