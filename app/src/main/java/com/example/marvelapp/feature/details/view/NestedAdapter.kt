package com.example.marvelapp.feature.details.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ItemRecycleBinding
import com.example.marvelapp.feature.marvelList.domain.models.SectionItem

class NestedAdapter(private var mItemsList: List<SectionItem>) :
    RecyclerView.Adapter<NestedViewHolder>() {
    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedViewHolder {
        val binding = ItemRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NestedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NestedViewHolder, position: Int) {
        holder.bind(mItemsList[position], onItemClick)

    }

    override fun getItemCount(): Int = mItemsList.size


    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(mItemsList: List<SectionItem>) {
        this.mItemsList = mItemsList
        notifyDataSetChanged()
    }


}

class NestedViewHolder(private val itemBinding: ItemRecycleBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private var requestItem: SectionItem? = null


    fun bind(requestItem: SectionItem, onItemClick: ((Int) -> Unit)?) {
        this.requestItem = requestItem

        if (requestItem.name != "")
            itemBinding.txtTitleRecycle.text = requestItem.name
        else
            itemBinding.txtTitleRecycle.visibility = View.GONE


        if (requestItem.items.isEmpty()) {
            itemBinding.txtTitleRecycle.visibility = View.GONE
            itemBinding.recycleSection.visibility = View.GONE
        } else {
            itemBinding.recycleSection.adapter = SectionAdapter(requestItem.items,{i,po->})
        }

    }

}