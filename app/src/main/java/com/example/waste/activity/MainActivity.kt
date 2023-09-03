package com.example.waste.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.waste.R
import com.example.waste.databinding.ActivityMainBinding
import com.example.waste.databinding.NoInternetDialogBinding
import com.example.waste.utility.NetworkConnection

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var networkBinding: NoInternetDialogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        networkBinding = NoInternetDialogBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this) { isConnected ->
            if (!isConnected) {
                // Display the network dialog when there is no internet connection
                setContentView(networkBinding.root)
            } else {
                // Hide the network dialog and restore the main content
                setContentView(binding.root)
            }
        }
        // Handle retry button click to refresh network status
        networkBinding.btnRetry.setOnClickListener {
            // Add code here to refresh the network status
        }
    }
}
