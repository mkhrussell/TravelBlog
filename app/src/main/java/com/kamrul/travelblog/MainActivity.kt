package com.kamrul.travelblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kamrul.travelblog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainTextView.text = "This is a sample text."
    }
}