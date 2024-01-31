package com.example.noncomposeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.data.response.Source
import com.example.noncomposeapp.databinding.ItemCategoryBinding
import com.example.noncomposeapp.databinding.ItemSourceBinding

class SourceAdapter(
    private var data: List<Source> = listOf()
): RecyclerView.Adapter<SourceAdapter.ViewHolder>(){

    private var itemClick: ((Source) -> Unit) = {}

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    fun itemClickListener(listener: ((Source) -> Unit)) {
        itemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceAdapter.ViewHolder {
        return ViewHolder(ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SourceAdapter.ViewHolder, position: Int) {
       holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemSourceBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(data: Source){
                    itemView.apply {
                        binding.tvSourcce.text = data.name
                        setOnClickListener{
                            itemClick.invoke(data)
                        }
                    }
                }
            }

}