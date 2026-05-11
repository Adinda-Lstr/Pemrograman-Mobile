package com.example.scrollablelistcompose.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.scrollablelistcompose.R
import com.example.scrollablelistcompose.data.DestinationDataSource

@Composable
fun DetailScreen(destId: Int, navController: NavController) {
    val dest = DestinationDataSource.dummyDestinations.find { it.id == destId } ?: return
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(Color.White)) {
        Box {
            Image(painterResource(dest.imageResId), null, Modifier.fillMaxWidth().height(350.dp), contentScale = ContentScale.Crop)

            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, top = 40.dp)
                    .size(40.dp)
                    .clickable { navController.popBackStack() },
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.9f),
                shadowElevation = 4.dp
            ) {
                Box(contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back_arrow),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(dest.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)

            Row {
                Text(dest.location, color = Color(0xFF6750A4), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(", ", color = Color(0xFF6750A4), fontWeight = FontWeight.Bold)
                Text("★ ", color = Color(0xFF6750A4), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(dest.rating.replace("★ ", ""), color = Color(0xFF6750A4), fontWeight = FontWeight.Normal, fontSize = 16.sp)
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), thickness = 1.dp, color = Color(0xFFE0E0E0))

            Text("Deskripsi Destinasi", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text(
                text = dest.description,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color(0xFF49454F),
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}