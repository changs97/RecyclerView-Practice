package com.example.recyclerview_practice


interface ItemEvent {
    fun onChangeIsChecked(itemAtIndex: Int, itemAtPosition: ItemModel)

    fun moveItem(fromPosition: Int, toPosition: Int)

    fun removeItem(position: Int)

    fun shuffleItem()
}