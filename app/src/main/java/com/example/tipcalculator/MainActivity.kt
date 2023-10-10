package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var etBase: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvTipPercent: TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView
    private lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBase = findViewById(R.id.etBase)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTipPercent = findViewById(R.id.tvTipPercent)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update the tip percentage TextView when SeekBar progress changes
                tvTipPercent.text = "$progress%"
                calculateTip() // Recalculate tip
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })

        // Set up a click listener for the calculate button
        buttonCalculate.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val billAmountStr = etBase.text.toString()

        if (billAmountStr.isNotEmpty()) {
            val billAmount = billAmountStr.toDouble()
            val tipPercentage = seekBarTip.progress.toDouble() / 100
            val tipAmount = billAmount * tipPercentage
            val totalAmount = billAmount + tipAmount

            tvTipAmount.text = String.format("%.2f", tipAmount)
            tvTotalAmount.text = String.format("%.2f", totalAmount)
        } else {
            // Handle the case where the bill amount is not entered
            tvTipAmount.text = "0.00"
            tvTotalAmount.text = "0.00"
        }
    }
}


