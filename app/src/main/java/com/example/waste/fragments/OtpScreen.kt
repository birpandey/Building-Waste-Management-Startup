package com.example.waste.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.waste.R
import com.example.waste.activity.Dashboard
import com.example.waste.databinding.FragmentOtpScreenBinding
import com.example.waste.models.OtpViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OtpScreen : Fragment() {
    private lateinit var phone: String
    private lateinit var binding: FragmentOtpScreenBinding
    private lateinit var otpViewModel: OtpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp_screen, container, false)
        phone = requireArguments().getString("phone").toString()
        proceed()
        // Initialize ViewModel
        otpViewModel = ViewModelProvider(this@OtpScreen)[OtpViewModel::class.java]
        binding.otpViewModel = otpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    private fun proceed() {
        binding.btnProceed.setOnClickListener {
            validateOTP()
        }
    }

    private fun validateOTP() {
        val phoneAuthCredential: PhoneAuthCredential = PhoneAuthProvider.getCredential(phone,binding.pinView.text.toString())
        val auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // User signed in successfully
                    val intent = Intent(requireContext(), Dashboard::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    activity?.finish()
                } else {
                    // Sign-in failed
                    Toast.makeText(
                        requireActivity(),
                        "Please Enter Correct OTP",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}