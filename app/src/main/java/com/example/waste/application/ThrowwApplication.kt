package com.example.waste.application

import android.app.Application
import com.example.waste.utility.NetworkMonitoringUtil
import com.google.firebase.FirebaseApp

class ThrowwApplication: Application() {
    var mNetworkMonitoringUtil: NetworkMonitoringUtil? = null
    override fun onCreate() {
        super.onCreate()

        mNetworkMonitoringUtil = NetworkMonitoringUtil(applicationContext)
        mNetworkMonitoringUtil!!.checkNetworkState()
        mNetworkMonitoringUtil!!.registerNetworkCallbackEvents()
        FirebaseApp.initializeApp(this)

    }

    companion object {
        val TAG: String = ThrowwApplication::class.java.simpleName
    }
}