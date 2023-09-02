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
import com.app.thorww.R
import com.app.thorww.databinding.FragmentOtpScreenBinding
import com.example.waste.activity.Dashboard
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
        autoCursor()
        proceed()
        // Initialize ViewModel
        otpViewModel = ViewModelProvider(this@OtpScreen)[OtpViewModel::class.java]
        binding.otpViewModel = otpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    private fun autoCursor() {
        val editTextList = listOf(binding.editTextOtp1, binding.editTextOtp2, binding.editTextOtp3, binding.editTextOtp4)

        editTextList.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        // Move to the next EditText when a digit is entered
                        if (index < editTextList.size - 1) {
                            editTextList[index + 1].requestFocus()
                        }
                    } else if (s?.isEmpty() == true) {
                        // Move to the previous EditText when the digit is deleted
                        if (index > 0) {
                            editTextList[index - 1].requestFocus()
                        }
                    }
                }
            })
        }
    }
    private fun proceed(){
        binding.btnProceed.setOnClickListener {
            val otpViewModel = binding.otpViewModel as OtpViewModel
            val isOtpValid = otpViewModel.verifyOtp()

            if (isOtpValid) {
                showToast("OTP is valid")
                // Proceed with the next steps in your app Start the Dashboard activity
                val intent = Intent(requireContext(), Dashboard::class.java)
                startActivity(intent)
            } else {
                showToast("Invalid OTP. Please try again.")
                // Handle the case of an invalid OTP
            }
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}