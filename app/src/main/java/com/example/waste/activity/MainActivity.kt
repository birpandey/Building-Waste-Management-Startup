package com.example.waste.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waste.R
import com.example.waste.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binder:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder= ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }
}