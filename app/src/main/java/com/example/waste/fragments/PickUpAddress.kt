package com.example.waste.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.waste.R
import com.example.waste.activity.SuccessActivity
import com.example.waste.databinding.FragmentPickUpAddressBinding
import com.example.waste.utility.SharedPreference
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PickUpAddress : Fragment(), OnMapReadyCallback {
    private lateinit var officeLocation: LatLng
    private lateinit var binder: FragmentPickUpAddressBinding
    private lateinit var googleMap: GoogleMap
    private val sharedPrefUtils: SharedPreference = SharedPreference()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = FragmentPickUpAddressBinding.inflate(inflater, container, false)
        val rootView = binder.root
        initialize()
        // Initialize the map fragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
        return rootView
    }

    private fun initialize() {
        binder.btnProceed.setOnClickListener {
            val flat = binder.etFlat.text.toString().trim()
            val area = binder.etArea.text.toString().trim()
            val landmark = binder.etLandmark.text.toString().trim()
            val pinCode = binder.etPinCode.text.toString().trim()

            if (flat.isBlank() || area.isBlank() || landmark.isBlank() || pinCode.isBlank()) {
                // Show an error message to the user indicating that all fields are required
                Toast.makeText(requireContext(), "Please fill in all the fields",
                    Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(requireContext(), SuccessActivity::class.java)
                startActivity(intent) // Start the SuccessActivity
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

            // Handle the latitude and longitude here
            // You can use them to perform location-related tasks
            // For example, display them on the UI or make API requests
            officeLocation = LatLng(sharedPrefUtils.getUserLatitude(), sharedPrefUtils.getUserLongitude())

            // Once officeLocation is initialized, you can use it here or in other methods.
            // For example, you can add the marker and move the camera here.
            googleMap.addMarker(MarkerOptions().position(officeLocation).title("Office Location"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(officeLocation, 15f))
        }
}
