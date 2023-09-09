package com.example.waste.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.waste.R
import com.example.waste.databinding.ActivityDashboardBinding
import com.example.waste.databinding.NoInternetDialogBinding
import com.example.waste.utility.GetLocation
import com.example.waste.utility.NetworkStateManager
import com.example.waste.utility.SharedPreference
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var networkBinding: NoInternetDialogBinding
    private val activeNetworkStateObserver =
        Observer<Boolean> { isConnected ->  setView(isConnected) }
    private var backButtonPressedTime = 0L
    private var getLocation:GetLocation?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        networkBinding = NoInternetDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NetworkStateManager.instance?.networkConnectivityStatus
            ?.observe(this, activeNetworkStateObserver)

        setSupportActionBar(binding.appBarMain.toolbar)
        getLocation= GetLocation.getInstance(this@Dashboard)
        getLocation?.init()
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Setting up ActionBarDrawerToggle
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_orders,
                R.id.nav_address,
                R.id.nav_support,
                R.id.nav_about,
                R.id.nav_rating,
                R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.menu.findItem(R.id.nav_logout).setOnMenuItemClickListener {
            logout()
            true
        }
    }

    private fun setView(connected: Boolean) {
        if(!connected){
            val noNetworkIntent =  Intent(this, NetworkError::class.java)
            startActivity(noNetworkIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_logout -> {
                // Handle logout item click
                logout()
                true
            }
            // Handle other menu items if needed
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout(){
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val sharedEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedEditor.clear()
        sharedEditor.apply()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        Toast.makeText(this,"You have been logged out",Toast.LENGTH_SHORT).show()

    }
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()

        if (currentTime - backButtonPressedTime < 2000) {
            // If back button is pressed within 2 seconds, exit the app
            super.onBackPressed()
        } else {
            // Show a toast message indicating the need to press back again to exit
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backButtonPressedTime = currentTime
        }
    }

}