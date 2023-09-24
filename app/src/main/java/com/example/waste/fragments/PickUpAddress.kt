package com.example.waste.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class PickUpAddress : Fragment(), OnMapReadyCallback {
    private lateinit var binder: FragmentPickUpAddressBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var pickupMarker: Marker
    private lateinit var pickupLocation: LatLng
    private val sharedPrefUtils: SharedPreference = SharedPreference()

    // Add references to UI elements for displaying location details
    private lateinit var locationDetailsTextView: TextView

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
        pickupLocation =
            LatLng(sharedPrefUtils.getUserLatitude(), sharedPrefUtils.getUserLongitude())

        // Initialize references to UI elements for location details
        locationDetailsTextView = binder.etLocation

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
                Toast.makeText(
                    requireContext(), "Please fill in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(requireContext(), SuccessActivity::class.java)
                startActivity(intent) // Start the SuccessActivity
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Set initial pickup marker
        pickupMarker =
            googleMap.addMarker(MarkerOptions().position(pickupLocation).title("Your Location"))!!

        // Move the camera to the initial pickup location
        val defaultCameraPosition = CameraPosition.Builder()
            .target(pickupLocation)
            .zoom(15f)
            .build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(defaultCameraPosition))

        // Allow users to tap on the map to set the pickup location
        googleMap.setOnMapClickListener { latLng ->
            // Update the pickup location and marker
            pickupLocation = latLng
            pickupMarker.position = latLng

            // Update UI with location details
            updateLocationDetailsUI(pickupLocation)
        }
    }

    // Function to update UI elements with location details
    private fun updateLocationDetailsUI(location: LatLng) {
        val address = getAddressFromLatLng(location) // Implement this function to get address from LatLng

        // Update UI elements with location details
        locationDetailsTextView.text = address
    }

    // Function to get an address from LatLng (You can implement this using the Google Places API)
    private fun getAddressFromLatLng(latLng: LatLng): String {
        // Implement code to fetch address from the LatLng
        // You can use the Google Places API or a geocoding library for this purpose
        // Return the address as a string
        return "Sample Address"
    }
}

