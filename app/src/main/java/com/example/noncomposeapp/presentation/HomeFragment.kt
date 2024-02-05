package com.example.noncomposeapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noncomposeapp.R
import com.example.noncomposeapp.ViewModel
import com.example.noncomposeapp.adapter.CategoryAdapter
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("tiara", "start fragment")

        viewModel.setDataCategories()
        observeViewModel()
    }

    private fun setUpView(data: List<Source>) {
        var listCategory = data.map {
            it.category
        }.distinct()
        val categoryAdapter = CategoryAdapter(listCategory)
        binding.categories.rvCategories.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
        }

        categoryAdapter.itemClickListener { selectedCategory ->

            val bundle = Bundle()
            bundle.putSerializable("selectedCategory", selectedCategory)

            val sourceFragment = SourceFragment()
            sourceFragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, sourceFragment)
                .commit()
        }

    }

    private fun observeViewModel() {

        viewModel.source.observe(requireActivity(), { source ->
            if (source.isNotEmpty()) {
                setUpView(source)
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}