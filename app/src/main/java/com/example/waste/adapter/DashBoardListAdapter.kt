package com.example.waste.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.waste.databinding.BannerItemBinding
import com.example.waste.databinding.DashBoardListBinding
import com.example.waste.models.BannerItem

class DashBoardListAdapter (private val context: Context, private val bannerItems: List<BannerItem>) :
RecyclerView.Adapter<DashBoardListAdapter.DashBoardViewHolder>() {



    lateinit var binding : DashBoardListBinding

    inner class DashBoardViewHolder(val binding: DashBoardListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        binding =  DashBoardListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DashBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {

        val realPosition = position%listSize()
        val user = bannerItems[realPosition]
        // Load the image into the ImageView using a library like Picasso or Glide
        // For example, using Picasso:
        Glide.with(context).load(user.imageUrl).into(holder.binding.bannerImage)
    }

    override fun getItemCount(): Int {
        return  bannerItems.size
    }

    public fun listSize():Int{
        return bannerItems.size        // original list size
    }
}