package com.example.recyclerview_practice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recyclerview_practice.databinding.LayoutItemBinding


class CustomAdapter(private val viewModel: MyViewModel) : ListAdapter<ItemModel, MyViewHolder>(MyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick = { position ->
                val itemAtPosition = currentList[position]
                viewModel.onChangeIsChecked(position, itemAtPosition)
            },
            onMoveItem = { fromPosition, toPosition ->
                viewModel.moveItem(fromPosition, toPosition)
            },
            onRemoveItem = { position ->
                viewModel.removeItem(position)
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

