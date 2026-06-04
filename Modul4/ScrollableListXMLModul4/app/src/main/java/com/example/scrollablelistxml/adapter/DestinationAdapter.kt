package com.example.scrollablelistxml.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollablelistxml.data.Destination
import com.example.scrollablelistxml.databinding.ItemDestinationBinding

class DestinationAdapter(
    private var list: List<Destination>,
    private val onDetailClick: (Destination) -> Unit,
    private val onMapsClick: () -> Unit
) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDestinationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDestinationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dest = list[position]
        holder.binding.apply {
            tvName.text = dest.name
            tvRating.text = dest.rating
            tvDesc.text = dest.description
            imgDest.setImageResource(dest.imageResId)

            btnMaps.setOnClickListener {
                onMapsClick()

                val gmmIntentUri = Uri.parse(dest.mapsUrl)
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                it.context.startActivity(mapIntent)
            }

            btnDetail.setOnClickListener {
                onDetailClick(dest)
            }
        }
    }

    fun setData(newList: List<Destination>) {
        this.list = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}