package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.buttonRoll)
        val diceLeft: ImageView = findViewById(R.id.DiceLeft)
        val diceRight: ImageView = findViewById(R.id.DiceRight)
        val resultText: TextView = findViewById(R.id.resultMessage)

        rollButton.setOnClickListener {
            val d1 = (1..6).random()
            val d2 = (1..6).random()

            resultText.visibility = View.VISIBLE

            diceLeft.setImageResource(getDiceImage(d1))
            diceRight.setImageResource(getDiceImage(d2))

            resultText.text = if (d1 == d2) "Selamat, Anda dapat dadu double!" else "Anda belum beruntung!"
        }
    }

    private fun getDiceImage(side: Int): Int = when (side) {
        1 -> R.drawable.dice_1; 2 -> R.drawable.dice_2; 3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4; 5 -> R.drawable.dice_5; 6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }
}