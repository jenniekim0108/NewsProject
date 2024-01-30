package com.example.noncomposeapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.adapter.CategoryAdapter
import com.example.noncomposeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView(){
        val categoryAdapter = CategoryAdapter()
        binding.categories.rvCategories.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }

        categoryAdapter.itemClickListener { selectedCategory ->
            val intent = Intent(this, SourceActivity::class.java)
            intent.putExtra("selectedCategory", selectedCategory)
            startActivity(intent)
        }

    }

}