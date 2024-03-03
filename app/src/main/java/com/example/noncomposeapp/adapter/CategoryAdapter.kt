package com.example.noncomposeapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.noncomposeapp.databinding.ItemCategoryBinding
import java.util.Locale

class CategoryAdapter(
    private var data: List<String> = listOf()
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    //    private var data: List<String> = Categories.category
    private var itemClick: ((String) -> Unit) = {}

    fun itemClickListener(listener: ((String) -> Unit)) {
        itemClick = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        val size = data.size
        Log.d("Tiara", size.toString())
        return size
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            itemView.apply {
                binding.tvCategory.text = data.uppercase(Locale.getDefault())
                if (data.equals("general")) {
                    Glide.with(context)
                        .load("https://www.wikihow.com/images/thumb/0/00/Develop-Good-Communication-Skills-Step-12.jpg/v4-460px-Develop-Good-Communication-Skills-Step-12.jpg")
                        .into(binding.ivCategory)
                }
                if (data.equals("business")) {
                    Glide.with(context)
                        .load("https://www.wikihow.com/images/thumb/7/79/Start-Your-Own-Business-Step-11-Version-2.jpg/v4-460px-Start-Your-Own-Business-Step-11-Version-2.jpg.webp")
                        .into(binding.ivCategory)
                }
                if (data.equals("technology")) {
                    Glide.with(context)
                        .load("https://www.wikihow.com/images/thumb/7/7f/Is-Technology-a-Good-Career-Path-Step-12.jpg/v4-460px-Is-Technology-a-Good-Career-Path-Step-12.jpg")
                        .into(binding.ivCategory)
                }
                if (data.equals("sports")) {
                    Glide.with(context)
                        .load("https://www.wikihow.com/images/9/93/Be-Good-at-Sports-Step-24-Version-2.jpg")
                        .into(binding.ivCategory)
                }
                if (data.equals("entertainment")) {
                    Glide.with(context)
                        .load("https://www.wikihow.com/images/thumb/6/6c/Make-Electronic-Music-Step-15.jpg/-crop-375-300-375px-nowatermark-Make-Electronic-Music-Step-15.jpg.webp")
                        .into(binding.ivCategory)
                }
                if (data.equals("health")) {
                    Glide.with(context)
                        .load("https://www.wikihow.com/images/thumb/8/83/Have-a-Good-General-Healthy-Body-Step-17-Version-2.jpg/aid817331-v4-1200px-Have-a-Good-General-Healthy-Body-Step-17-Version-2.jpg")
                        .into(binding.ivCategory)
                }
                if (data.equals("science")) {
                    Glide.with(context)
                        .load("https://www.wikihow.com/images/thumb/c/c3/Enjoy-Studying-Science-Step-3-Version-2.jpg/v4-460px-Enjoy-Studying-Science-Step-3-Version-2.jpg.webp")
                        .into(binding.ivCategory)
                }
                setOnClickListener {
                    itemClick.invoke(data)
                }
            }
        }
    }

}