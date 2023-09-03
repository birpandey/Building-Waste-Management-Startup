package com.example.waste.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.waste.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
/*// To show the dialog from a Fragment
val dialogFragment = DynamicDialogFragment.newInstance(dialogLayoutId, dialogTitle, dismissDelayMillis)
dialogFragment.show(this *//* your fragment reference *//*, "DynamicDialogFragment")*/
