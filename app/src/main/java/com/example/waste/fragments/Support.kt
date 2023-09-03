package com.example.waste.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.waste.databinding.FragmentSupportBinding

class Support : Fragment() {
    private lateinit var binding: FragmentSupportBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSupportBinding.inflate(inflater, container, false)
        binding.llClickEmail.setOnClickListener {
            sendToMail()
        }
        return binding.root
    }

    private fun sendToMail() {
        try {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_APP_EMAIL)
            startActivity(intent)
        } catch (e: android.content.ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                "There is no email app is installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}