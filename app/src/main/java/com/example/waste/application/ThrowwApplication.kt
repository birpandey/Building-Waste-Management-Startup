package com.example.waste.application

import android.app.Application
import com.example.waste.utility.NetworkMonitoringUtil
import com.example.waste.utility.SharedInit
import com.example.waste.utility.SharedPreference
import com.google.firebase.FirebaseApp

class ThrowwApplication: Application() {
    private var mNetworkMonitoringUtil: NetworkMonitoringUtil? = null
    override fun onCreate() {
        super.onCreate()

        mNetworkMonitoringUtil = NetworkMonitoringUtil(applicationContext)
        mNetworkMonitoringUtil!!.checkNetworkState()
        mNetworkMonitoringUtil!!.registerNetworkCallbackEvents()
        FirebaseApp.initializeApp(this)
        SharedInit.init(this)

    }

    companion object {
        val TAG: String = ThrowwApplication::class.java.simpleName
    }
}