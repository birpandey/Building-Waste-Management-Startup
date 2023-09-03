package com.example.waste.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.waste.R
import com.example.waste.databinding.FragmentPickUpAddressBinding
import com.example.waste.activity.SuccessActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PickUpAddress : Fragment(), OnMapReadyCallback {
    private lateinit var binder: FragmentPickUpAddressBinding
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = FragmentPickUpAddressBinding.inflate(inflater, container, false)
        val rootView = binder.root
        initialize()
        // Initialize the map fragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as? SupportMapFragment
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
                startActivity(intent) // Start the Dashboard activity
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Add a marker to a location (e.g., your office)
        val officeLocation = LatLng(51.5074, -0.1278) // Replace with your location's coordinates
        googleMap.addMarker(MarkerOptions().position(officeLocation).title("Office Location"))

        // Move the camera to the marker's position
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(officeLocation, 15f))
    }
}