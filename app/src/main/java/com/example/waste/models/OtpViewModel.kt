package com.example.waste.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class OtpViewModel : ViewModel() {
    val otpDigit1 = MutableLiveData<String>()
    val otpDigit2 = MutableLiveData<String>()
    val otpDigit3 = MutableLiveData<String>()
    val otpDigit4 = MutableLiveData<String>()


    fun verifyOtp(): Boolean {
        val enteredOtp = "${otpDigit1.value}${otpDigit2.value}${otpDigit3.value}${otpDigit4.value}"
        return isValidOtp(enteredOtp)
    }

    private fun isValidOtp(enteredOtp: String): Boolean {
        // Replace this with your actual validation logic
        val storedOtp = "1234" // Replace with the actual stored OTP
        return enteredOtp == storedOtp
    }
}
