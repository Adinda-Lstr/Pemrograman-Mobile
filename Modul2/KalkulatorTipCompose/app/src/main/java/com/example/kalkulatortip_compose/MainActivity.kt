package com.example.kalkulatortip_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipTimeScreen() {


    var amountInput by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(15.0) }
    var roundUp by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }


    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tip = (tipPercent / 100) * amount


    if (roundUp) {
        tip = ceil(tip)
    }


    val formattedTip = NumberFormat.getCurrencyInstance(Locale.US).format(tip)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Calculate Tip",
            fontSize = 18.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 24.dp)
        )


        TextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Bill Amount") },
            leadingIcon = {

                Icon(painter = painterResource(id = R.drawable.ic_money), contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3E5F5),
                unfocusedContainerColor = Color(0xFFF3E5F5),
                focusedIndicatorColor = Color(0xFF9C27B0),
                unfocusedLabelColor = Color(0xFF9C27B0)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))


        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = "${tipPercent.toInt()}%",
                onValueChange = {},
                readOnly = true,
                label = { Text("Tip Percentage") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF3E5F5),
                    unfocusedContainerColor = Color(0xFFF3E5F5),
                    focusedIndicatorColor = Color(0xFF9C27B0),
                    unfocusedLabelColor = Color(0xFF9C27B0)
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOf(15.0, 18.0, 20.0).forEach { selection ->
                    DropdownMenuItem(
                        text = { Text("${selection.toInt()}%") },
                        onClick = {
                            tipPercent = selection
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Round up tip?",
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF9C27B0),
                    checkedTrackColor = Color(0xFFE1BEE7)
                )
            )
        }

        Spacer(modifier = Modifier.height(48.dp))


        Text(
            text = "Tip Amount: $formattedTip",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF001F3F)
        )
    }
}