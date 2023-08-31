package com.example.waste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waste.databinding.ActivityEwasteItemListBinding

class EwasteItemList : AppCompatActivity() {
    private lateinit var binder:ActivityEwasteItemListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder= ActivityEwasteItemListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_ewaste_item_list)

    }
}