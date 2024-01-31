package com.example.noncomposeapp.presentation

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.noncomposeapp.databinding.ActivityWebviewBinding

class ArticleDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myWebView = WebView(this)
        setContentView(myWebView)

        myWebView.loadUrl("https://www.hindustantimes.com/cities/chandigarh-news/chandigarh-mayoral-polls-live-updates-january-30-kuldeep-dhalor-manoj-sonkar-aap-congress-bjp-101706579538051.html")
    }



}