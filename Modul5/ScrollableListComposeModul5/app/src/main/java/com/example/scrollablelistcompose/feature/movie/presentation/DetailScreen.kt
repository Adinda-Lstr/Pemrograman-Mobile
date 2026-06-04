package com.example.scrollablelistcompose.feature.movie.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.scrollablelistcompose.feature.movie.data.local.MovieEntity

@Composable
fun DetailScreen(movie: MovieEntity, onBackClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(Color.White)) {
        Box {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(350.dp),
                contentScale = ContentScale.Crop
            )

            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, top = 40.dp)
                    .size(40.dp)
                    .clickable { onBackClick() },
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.9f),
                shadowElevation = 4.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("←", fontSize = 24.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(movie.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)

            Row {
                Text("TMDB API", color = Color(0xFF6750A4), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(", ", color = Color(0xFF6750A4), fontWeight = FontWeight.Bold)
                Text("★ ", color = Color(0xFF6750A4), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("Populer", color = Color(0xFF6750A4), fontWeight = FontWeight.Normal, fontSize = 16.sp)
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), thickness = 1.dp, color = Color(0xFFE0E0E0))

            Text("Sinopsis Film", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text(
                text = movie.overview,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color(0xFF49454F),
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}