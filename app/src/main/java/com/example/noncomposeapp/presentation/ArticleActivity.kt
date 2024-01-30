package com.example.noncomposeapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.adapter.CategoryAdapter
import com.example.noncomposeapp.adapter.SourceAdapter
import com.example.noncomposeapp.databinding.ActivityArticleBinding
import com.example.noncomposeapp.databinding.ActivitySourceBinding

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView(){
        val sourceAdapter = SourceAdapter()
        binding.article.rvArticles.apply {
            adapter = sourceAdapter
            layoutManager = LinearLayoutManager(this@ArticleActivity, RecyclerView.VERTICAL, false)
        }

        sourceAdapter.itemClickListener { selectedSource ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("selectedSource", selectedSource)
            startActivity(intent)
        }

    }

}