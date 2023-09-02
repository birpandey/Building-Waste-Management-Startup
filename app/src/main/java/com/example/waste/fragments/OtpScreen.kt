package com.example.waste.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.waste.R
import com.example.waste.activity.SuccessActivity
import com.example.waste.databinding.FragmentOtpScreenBinding
import com.example.waste.models.OtpViewModel

class OtpScreen : Fragment() {
    private lateinit var binding: FragmentOtpScreenBinding
    private lateinit var otpViewModel: OtpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp_screen, container, false)
        proceed()
        // Initialize ViewModel
        otpViewModel = ViewModelProvider(this@OtpScreen)[OtpViewModel::class.java]
        binding.otpViewModel = otpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
    private fun proceed(){

        binding.btnProceed.setOnClickListener {
            val otpViewModel = binding.otpViewModel as OtpViewModel
            val isOtpValid = otpViewModel.verifyOtp()

            if (isOtpValid) {
                showToast("OTP is valid")
                // Proceed with the next steps in your app
            } else {
                showToast("Invalid OTP. Please try again.")
                // Handle the case of an invalid OTP
            }
        }
        binding.t1ChangeNo.setOnClickListener{
            val intent = Intent(requireContext(), Login::class.java)
            startActivity(intent)
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}