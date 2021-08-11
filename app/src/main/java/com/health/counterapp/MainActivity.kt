package com.health.counterapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var stepCount = 0
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        sharedPreferences = getSharedPreferences("MyStorage", Context.MODE_PRIVATE)

        // First Setup
        setUpCounterValue()

        // Reset Counter
        button_reset.setOnClickListener {
            reset()
        }

        // Increment Counter
        button_count.setOnClickListener {
            count()
        }
    }

    private fun setUpCounterValue() {
        stepCount = gerCount()
        displayCount(stepCount)
    }

    private fun count() {
        stepCount++
        storeCount(stepCount)
        displayCount(stepCount)
    }

    private fun reset() {
        stepCount = 0
        storeCount(stepCount)
        displayCount(stepCount)
    }

    private fun displayCount(countValue: Int) {
        tv_count.text = countValue.toString()
    }

    private fun storeCount(countValue: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("counter", countValue)
        editor.apply()
    }

    private fun gerCount() : Int {
         return sharedPreferences.getInt("counter", 0)
    }
}