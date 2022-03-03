package com.example.hellotoast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var mCount = 0
    private lateinit var mShowCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mShowCount = findViewById(R.id.show_count)
        val countButton: Button = findViewById(R.id.button_count)
        countButton.setOnClickListener { countUp() }
        val toastButton: Button = findViewById(R.id.button_toast)
        toastButton.setOnClickListener {
            val intent = Intent(this, HelloActivity::class.java)
            intent.putExtra("COUNT", mCount)
            startActivity(intent)
        }
    }

    private fun showToast() {
        val toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun countUp() {
        mCount++
        mShowCount.text = mCount.toString()
    }
}
