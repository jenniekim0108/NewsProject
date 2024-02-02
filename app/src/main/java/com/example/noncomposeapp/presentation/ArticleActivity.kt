package com.example.noncomposeapp.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.ViewModel
import com.example.noncomposeapp.adapter.ArticleAdapter
import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    private val viewModel: ViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navbar.ivNavbar.setOnClickListener {
            onBackPressed()
        }
        binding.navbar.tvNavbar.text = "Articles"


        val selectedCategory = intent.getStringExtra("selectedCategory").toString()
        val selectedSource = intent.getStringExtra("selectedSource").toString()

        if (selectedSource != null && selectedCategory != null) {
            viewModel.setDataArticles(selectedCategory, selectedSource)
            observeViewModel()
        } else {
            Toast.makeText(applicationContext, "Data is empty", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setUpView(data: List<Article>) {
        val articleAdapter = ArticleAdapter(data)
        binding.article.rvArticles.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(this@ArticleActivity, RecyclerView.VERTICAL, false)
        }

        articleAdapter.itemClickListener { selectedArticle ->
            Log.d("tiara", "selek artiikel $selectedArticle")

            val intent = Intent(this, ArticleDetailActivity::class.java)
            intent.putExtra("selectedArticle", selectedArticle)

            startActivity(intent)
        }

    }

    private fun observeViewModelTest() {
        viewModel.article.observe(this, { article ->
            if (article.isNotEmpty()) {
                setUpView(article)
            } else {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun observeViewModel() {
        viewModel.article.observe(this) { article ->
            setUpView(article)
            binding.searchBar.etSearch.addTextChangedListener { text ->
                val q = text.toString().trim().toLowerCase()

                if (q.length >= 3) {
                    viewModel.setDataSearchedArticles(q)
                } else {
//                    viewModel.resetSource()
                }
            }
        }

    }

}