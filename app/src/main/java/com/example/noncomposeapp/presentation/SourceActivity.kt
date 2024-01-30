package com.example.noncomposeapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.adapter.CategoryAdapter
import com.example.noncomposeapp.adapter.SourceAdapter
import com.example.noncomposeapp.databinding.ActivitySourceBinding

class SourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView(){
        val sourceAdapter = SourceAdapter()
        binding.source.rvSources.apply {
            adapter = sourceAdapter
            layoutManager = LinearLayoutManager(this@SourceActivity, RecyclerView.VERTICAL, false)
        }

        sourceAdapter.itemClickListener { selectedSource ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("selectedSource", selectedSource)
            startActivity(intent)
        }

    }

}