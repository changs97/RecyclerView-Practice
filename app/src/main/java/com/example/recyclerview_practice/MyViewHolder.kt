package com.example.recyclerview_practice

import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_practice.databinding.LayoutItemBinding

class MyViewHolder(
    private val binding: LayoutItemBinding,
    private val onItemClick: (position: Int) -> Unit,
    val onMoveItem: (fromPosition: Int, toPosition: Int) -> Unit,
    val onRemoveItem: (position: Int) -> Unit

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemModel) {
        with(binding) {
            itemName.text = item.name
            itemNumber.text = item.number
            itemCheck.isChecked = item.isChecked
            itemCheck.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }

    fun setAlpha(alpha: Float) {
        with(binding) {
            itemName.alpha = alpha
            itemNumber.alpha = alpha
            itemCheck.alpha = alpha
        }
    }

}