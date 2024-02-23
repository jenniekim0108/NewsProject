package com.example.noncomposeapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noncomposeapp.ViewModel
import com.example.noncomposeapp.adapter.CategoryAdapter
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.databinding.FragmentHomeBinding
import com.example.noncomposeapp.presentation.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: ViewModel by viewModels()
    private var source: List<Source> = listOf()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }


    override fun observeData() {
        viewModel.setDataCategories()
        viewModel.source.observe(viewLifecycleOwner) {
            source = it
            setupViews()
        }
    }

    override fun setupViews() {
        val data = source
        val listCategory = data.map {
            it.category
        }.distinct()

        val categoryAdapter = createAdapter(listCategory)
        setupRecyclerView(categoryAdapter)
        setupAdapterClickListener(categoryAdapter)
    }

    private fun createAdapter(data: List<String>): CategoryAdapter {
        return CategoryAdapter(data)
    }

    private fun setupRecyclerView(categoryAdapter: CategoryAdapter) {
        binding.categories.rvCategories.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
        }
    }

    private fun setupAdapterClickListener(categoryAdapter: CategoryAdapter) {
        categoryAdapter.itemClickListener { selectedCategory ->
            val sourceFragment =
                HomeFragmentDirections.actionHomeFragmentToSourceFragment(selectedCategory)
            findNavController().navigate(sourceFragment)
        }
    }

}



















































