package com.example.waste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.waste.R
import com.example.waste.databinding.FragmentPickUpAddressBinding
import com.example.waste.databinding.FragmentRegistrationBinding

class Registration : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val rootView = binding.root
        initialize()
        btnProceed()

        return rootView

    }

    private fun initialize() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phoneNumber = binding.etPhoneNumber.text.toString().trim()

        if (firstName.isNullOrEmpty() || lastName.isNullOrEmpty() || email.isNullOrEmpty() || phoneNumber.isNullOrEmpty()) {

            // For example, you can show a Toast message:
            Toast.makeText(requireContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show()
        } else {
            // Proceed with further actions here
        }

    }
    private fun btnProceed(){
        binding.btnProceed.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.otpScreen)
        }
    }

}