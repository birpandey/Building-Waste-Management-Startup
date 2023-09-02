package com.example.waste.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.waste.databinding.FragmentPickUpAddressBinding
import com.example.waste.activity.SuccessActivity

class PickUpAddress : Fragment() {
    private lateinit var binder: FragmentPickUpAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentPickUpAddressBinding.inflate(inflater, container, false)
        val rootView = binder.root

        initialize()


        return rootView
    }


    private fun initialize() {
        binder.btnNext.setOnClickListener {
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
}