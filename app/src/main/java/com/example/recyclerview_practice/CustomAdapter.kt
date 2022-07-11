package com.example.recyclerview_practice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_practice.databinding.LayoutItemBinding
import com.example.recyclerview_practice.model.ItemModel
import java.util.*

class CustomAdapter(
    private val onChangeIsChecked: (itemAtIndex: Int, itemAtPosition: ItemModel) -> Unit
    ) : ListAdapter<ItemModel, MyViewHolder>(MyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick = { position ->
                val itemAtPosition = currentList[position]
                this.onChangeIsChecked(position, itemAtPosition)
            })
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(getItem(position))

    object MyDiffUtil : DiffUtil.ItemCallback<ItemModel>(){
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        submitList(newList)
    }

    fun removeItem(position: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }
}

