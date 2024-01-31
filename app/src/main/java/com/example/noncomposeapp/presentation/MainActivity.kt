package com.example.noncomposeapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.ViewModel
import com.example.noncomposeapp.adapter.CategoryAdapter
import com.example.noncomposeapp.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.example.noncomposeapp.data.response.Source

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.setDataCategories()
        observeViewModel()
    }

    private fun setUpView(data: List<Source>){
//        var listCategory:MutableList<String> = mutableListOf()
//        for (item in data){
//            listCategory.add(item.category)
//        }
        var listCategory = data.map {
            it.category }.distinct()
        val categoryAdapter = CategoryAdapter(listCategory)
        binding.categories.rvCategories.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }

        categoryAdapter.itemClickListener { selectedCategory ->
            val intent = Intent(this, SourceActivity::class.java)
            intent.putExtra("selectedCategory", selectedCategory)
            startActivity(intent)
        }

    }

    private fun observeViewModel() {

        viewModel.source.observe(this, {source ->
//            Log.d("tir", source.toString())
            setUpView(source)
        })

    }

}