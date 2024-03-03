package com.example.noncomposeapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noncomposeapp.adapter.SourceAdapter
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.databinding.FragmentSourceBinding
import com.example.noncomposeapp.presentation.base.BaseFragment
import com.example.noncomposeapp.presentation.viewmodel.SourceViewModel
import java.util.Locale

class SourceFragment : BaseFragment<FragmentSourceBinding>() {

    private val sourceViewModel: SourceViewModel by viewModels()
    private var source: List<Source> = listOf()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSourceBinding {
        return FragmentSourceBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
        observeData()
    }

    override fun setupViews() {

        val listSource = source.map {
            it.name
        }.distinct()

        setupBinding()
        val sourceAdapter = createAdapter(listSource)
        setupRecyclerView(sourceAdapter)
        setAdapterClickListener(sourceAdapter)
    }

    private fun setupData() {
        val args: SourceFragmentArgs by navArgs()
        val selectedCategory = args.selectedCategory

        sourceViewModel.setDataSources(selectedCategory)
    }

    private fun setupBinding() {
        binding.navbar.ivNavbar.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.navbar.tvNavbar.text = "Sources"
    }

    private fun createAdapter(source: List<String>): SourceAdapter {
        return SourceAdapter(source)
    }

    private fun setupRecyclerView(sourceAdapter: SourceAdapter) {
        binding.source.rvSources.apply {
            adapter = sourceAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
        }
    }

    private fun setAdapterClickListener(sourceAdapter: SourceAdapter) {
        sourceAdapter.itemClickListener { selectedSource ->
            val articleFragment =
                SourceFragmentDirections.actionSourceFragmentToArticleFragment(selectedSource)
            findNavController().navigate(articleFragment)
        }
    }

    private fun setupSearch() {
        binding.searchBar.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let {
                    performSearch(p0)
                }
                return true
            }

        })
    }

    private fun performSearch(text: String) {
        val q = text.trim().lowercase(Locale.getDefault())

        if (q.length >= 3) {
            sourceViewModel.setDataSearchedSources(source, q)
        } else {
            sourceViewModel.resetSource()
        }
    }


    override fun observeData() {
        sourceViewModel.sourceByCategory.observe(requireActivity()) {
            source = it
            setupViews()
            Log.d("test", source.toString())
            setupSearch()
        }

    }
}
