package com.example.kalkulatortip_xml

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var etCost: TextInputEditText
    private lateinit var acOptions: AutoCompleteTextView
    private lateinit var swRound: MaterialSwitch
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCost = findViewById(R.id.et_cost)
        acOptions = findViewById(R.id.ac_options)
        swRound = findViewById(R.id.sw_round)
        tvResult = findViewById(R.id.tv_result)

        val listPersen = arrayOf("15%", "18%", "20%")
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, listPersen)
        acOptions.setAdapter(adapter)

        acOptions.setDropDownBackgroundResource(android.R.color.transparent)
        acOptions.dropDownVerticalOffset = 0


        etCost.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { hitungTip() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        acOptions.setOnItemClickListener { _, _, _, _ -> hitungTip() }
        swRound.setOnCheckedChangeListener { _, _ -> hitungTip() }

        hitungTip()
    }

    private fun hitungTip() {
        val biaya = etCost.text.toString().toDoubleOrNull() ?: 0.0
        val persenStr = acOptions.text.toString()
        val persen = when {
            persenStr.contains("15") -> 0.15
            persenStr.contains("18") -> 0.18
            persenStr.contains("20") -> 0.20
            else -> 0.0
        }

        var tip = biaya * persen
        if (swRound.isChecked) tip = ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance(Locale.US).format(tip)
        tvResult.text = getString(R.string.tip_amount, formattedTip)
    }
}