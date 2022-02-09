package com.example.twoactivitiesintentextras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.PackageManagerCompat.LOG_TAG


const val PASSAGE_NUMBER = "com.example.twoactivitiesintentextras.extra.PASSAGE_NUMBER"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchSecondActivity(view: android.view.View) {
        when (view.id) {
            R.id.button_one -> {
                val intent = Intent(this, SecondActivity::class.java) .apply {
                    putExtra(PASSAGE_NUMBER, 1)
                }
                startActivity(intent)
            }
            R.id.button_two -> {
                val intent = Intent(this, SecondActivity::class.java) .apply {
                    putExtra(PASSAGE_NUMBER, 2)
                }
                startActivity(intent)
            }
            R.id.button_three -> {
                val intent = Intent(this, SecondActivity::class.java) .apply {
                    putExtra(PASSAGE_NUMBER, 3)
                }
                startActivity(intent)
            }
        }

    }
}