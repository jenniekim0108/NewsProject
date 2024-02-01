package com.example.noncomposeapp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.ViewModel
import com.example.noncomposeapp.adapter.CategoryAdapter
import com.example.noncomposeapp.adapter.SourceAdapter
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.databinding.ActivitySourceBinding
import java.io.Serializable

class SourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySourceBinding
    private lateinit var selectedCategory: String
    private val viewModel: ViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navbar.ivNavbar.setOnClickListener {
            onBackPressed()
        }
        binding.navbar.tvNavbar.text = "Sources"


        selectedCategory = intent.getStringExtra("selectedCategory").toString()

        if (selectedCategory != null) {
            viewModel.setDataSources(selectedCategory)
            observeViewModel()
        } else {
            Toast.makeText(applicationContext, "Data is empty", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setUpView(data: List<Source>) {
        val sourceAdapter = SourceAdapter(data)
        binding.source.rvSources.apply {
            adapter = sourceAdapter
            layoutManager = GridLayoutManager(this@SourceActivity, 2)
        }

        sourceAdapter.itemClickListener { selectedSource ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("selectedCategory", selectedCategory)
            intent.putExtra("selectedSource", selectedSource)

            startActivity(intent)
        }

    }

    private fun observeViewModel() {
        viewModel.sourceByCategory.observe(this, { source ->
            setUpView(source)
        })

    }

}