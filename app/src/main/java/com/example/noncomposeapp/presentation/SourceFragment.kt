package com.example.noncomposeapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noncomposeapp.ViewModel
import com.example.noncomposeapp.adapter.SourceAdapter
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.databinding.FragmentSourceBinding
import com.example.noncomposeapp.presentation.base.BaseFragment
import java.util.Locale

class SourceFragment : BaseFragment<FragmentSourceBinding>() {

    private val viewModel: ViewModel by viewModels()
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

        viewModel.setDataSources(selectedCategory)
    }

    private fun setupBinding() {
        binding.navbar.ivNavbar.setOnClickListener {
            requireActivity().onBackPressed()
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
        binding.searchBar.etSearch.addTextChangedListener { text ->
            val q = text.toString().trim().lowercase(Locale.getDefault())

            if (q.length >= 3) {
                viewModel.setDataSearchedSources(source, q)
            } else {
                viewModel.resetSource()
            }
        }
    }

    override fun observeData() {
        viewModel.sourceByCategory.observe(requireActivity()) {
            source = it
            setupViews()
            Log.d("test", source.toString())
            setupSearch()
        }

    }
}
