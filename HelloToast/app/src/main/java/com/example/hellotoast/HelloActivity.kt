package com.example.hellotoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HelloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
        val count = intent.getIntExtra("COUNT", 0)
        val countText = findViewById<TextView>(R.id.countValue)
        countText.text = count.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}