package com.example.scrollablelistcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scrollablelistcompose.ui.components.DestinationItem
import com.example.scrollablelistcompose.ui.viewmodel.DestinationViewModel
import com.example.scrollablelistcompose.ui.viewmodel.DestinationViewModelFactory

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: DestinationViewModel = viewModel(
        factory = DestinationViewModelFactory("Wisata Kalimantan Selatan")
    )
) {
    val destinations by viewModel.destinations.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        item {
            Text(
                text = "Wisata Populer",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                items(destinations) { dest ->
                    Card(
                        modifier = Modifier
                            .size(width = 150.dp, height = 210.dp)
                            .clickable {
                                viewModel.onDetailClicked(dest)
                                navController.navigate("detail/${dest.id}")
                            },
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = dest.imageResId),
                            contentDescription = dest.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "Daftar Destinasi",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }

        items(destinations) { dest ->
            DestinationItem(
                dest = dest,
                onDetailClick = {
                    viewModel.onDetailClicked(dest)
                    navController.navigate("detail/${dest.id}")
                },
                onMapsClick = {
                    viewModel.onMapsClicked()
                }
            )
        }
    }
}