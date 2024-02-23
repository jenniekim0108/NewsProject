package com.example.noncomposeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noncomposeapp.databinding.ItemSourceBinding

//private var itemClick: ((String) -> Unit) = {}

class SourceAdapter(
    private var data: List<String> = listOf()
) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    private var itemClick: ((String) -> Unit) = {}

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    fun itemClickListener(listener: ((String) -> Unit)) {
        itemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceAdapter.ViewHolder {
        return ViewHolder(
            ItemSourceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SourceAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            itemView.apply {
                binding.tvSource.text = data
                setOnClickListener {
                    itemClick.invoke(data)
                }
            }
        }
    }

}