package com.example.waste.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.waste.databinding.NoInternetDialogBinding
import com.example.waste.utility.NetworkStateManager

class NetworkError : AppCompatActivity()  {
    lateinit var binding : NoInternetDialogBinding
    private val activeNetworkStateObserver =
        Observer<Boolean> { isConnected ->  setView(isConnected) }

    private fun setView(connected: Boolean) {
        if(connected){
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  NoInternetDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    override fun onResume() {
        super.onResume()
        NetworkStateManager.instance?.networkConnectivityStatus
            ?.observe(this, activeNetworkStateObserver)
    }
    override fun onBackPressed() {
    }
}