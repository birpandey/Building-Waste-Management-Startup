package com.example.waste.activity

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.waste.databinding.ActivityDashboardBinding
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.waste.R

class Dashboard : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

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
                R.id.nav_rating
            ), drawerLayout
        )

       /* navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> navController.navigate(R.id.nav_home)
                R.id.nav_orders -> navController.navigate(R.id.nav_orders)
                R.id.nav_address -> navController.navigate(R.id.nav_address)
                R.id.nav_support -> navController.navigate(R.id.nav_support)
                R.id.nav_about -> navController.navigate(R.id.nav_about)
                R.id.nav_rating -> navController.navigate(R.id.nav_rating)
                // Add more cases for other menu items if needed
            }
            drawerLayout.closeDrawers() // Close the drawer after item click
            true
        }*/
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity2, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}