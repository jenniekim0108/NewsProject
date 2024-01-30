package com.example.noncomposeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.Categories
import com.example.noncomposeapp.data.response.Article
import com.example.noncomposeapp.databinding.ItemArticleBinding
import com.example.noncomposeapp.databinding.ItemCategoryBinding
import com.example.noncomposeapp.model.NewsModel

class ArticleAdapter: RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){

    private var data: List<Article> = listOf()
    private var itemClick: ((String) -> Unit) = {}

    fun itemClickListener(listener: ((String) -> Unit)) {
        itemClick = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        return ViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
       holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemArticleBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(data: Article){
                    itemView.apply {
                        binding.tvTitle.text = data.title
                        binding.tvUrl.text = data.url
                    }
                }
            }

}