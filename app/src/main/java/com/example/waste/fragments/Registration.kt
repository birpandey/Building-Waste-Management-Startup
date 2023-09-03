package com.example.waste.fragments

import DynamicDialogFragment
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.waste.R
import com.example.waste.databinding.FragmentRegistrationBinding

class Registration : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    var mDialog: Dialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        btnProceed()

        return binding.root

    }
    private fun initialize() {
        val firstName = binding.etFirstName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phoneNumber = binding.etPhoneNumber.text.toString().trim()

        if (firstName.isNullOrEmpty() || lastName.isNullOrEmpty() || email.isNullOrEmpty() || phoneNumber.isNullOrEmpty() || phoneNumber.length != 10) {

            // For example, you can show a Toast message:
            Toast.makeText(requireContext(), "Please fill in all the fields", Toast.LENGTH_SHORT)
                .show()
        } else {
//            view?.let { Navigation.findNavController(it).navigate(R.id.otpScreen) }
            showSuccessDialog()
        }
    }

    private fun btnProceed() {
        binding.btnProceed.setOnClickListener {
            initialize()
        }
    }

    private fun showSuccessDialog() {
        val dialogLayoutId =
            R.layout.successfull_created // Replace with your custom layout resource ID
        val dialogTitle = "Dialog Title" // Optional title
        val dismissDelayMillis = 3000L // 3 seconds
        val dialogFragment =
            DynamicDialogFragment.newInstance(dialogLayoutId, dialogTitle, dismissDelayMillis)
        dialogFragment.show(parentFragmentManager, "DynamicDialogFragment")
            Navigation.findNavController(requireView()).navigate(R.id.otpScreen)
        }
    }