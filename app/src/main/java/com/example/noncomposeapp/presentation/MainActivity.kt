package com.example.noncomposeapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.noncomposeapp.R
import com.example.noncomposeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment()
        Log.d("tiara", "start activity")
    }

    private fun setupFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, HomeFragment())
            .commit()
    }


}