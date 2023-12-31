package com.example.waste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.waste.R
import com.example.waste.databinding.FragmentLoginBinding
class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private var phoneNumber: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        initialize()

        return binding.root
    }

    private fun initialize() {
        binding?.btnProceed?.setOnClickListener { view ->
            val phoneNumber = binding?.etPhoneNumber?.text?.toString()?.trim()
            if (phoneNumber.isNullOrEmpty() || phoneNumber.length != 10) {
                // Handle the case where phoneNumber is empty, null, or not 10 digits long
                Toast.makeText(
                    requireContext(), "Please enter a valid 10-digit phone number",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                when (phoneNumber) {
                    "8340483779", "7979079192", "8299839817" -> {
                        // Navigate to otpScreen for any of the valid phone numbers
                        findNavController(view).navigate(R.id.otpScreen)
                    }

                    else -> {
                        // Navigate to registration for any other phone numbers
                        findNavController(view).navigate(R.id.registration)
                    }
                }
            }
        }
    }
}