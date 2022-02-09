package com.example.twoactivitiesintentextras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val passage = intent.getIntExtra(PASSAGE_NUMBER, 0)
        val textView = findViewById<TextView>(R.id.textPassage).apply {
            when(passage) {
                1 -> {
                    text = getString(R.string.passage_one)
                }
                2 -> {
                    text = getString(R.string.passage_two)
                }
                3 -> {
                    text = getString(R.string.passage_three)
                }
            }

        }
    }
}