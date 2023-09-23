package com.example.waste.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.waste.R
import com.example.waste.databinding.ContentMainBinding
import com.example.waste.databinding.NoInternetDialogBinding
import com.example.waste.utility.NetworkStateManager
import com.example.waste.utility.SharedPreference
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView


class Dashboard : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var networkBinding: NoInternetDialogBinding
    private val activeNetworkStateObserver =
        Observer<Boolean> { isConnected -> setView(isConnected) }
    private var backButtonPressedTime = 0L
    lateinit var mainBinding:ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding =  ContentMainBinding.inflate(layoutInflater)
        networkBinding = NoInternetDialogBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        NetworkStateManager.instance?.networkConnectivityStatus
            ?.observe(this, activeNetworkStateObserver)

        val navView: BottomNavigationView = mainBinding.navView

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                 R.id.nav_orders, R.id.nav_address,R.id.nav_home, R.id.nav_support, R.id.nav_setting
            )
        )
        navView.setupWithNavController(navController)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()
    }

    private fun setView(connected: Boolean) {
        if (!connected) {
            val noNetworkIntent = Intent(this, NetworkError::class.java)
            startActivity(noNetworkIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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

    private fun logout() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val sharedEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedEditor.clear()
        sharedEditor.apply()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show()

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

    private val sharedPrefUtils: SharedPreference = SharedPreference()
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location != null) {
                        sharedPrefUtils.setUserLatitude(location.latitude)
                        sharedPrefUtils.setUserLongitude(location.longitude)
                    }
                }
            } else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }
}