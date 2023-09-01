package com.example.waste.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.waste.activity.SuccessActivity
import com.example.waste.databinding.FragmentFeedbackBinding

class Feedback : Fragment() {
    private lateinit var binding: FragmentFeedbackBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        val rootView = binding.root


        feedback()
        setupRating()
        skip()


        return rootView


    }
    private fun feedback(){
        val feedback= binding.feedback.text.toString()
        binding.submit.setOnClickListener {
            val intent = Intent(requireContext(), SuccessActivity::class.java)
            startActivity(intent) // Start SecondActivity
        }
    }


    // Rating Star Used
    private fun setupRating() {
        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->

        }
    }
    private fun skip(){
        binding.Skip.setOnClickListener{
            val intent = Intent(requireContext(), SuccessActivity::class.java)
            startActivity(intent) // Start SecondActivity
        }
    }

}