package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DiceRollerComposeApp()
        }
    }
}

@Composable
fun DiceRollerComposeApp() {

    var d1 by remember { mutableStateOf(0) }
    var d2 by remember { mutableStateOf(0) }
    var msg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Image(
                    painter = painterResource(getDiceRes(d1)),
                    contentDescription = null,
                    modifier = Modifier.size(160.dp)
                )
                Image(
                    painter = painterResource(getDiceRes(d2)),
                    contentDescription = null,
                    modifier = Modifier.size(160.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    d1 = (1..6).random()
                    d2 = (1..6).random()
                },

                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBBADFF))
            ) {
                Text("ROLL", color = Color.Black, fontSize = 16.sp)
            }
        }


        if (d1 > 0) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 80.dp),
                color = Color(0xFFF3E5F5),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = if (d1 == d2) "Selamat, Anda dapat dadu double!" else "Anda belum beruntung!",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}

fun getDiceRes(side: Int): Int {
    return when (side) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }
}
