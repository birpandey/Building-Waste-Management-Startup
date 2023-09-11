package com.example.waste.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        holder.productName.text = currentItem.productName
        holder.price.text = currentItem.price
        holder.addItems.text = currentItem.addItems.toString()

        // Set a click listener for the button
        holder.addItems.setOnClickListener {
            val currentQuantity = currentItem.addItems.toInt()

            // Check if currentQuantity is greater than 0 before decrementing
            if (currentQuantity > 0) {
                currentItem.addItems = (currentQuantity - 1).toString() // Decrement
            } else {
                currentItem.addItems = (currentQuantity + 1).toString() // Increment
            }

            // Update the button text to display the new quantity
            holder.addItems.text = currentItem.addItems.toString()
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val price: TextView = itemView.findViewById(R.id.price)
        val productImage: ShapeableImageView = itemView.findViewById(R.id.e_images)
        val productName: TextView = itemView.findViewById(R.id.el_product_name)
        val addItems: Button = itemView.findViewById(R.id.btn_add)
    }
}



// val productImage: ShapeableImageView = itemView.findViewById(R.id.e_images)