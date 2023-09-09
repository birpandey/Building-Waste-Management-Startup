package com.example.waste.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.waste.R
import com.example.waste.models.BannerItem
import com.squareup.picasso.Picasso

class BannerAdapter(private val bannerItems: List<BannerItem>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bannerImage: ImageView = itemView.findViewById(R.id.banner_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val item = bannerItems[position]
        // Load the image into the ImageView using a library like Picasso or Glide
        // For example, using Picasso:
        Picasso.get().load(item.imageUrl).into(holder.bannerImage)
    }

    override fun getItemCount(): Int {
        return bannerItems.size
    }
}
