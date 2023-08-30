package com.example.waste

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.waste.R.*
import com.example.waste.databinding.ActivitySupportBinding

class Support : AppCompatActivity() {
    private lateinit var binder:ActivitySupportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder=ActivitySupportBinding.inflate(layoutInflater)
        setContentView(layout.activity_support)
        binder.clickemail.setOnClickListener {
            val email = "your_email@gmail.com"

            // Construct the Gmail compose URL
            val gmailUrl = "mailto:$email"

            // Create an intent to open Gmail or a mail app
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_LONG).show()

            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(gmailUrl))

            // Verify that there is an app available to handle the intent
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}