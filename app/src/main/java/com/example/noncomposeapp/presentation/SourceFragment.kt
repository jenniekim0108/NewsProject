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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noncomposeapp.R
import com.example.noncomposeapp.ViewModel
import com.example.noncomposeapp.adapter.SourceAdapter
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.databinding.FragmentSourceBinding

class SourceFragment : Fragment() {
    private lateinit var binding: FragmentSourceBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSourceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navbar.ivNavbar.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.navbar.tvNavbar.text = "Sources"


        val selectedCategory = arguments?.getString("selectedCategory").toString()

        if (selectedCategory != null) {
            viewModel.setDataSources(selectedCategory)
            observeViewModel()
        } else {
            Toast.makeText(context, "Data is empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpView(data: List<Source>) {
        var listSource = data.map {
            it.name
        }.distinct()
        val sourceAdapter = SourceAdapter(listSource)
        binding.source.rvSources.apply {
            adapter = sourceAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
        }

        sourceAdapter.itemClickListener { selectedSource ->
            val bundle = Bundle()
            bundle.putSerializable("selectedSource", selectedSource)

            val articleFragment = ArticleFragment()
            articleFragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, articleFragment)
                .commit()
        }

    }

    private fun observeViewModel() {
        viewModel.sourceByCategory.observe(requireActivity()) { source ->
            setUpView(source)
            binding.searchBar.etSearch.addTextChangedListener { text ->
                val q = text.toString().trim().toLowerCase()

                if (q.length >= 3) {
                    Log.d("TAG", "observeViewModel: " + q)
                    viewModel.setDataSearchedSources(source, q)
                } else {
                    viewModel.resetSource()
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}