package com.example.waste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waste.databinding.ActivityPickupAddressBinding


class PickupAddress : AppCompatActivity() {
    private lateinit var binder:ActivityPickupAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder= ActivityPickupAddressBinding.inflate(layoutInflater)
        setContentView(binder.root)

        binder.btnNext.setOnClickListener {
            val intent = Intent(this, SuccessActivity::class.java)
            startActivity(intent) // Start SecondActivity
        }
    }
}