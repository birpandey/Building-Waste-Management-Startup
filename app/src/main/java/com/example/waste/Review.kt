package com.example.waste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waste.databinding.ActivityReviewBinding

class Review : AppCompatActivity() {
    private lateinit var binder:ActivityReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder=ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binder.root)

        feedback()
        setupRating()
        skip()
        binder.submit.setOnClickListener {
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent) // Start SecondActivity
        }
    }

    //
    private fun feedback(){
        val feedback= binder.feedback.text.toString()
    }


    // Rating Star Used
    private fun setupRating() {
        binder.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->

        }
    }
    private fun skip(){
        binder.Skip.setOnClickListener{
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent) // Start SecondActivity
        }
    }
}