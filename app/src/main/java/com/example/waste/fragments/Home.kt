package com.example.waste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.waste.R
import com.example.waste.adapter.BannerAdapter
import com.example.waste.databinding.FragmentHomeBinding
import com.example.waste.models.BannerItem

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val rootView = binding.root
        initialize()
        banner()

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

    private fun banner() {
        val bannerRecyclerView: RecyclerView = requireView().findViewById(R.id.banner_recycler_view)
        val bannerItems = listOf(
            BannerItem("https://media.istockphoto.com/id/1293353471/photo/recycling-symbol-abstract-concept-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=f-K28sQgRGVLHgFUqf4xsOttLR944FgNaTMsaiu7PfY="),
            BannerItem("https://media.istockphoto.com/id/1293353502/photo/recycling-symbol-abstract-concept-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=ambvpy-DifkF4WYb6WJKadaXRsu7RB8e2TsFtxdXDvM="),
            BannerItem("https://media.istockphoto.com/id/1273205088/photo/cyber-monday-abstract-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=3aDT6XZk66OfVjm5jmLODyTDAJutaUhNxW5FcBrvbh4=")
            // Add more banner items as needed
        )
        val bannerAdapter = BannerAdapter(bannerItems)
        bannerRecyclerView.adapter = bannerAdapter

    }
}