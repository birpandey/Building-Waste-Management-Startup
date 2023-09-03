package com.example.waste.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.waste.R
import com.example.waste.databinding.ActivityMainBinding
import com.example.waste.utility.NetworkConnection

class MainActivity : AppCompatActivity() {
    private lateinit var networkDialog: LinearLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var retryButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize views for the network dialog
        networkDialog = findViewById(R.id.network_dialog)
        retryButton = findViewById(R.id.btn_retry)

        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this) { isConnected ->

            if (!isConnected) {
                // Display the network dialog when there is no internet connection
                binding.root.visibility = View.GONE
                networkDialog.visibility = View.VISIBLE
            } else {
                // Hide the network dialog and restore the main content
                binding.root.visibility = View.VISIBLE
                networkDialog.visibility = View.GONE
            }
        }
        // Handle retry button click to refresh network status
        retryButton.setOnClickListener {
            // Add code here to refresh the network status
        }
    }
}
