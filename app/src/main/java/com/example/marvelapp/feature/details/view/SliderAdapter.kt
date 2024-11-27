package com.example.marvelapp.feature.details.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.SliderItemBinding
import com.example.marvelapp.feature.marvelList.domain.models.Item
import com.example.marvelapp.utils.showImgGlide

class SliderAdapter(private val items: List<Item>) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    class SliderViewHolder(private val binding: SliderItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item :Item){
            binding.txtName.text = item.name

            binding.sliderImg.context.showImgGlide(
                binding.sliderImg,
                null,
                item.resourceURI,
                ""
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = SliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}
