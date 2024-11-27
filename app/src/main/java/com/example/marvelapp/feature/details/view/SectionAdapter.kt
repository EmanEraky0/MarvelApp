package com.example.marvelapp.feature.details.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.RowDetailsBinding
import com.example.marvelapp.feature.marvelList.domain.models.Item
import com.example.marvelapp.utils.showImgGlide

class SectionAdapter(private var items:  List<Item>,var onItemClick: ((List<Item> ,Int) -> Unit)?) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {

    inner class SectionViewHolder(private val binding: RowDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(item: Item){
           binding.txtName.text=item.name
           binding.imgMarvel.setOnClickListener {
               onItemClick?.invoke(items ,adapterPosition)
           }
           binding.txtName.context.showImgGlide(binding.imgMarvel, binding.loading, item.resourceURI,"")
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding = RowDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SectionViewHolder(binding)


    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(mItemsList: List<Item>) {
        this.items = mItemsList
        notifyDataSetChanged()
    }

}