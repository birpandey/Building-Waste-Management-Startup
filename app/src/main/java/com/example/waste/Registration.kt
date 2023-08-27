package com.example.waste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import com.example.waste.databinding.FragmentRegistrationBinding

class Registration : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)

        binding.btnProceed.setOnClickListener { view ->
            findNavController(view).navigate(R.id.otpScreen)
        }
        return binding.root

    }
}