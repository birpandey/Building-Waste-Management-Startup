package com.example.waste.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waste.R
import com.example.waste.models.PriceWasteItemModel
import com.google.android.material.imageview.ShapeableImageView

class WasteItemsAdapter(private val list: List<PriceWasteItemModel>) :
    RecyclerView.Adapter<WasteItemsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.waste_items, parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]

//        holder.productImage.setImageResource(currentItem.productImage) // Assuming productImage is an image resource ID
        holder.productName.text = currentItem.productName
        holder.price.text = currentItem.price// Assuming price is an Int or a String
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val price: TextView = itemView.findViewById(R.id.price)
        val productImage: ShapeableImageView = itemView.findViewById(R.id.e_images)
        val productName: TextView = itemView.findViewById(R.id.el_product_name)
    }
}
