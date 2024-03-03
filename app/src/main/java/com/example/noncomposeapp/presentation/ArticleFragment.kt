package com.example.noncomposeapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.adapter.ArticleAdapter
import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.databinding.FragmentArticleBinding
import com.example.noncomposeapp.presentation.base.BaseFragment
import com.example.noncomposeapp.presentation.viewmodel.ArticleViewModel
import java.util.Locale

class ArticleFragment : BaseFragment<FragmentArticleBinding>() {

    private val viewModel: ArticleViewModel by viewModels()
    private var article: List<Article> = listOf()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentArticleBinding {
        return FragmentArticleBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding()

        val args: ArticleFragmentArgs by navArgs()
        val selectedSource = args.selectedSource

        viewModel.setDataArticles(selectedSource)
        observeData()
    }

    private fun setupBinding() {
        binding.navbar.ivNavbar.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.navbar.tvNavbar.text = "Articles"
    }

    override fun setupViews() {
        val articleAdapter = ArticleAdapter()
        articleAdapter.setData(article)

        setupRecyclerView(articleAdapter)
        setAdapterClickListener(articleAdapter)
    }

    override fun observeData() {
        viewModel.article.observe(requireActivity()) {
            article = it
            setupViews()
            setupSearch()
        }
    }

    private fun setupRecyclerView(articleAdapter: ArticleAdapter) {
        binding.article.rvArticles.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        }
    }

    private fun setAdapterClickListener(articleAdapter: ArticleAdapter) {
        articleAdapter.itemClickListener {
            val articleDetailFragment =
                ArticleFragmentDirections.actionArticleFragmentToArticleDetailFragment(it.url)
            findNavController().navigate(articleDetailFragment)
        }
    }

    private fun setupSearch() {
        val listener: SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
//                p0?.let (this@ArticleFragment::textSubmit)

                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text?.let(this@ArticleFragment::performSearch)
                return true
            }
        }
        binding.searchBar.searchView.setOnQueryTextListener(listener)
    }

    private fun performSearch(text: String) {
        val q = text.trim().lowercase(Locale.getDefault())
        if (q.length >= 3) viewModel.setDataSearchedArticles(q)
    }


}