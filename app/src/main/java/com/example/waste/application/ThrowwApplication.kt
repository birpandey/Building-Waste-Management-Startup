package com.example.waste.application

import android.app.Application
import com.example.waste.utility.NetworkMonitoringUtil




class ThrowwApplication: Application() {
    var mNetworkMonitoringUtil: NetworkMonitoringUtil? = null
    override fun onCreate() {
        super.onCreate()

        mNetworkMonitoringUtil = NetworkMonitoringUtil(applicationContext)
        mNetworkMonitoringUtil!!.checkNetworkState()
        mNetworkMonitoringUtil!!.registerNetworkCallbackEvents()
    }

    companion object {
        val TAG: String = ThrowwApplication::class.java.getSimpleName()
    }
}