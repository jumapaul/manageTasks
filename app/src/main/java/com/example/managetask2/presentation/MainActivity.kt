package com.example.managetask2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.managetask2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ManageTask2)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}