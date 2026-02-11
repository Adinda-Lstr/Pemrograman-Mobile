package com.example.tugasmobile_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi Komponen
        val switchMode = findViewById<SwitchCompat>(R.id.switchMode)
        val editNama = findViewById<EditText>(R.id.editNama)
        val btnSapa = findViewById<Button>(R.id.btnSapa)
        val txtHasil = findViewById<TextView>(R.id.txtHasil)

        // Logika Tombol Sapa
        btnSapa.setOnClickListener {
            val nama = editNama.text.toString()
            if (nama.isNotEmpty()) {
                txtHasil.text = "Hello, $nama!"
            } else {
                txtHasil.text = "Namanya diisi dulu ya!"
            }
        }

        // Logika Switch Mode Gelap/Terang
        switchMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}