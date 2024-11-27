package com.example.marvelapp.feature.marvelList.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.RowMarvelBinding
import com.example.marvelapp.feature.marvelList.domain.models.Character
import com.example.marvelapp.utils.showImgGlide

class CharacterAdapter(private var mItemsList: ArrayList<Character>) : RecyclerView.Adapter<CharacterViewHolder>() {

     var onItemClick: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = RowMarvelBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(mItemsList[position], onItemClick)

    }

    override fun getItemCount(): Int = mItemsList.size


    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(mItemsList: ArrayList<Character>) {
        this.mItemsList = mItemsList
        notifyDataSetChanged()
    }

}

class CharacterViewHolder(private val itemBinding: RowMarvelBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private var requestItem: Character? = null


    fun bind(requestItem: Character,onItemClick: ((Character) -> Unit)?) {
        this.requestItem = requestItem

        itemBinding.root.context.showImgGlide(itemBinding.imgMarvel,itemBinding.loading,requestItem.thumbnail?.path?:"" ,requestItem.thumbnail?.extension?:"")
        itemBinding.txtName.text = requestItem.name

        itemBinding.imgMarvel.setOnClickListener {
            onItemClick?.invoke(requestItem)
        }
    }

}