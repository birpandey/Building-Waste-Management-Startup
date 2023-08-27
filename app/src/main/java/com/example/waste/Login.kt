package com.example.waste

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.waste.databinding.FragmentLoginBinding

class Login : Fragment(){
    private lateinit var binding: FragmentLoginBinding
    private var phoneNumber: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.btnProceed.setOnClickListener { view ->
            // Navigate to the Registration fragment using the action defined in the navigation graph
            val phoneNumber = binding.etPhoneNumber.text.toString()
            if (phoneNumber == "8340483779") {
                findNavController(view).navigate(R.id.otpScreen)
            } else {
                findNavController(view).navigate(R.id.registration)
            }
        }
        return binding.root
    }


}