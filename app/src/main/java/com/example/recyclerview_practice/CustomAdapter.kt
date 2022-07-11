package com.example.recyclerview_practice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recyclerview_practice.databinding.LayoutItemBinding


class CustomAdapter(
    private val onChangeIsChecked: (itemAtIndex: Int, itemAtPosition: ItemModel) -> Unit,
    private val onMoveItem: (fromPosition: Int, toPosition: Int) -> Unit,
    private val onRemoveItem: (itemAtIndex: Int) -> Unit
    ) : ListAdapter<ItemModel, MyViewHolder>(MyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick = { position ->
                val itemAtPosition = currentList[position]
                this.onChangeIsChecked(position, itemAtPosition)
            },
            onMoveItem = { fromPosition, toPosition ->
                this.onMoveItem(fromPosition, toPosition)
            },
            onRemoveItem = { position ->
                this.onRemoveItem(position)
            }
        )
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

}

