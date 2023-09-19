package com.example.waste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.waste.R
import com.example.waste.adapter.BannerAdapter
import com.example.waste.adapter.DashBoardListAdapter
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

        val bannerItems = listOf(
            BannerItem("https://media.istockphoto.com/id/1293353471/photo/recycling-symbol-abstract-concept-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=f-K28sQgRGVLHgFUqf4xsOttLR944FgNaTMsaiu7PfY="),
            BannerItem("https://media.istockphoto.com/id/1293353502/photo/recycling-symbol-abstract-concept-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=ambvpy-DifkF4WYb6WJKadaXRsu7RB8e2TsFtxdXDvM="),
            BannerItem("https://media.istockphoto.com/id/1273205088/photo/cyber-monday-abstract-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=3aDT6XZk66OfVjm5jmLODyTDAJutaUhNxW5FcBrvbh4="),
            BannerItem("https://media.istockphoto.com/id/1293353471/photo/recycling-symbol-abstract-concept-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=f-K28sQgRGVLHgFUqf4xsOttLR944FgNaTMsaiu7PfY="),
//            BannerItem("https://media.istockphoto.com/id/1293353502/photo/recycling-symbol-abstract-concept-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=ambvpy-DifkF4WYb6WJKadaXRsu7RB8e2TsFtxdXDvM="),
//            BannerItem("https://media.istockphoto.com/id/1273205088/photo/cyber-monday-abstract-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=3aDT6XZk66OfVjm5jmLODyTDAJutaUhNxW5FcBrvbh4=")
            // Add more banner items as needed
        )
        val adapter = DashBoardListAdapter(requireContext(), bannerItems)
        binding.maiList.adapter = adapter
    }

    private lateinit var bannerAdapter: BannerAdapter
    private fun banner() {
//        val bannerRecyclerView: RecyclerView = requireView().findViewById(R.id.banner_recycler_view)
        val bannerItems = listOf(
            BannerItem("https://media.istockphoto.com/id/1293353471/photo/recycling-symbol-abstract-concept-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=f-K28sQgRGVLHgFUqf4xsOttLR944FgNaTMsaiu7PfY="),
            BannerItem("https://media.istockphoto.com/id/1293353502/photo/recycling-symbol-abstract-concept-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=ambvpy-DifkF4WYb6WJKadaXRsu7RB8e2TsFtxdXDvM="),
            BannerItem("https://media.istockphoto.com/id/1273205088/photo/cyber-monday-abstract-3d-illustration.jpg?s=1024x1024&w=is&k=20&c=3aDT6XZk66OfVjm5jmLODyTDAJutaUhNxW5FcBrvbh4=")
            // Add more banner items as needed
        )
        bannerAdapter = BannerAdapter(requireContext(), bannerItems)
        binding.bannerRecyclerView.adapter = bannerAdapter
        setAdapter()

    }

    private fun setAdapter() {
        val listSize = bannerAdapter.listSize()
        binding.bannerRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
                if (firstVisiblePosition > listSize && firstVisiblePosition % listSize == 0) {
                    // when the position reaches to the end of the list, we will scroll to beginning of the list.
                    recyclerView.scrollToPosition(listSize)
                } else if (firstVisiblePosition == listSize - 1) {
                    // when position reaches to the beginning of list, then we need to scroll to list*2(end of list).
                    recyclerView.scrollToPosition(listSize * 2)
                }
            }
        })
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.bannerRecyclerView)
    }
}