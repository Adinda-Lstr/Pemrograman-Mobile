package com.example.scrollablelistcompose.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.scrollablelistcompose.data.DestinationDataSource
import com.example.scrollablelistcompose.ui.components.DestinationItem

@Composable
fun HomeScreen(navController: NavController) {
    val destinations = DestinationDataSource.dummyDestinations
    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.White)) {
        item {
            Spacer(modifier = Modifier.height(48.dp))
            Text("Wisata Populer", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(destinations) { dest ->
                    Card(
                        modifier = Modifier.size(140.dp, 200.dp).clickable { navController.navigate("detail/${dest.id}") },
                        shape = RoundedCornerShape(16.dp)
                    ) { Image(painterResource(dest.imageResId), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()) }
                }
            }
            Text("Daftar Destinasi", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
        }
        items(destinations) { dest ->
            DestinationItem(dest) { navController.navigate("detail/${dest.id}") }
        }
    }
}