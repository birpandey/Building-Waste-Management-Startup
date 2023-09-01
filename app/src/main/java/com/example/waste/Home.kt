package com.example.waste

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.waste.databinding.FragmentHomeBinding

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding.root
        initilize()
        bannerWebView()

        return rootView
    }

    private fun initilize(){
        binding.cvEwaste.setOnClickListener {
            val intent = Intent(requireContext(), SuccessActivity::class.java)
            startActivity(intent)
        }


        binding.cvPlastic.setOnClickListener {
            val intent = Intent(requireContext(), SuccessActivity::class.java)
            startActivity(intent)
        }


        binding.cvBattery.setOnClickListener {
            val intent = Intent(requireContext(), SuccessActivity::class.java)
            startActivity(intent)
        }


        binding.cvTyre.setOnClickListener {
            val intent = Intent(requireContext(), SuccessActivity::class.java)
            startActivity(intent)
        }

    }

    private fun bannerWebView(){
        val imageurl: ImageView = binding.imageView

        val url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAOZsOIBTyNND54nzK7qntwTYvOXjSz-m4yg&usqp=CAU"
        Glide.with(this).load(url).into(imageurl)

    }
}
