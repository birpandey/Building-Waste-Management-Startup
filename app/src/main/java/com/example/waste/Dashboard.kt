package com.example.waste

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.waste.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Dashboard : AppCompatActivity() {

    private lateinit var binder: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binder.root)

        // Get the NavController associated with the mainFrame view
        val navController = findNavController(R.id.nav_host_fragment)

        // Find reference to the BottomNavigationView
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)

        // Hook your navigation controller to the BottomNavigationView
        navView.setupWithNavController(navController)
    }
}