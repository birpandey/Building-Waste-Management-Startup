package com.example.waste.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.waste.R
import com.example.waste.databinding.BannerItemBinding
import com.example.waste.models.BannerItem

class BannerAdapter(private val context: Context, private val bannerItems: List<BannerItem>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    lateinit var binding :BannerItemBinding

    inner class BannerViewHolder(val binding: BannerItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        binding =  BannerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        val realPosition = position%listSize()
        val user = bannerItems[realPosition]
        // Load the image into the ImageView using a library like Picasso or Glide
        // For example, using Picasso:
        Glide.with(context).load(user.imageUrl).into(holder.binding.bannerImage)
    }

    override fun getItemCount(): Int {
        return 3 * bannerItems.size
    }

    public fun listSize():Int{
        return bannerItems.size        // original list size
    }
}
