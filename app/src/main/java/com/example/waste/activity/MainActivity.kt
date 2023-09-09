package com.example.waste.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.waste.databinding.ActivityMainBinding
import com.example.waste.databinding.NoInternetDialogBinding
import com.example.waste.utility.NetworkStateManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var networkBinding: NoInternetDialogBinding
    private val activeNetworkStateObserver =
        Observer<Boolean> { isConnected ->  setView(isConnected) }
    private var getLocation:GetLocation?=null
    private fun setView(connected: Boolean) {
        if(!connected){
             val noNetworkIntent =  Intent(this, NetworkError::class.java)
            startActivity(noNetworkIntent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        networkBinding = NoInternetDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getLocation= GetLocation.getInstance(this@MainActivity)
        getLocation?.init()
        NetworkStateManager.instance?.networkConnectivityStatus
            ?.observe(this, activeNetworkStateObserver)
    }
}
