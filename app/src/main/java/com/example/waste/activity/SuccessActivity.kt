package com.example.waste.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.waste.R
import com.example.waste.databinding.ActivitySuccessBinding
import com.example.waste.databinding.NoInternetDialogBinding
import com.example.waste.utility.NetworkConnection
import com.example.waste.utility.NetworkStateManager

class SuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessBinding
    private lateinit var networkBinding: NoInternetDialogBinding
    private val activeNetworkStateObserver =
        Observer<Boolean> { isConnected -> setView(isConnected) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_success)
        networkBinding = NoInternetDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NetworkStateManager.instance?.networkConnectivityStatus
            ?.observe(this, activeNetworkStateObserver)

    }


    private fun setView(connected: Boolean) {
        if(!connected){
            val noNetworkIntent =  Intent(this, NetworkError::class.java)
            startActivity(noNetworkIntent)
        }
    }
}