package com.example.waste.adapter

import android.animation.Animator
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.waste.databinding.DashBoardListBinding
import com.example.waste.models.CategoryItems

class DashBoardListAdapter(
    private val context: Context,
    private val categoryItems: List<CategoryItems>
) : RecyclerView.Adapter<DashBoardListAdapter.DashBoardViewHolder>() {

    inner class DashBoardViewHolder(val binding: DashBoardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val binding =
            DashBoardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashBoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val realPosition = position % listSize()
        val categoryItem = categoryItems[realPosition]

        // Set the name for the item
        holder.binding.categoryItemsName.text = categoryItem.categoryItems

        // Load the Lottie animations from the list of file names
        val lottieAnimationView = holder.binding.lottieAnimation

        // Initialize the index for the current animation file
        var currentIndex = 0

        // Play the first animation
        playLottieAnimation(lottieAnimationView, categoryItem.lottieFileNames, currentIndex)

        // Set an AnimationListener to switch to the next animation
        lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                // Increment the index to switch to the next animation
                currentIndex++
                Log.d("AnimationDebug", "currentIndex: $currentIndex")
                if (currentIndex < categoryItem.lottieFileNames.size) {
                    // Play the next animation
                    playLottieAnimation(lottieAnimationView, categoryItem.lottieFileNames, currentIndex)
                } else {
                    // Reset the index to play animations in a loop
                    currentIndex = 0
                    Log.d("AnimationDebug", "Resetting to 0")
                    playLottieAnimation(lottieAnimationView, categoryItem.lottieFileNames, currentIndex)
                }
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
                Log.d("","")
            }
        })
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }

    private fun listSize(): Int {
        return categoryItems.size // original list size
    }

    private fun playLottieAnimation(
        lottieView: LottieAnimationView,
        fileNames: List<String>,
        index: Int
    ) {
        if (index < fileNames.size) {
            val resId = context.resources.getIdentifier(
                fileNames[index],
                "raw",
                context.packageName
            )
            if (resId != 0) {
                lottieView.setAnimation(resId)
                lottieView.playAnimation()
            }
        }
    }
}



