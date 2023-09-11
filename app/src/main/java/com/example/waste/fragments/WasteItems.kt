package com.example.waste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waste.R
import com.example.waste.adapter.WasteItemsAdapter
import com.example.waste.databinding.FragmentWasteItemsBinding
import com.example.waste.models.PriceWasteItemModel

class WasteItems : Fragment() {
    private lateinit var binding: FragmentWasteItemsBinding
    private lateinit var wasteItemsAdapter: WasteItemsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWasteItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the RecyclerView in your layout
        recyclerView = view.findViewById(R.id.recyclerViewWasteItems)
        val dummyData = listOf(
            PriceWasteItemModel("10.00", "product_image_1", "Product 1", addItems = "0"),
            PriceWasteItemModel("15.00", "product_image_2", "Product 2", addItems = "0"),
            PriceWasteItemModel("8.50", "product_image_3", "Product 3", addItems = "0"),
            PriceWasteItemModel("12.99", "product_image_4", "Product 4", addItems = "0"),
            PriceWasteItemModel("5.75", "product_image_5", "Product 5",addItems = "0")
        )

        // Create a list of waste items (you should populate this list with your data)
        val wasteItemsList = ArrayList<PriceWasteItemModel>()

        // Create an instance of your adapter and pass the wasteItemsList
//        wasteItemsAdapter = WasteItemsAdapter(wasteItemsList)



        // Set the RecyclerView's layout manager (e.g., LinearLayoutManager or GridLayoutManager)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager


        // Apply the default AndroidX DividerItemDecoration
        recyclerView.addItemDecoration(DividerItemDecoration(requireActivity(), layoutManager.orientation))

        // Attach the adapter to the RecyclerView
//        recyclerView.adapter = wasteItemsAdapter
        recyclerView.adapter =WasteItemsAdapter(dummyData)
    }
}
