package com.example.noncomposeapp.presentation

import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.noncomposeapp.ViewModel
import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.databinding.ActivityWebviewBinding

class ArticleDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val myWebView = WebView(this)
        setContentView(myWebView)

        val selectedArticle = intent.getStringExtra("selectedArticle").toString()

        Log.d("tiara", "detail artikel $selectedArticle")

        if (selectedArticle != null) {
            myWebView.apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(selectedArticle)
            }
        } else {
            Toast.makeText(applicationContext, "Data is empty", Toast.LENGTH_SHORT).show()
        }


    }



}