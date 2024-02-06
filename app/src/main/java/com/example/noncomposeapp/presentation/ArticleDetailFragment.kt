package com.example.noncomposeapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.noncomposeapp.databinding.FragmentWebviewBinding

class ArticleDetailFragment : Fragment() {
    private lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(layoutInflater)
        return binding.root
//        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {

        val selectedArticle = arguments?.getString("selectedArticle").toString()

        Log.d("tiara", "detail artikel $selectedArticle")

        binding.webview.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl(selectedArticle)
        }
    }

}