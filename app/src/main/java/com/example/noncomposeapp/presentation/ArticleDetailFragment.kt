package com.example.noncomposeapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.noncomposeapp.databinding.FragmentWebviewBinding
import com.example.noncomposeapp.presentation.base.BaseFragment

class ArticleDetailFragment : BaseFragment<FragmentWebviewBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWebviewBinding {
        return FragmentWebviewBinding.inflate(layoutInflater)
    }

    override fun observeData() {
        setupViews()
    }

    override fun setupViews() {
        val args: ArticleDetailFragmentArgs by navArgs()
        val selectedArticle = args.selectedArticle

        binding.webview.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl(selectedArticle)
        }
    }
}
