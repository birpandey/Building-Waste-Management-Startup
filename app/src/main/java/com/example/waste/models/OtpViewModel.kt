package com.example.waste.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class OtpViewModel : ViewModel() {
    val otpDigits = MutableLiveData<String>()

    fun verifyOtp(): Boolean {
        val enteredOtp = "${otpDigits.value}"
        return isValidOtp(enteredOtp)
    }

    private fun isValidOtp(enteredOtp: String): Boolean {
        // Replace this with your actual validation logic
        val storedOtp = "1234" // Replace with the actual stored OTP
        return enteredOtp == storedOtp
    }
}
