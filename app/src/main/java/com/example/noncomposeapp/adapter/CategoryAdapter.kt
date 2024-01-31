package com.example.noncomposeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.Categories
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.databinding.ItemCategoryBinding

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

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            itemView.apply {
                binding.tvCategory.text = data.toUpperCase()
                setOnClickListener {
                    itemClick.invoke(data)
                }
            }
        }
//                fun bind(data: String){
//                    itemView.apply {
//                        binding.tvCategory.text = data
//                        setOnClickListener{
//                            itemClick.invoke(data)
//                        }
//                    }
//                }
    }

}