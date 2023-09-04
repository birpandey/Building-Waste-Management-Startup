package com.example.waste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import com.example.waste.R
import com.example.waste.databinding.FragmentHomeBinding

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding.root
        initialize()
        bannerWebView()

        return rootView
    }

    private fun initialize() {
        binding.cvEwaste.setOnClickListener { view ->
            findNavController(view).navigate(R.id.wasteItems)
        }

        binding.cvPlastic.setOnClickListener { view ->
            findNavController(view).navigate(R.id.wasteItems)
        }

        binding.cvBattery.setOnClickListener { view ->
            findNavController(view).navigate(R.id.wasteItems)
        }

        binding.cvTyre.setOnClickListener { view ->
            findNavController(view).navigate(R.id.wasteItems)
        }
    }

    private fun bannerWebView() {
        val imageUrl: ImageView = binding.imageView
        val url =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAOZsOIBTyNND54nzK7qntwTYvOXjSz-m4yg&usqp=CAU"
        Glide.with(this).load(url).into(imageUrl)
    }
}