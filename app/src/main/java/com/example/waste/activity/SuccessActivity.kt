package com.example.waste.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.waste.R
import com.example.waste.databinding.ActivitySuccessBinding
import com.example.waste.databinding.NoInternetDialogBinding
import com.example.waste.utility.NetworkConnection

class SuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessBinding
    private lateinit var networkBinding: NoInternetDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_success)
        networkBinding = NoInternetDialogBinding.inflate(layoutInflater)
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

       // setContentView(binding.root)
    }
}