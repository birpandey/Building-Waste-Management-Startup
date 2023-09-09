package com.example.waste.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.waste.R
import com.example.waste.activity.Dashboard
import com.example.waste.application.ThrowwApplication.Companion.TAG
import com.example.waste.databinding.FragmentLoginBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
//        val getPhoneNumber = sharedPreferences.getString("phone_number", "")

        initialize()
//        if (getPhoneNumber != "") {
//            val intent = Intent(requireContext(), Dashboard::class.java)
//            startActivity(intent)
//        }
        return binding.root
    }

    private fun initialize() {
        binding.btnProceed.setOnClickListener { view ->
            val phoneNumber = binding.etPhoneNumber.text?.toString()?.trim()
            if (phoneNumber.isNullOrEmpty() || phoneNumber.length != 10) {
                // Handle the case where phoneNumber is empty, null, or not 10 digits long
                Toast.makeText(
                    requireContext(), "Please enter a valid 10-digit phone number",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                when (phoneNumber) {
                    "8340483779", "7979079192", "8299839817","7325847536","8565842829"-> {
                        // Navigate to otpScreen for any of the valid phone numbers
//                        otp bypass direct dashboard
                        val intent = Intent(requireContext(), Dashboard::class.java)
                        startActivity(intent)
                        activity?.finish()

//                        sendOTP(view)
                    }

                    else -> {
                        // Navigate to registration for any other phone numbers
                        findNavController(view).navigate(R.id.registration)
                    }
                }
            }
        }
    }
private lateinit var auth: FirebaseAuth
    private fun sendOTP(view: View) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // Automatically detected verification code
//                signInWithPhoneAuthCredential(credential)
                val bundle = Bundle()
                bundle.putString("phone", binding.etPhoneNumber.text.toString())
                val navController: NavController?
                navController = findNavController(view)
                sharedPrefLogin()
                navController.navigate(R.id.otpScreen, bundle)

            }

            override fun onVerificationFailed(e: FirebaseException) {
                // Handle verification failure
                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                val bundle = Bundle()
                bundle.putString("phone", binding.etPhoneNumber.text.toString())
                val navController: NavController?
                navController = findNavController(view)
                sharedPrefLogin()
                navController.navigate(R.id.otpScreen, bundle)

                // Save verification ID and token, show code input UI
                // You can send the verification code to the user via SMS, or use it for auto-retrieval
            }
        }
        auth = FirebaseAuth.getInstance()
        val phoneNumber =
            "+91" + binding.etPhoneNumber.text.toString()  // Replace with the user's phone number
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun sharedPrefLogin() {
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val sharedEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedEditor.putString("phone_number", binding.etPhoneNumber.text.toString())
        sharedEditor.apply()
        sharedEditor.commit()
    }
}