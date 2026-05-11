package com.example.scrollablelistxml.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollablelistxml.data.Destination
import com.example.scrollablelistxml.databinding.ItemTrendingBinding

class TrendingAdapter(
    private val list: List<Destination>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemTrendingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dest = list[position]
        holder.binding.imgTrending.setImageResource(dest.imageResId)
        holder.itemView.setOnClickListener { onItemClick(dest.id) }
    }

    override fun getItemCount() = list.size
}