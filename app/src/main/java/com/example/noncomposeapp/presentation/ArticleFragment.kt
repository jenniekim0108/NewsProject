package com.example.noncomposeapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.R
import com.example.noncomposeapp.ViewModel
import com.example.noncomposeapp.adapter.ArticleAdapter
import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navbar.ivNavbar.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.navbar.tvNavbar.text = "Articles"

        val selectedSource = arguments?.getString("selectedSource").toString()

        if (selectedSource != null) {
            viewModel.setDataArticles(selectedSource)
            observeViewModel()
        } else {
            Toast.makeText(requireActivity(), "Data is empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupView(data: List<Article>) {
        val articleAdapter = ArticleAdapter(data)
        binding.article.rvArticles.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        }

        articleAdapter.itemClickListener { selectedArticle ->
            Log.d("tiara", "selected article $selectedArticle")

            val bundle = Bundle()
            bundle.putSerializable("selectedArticle", selectedArticle)

            val articleDetailFragment = ArticleDetailFragment()
            articleDetailFragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, articleDetailFragment)
                .commit()
        }
    }

    private fun observeViewModel() {
        viewModel.article.observe(requireActivity()) { article ->
            setupView(article)
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

    override fun onDestroyView() {
        super.onDestroyView()
    }


}