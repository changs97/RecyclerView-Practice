package com.example.recyclerview_practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class MyViewModel : ViewModel(), ItemEvent{
    private lateinit var customAdapter : CustomAdapter
    private val _dataSet : MutableLiveData<ArrayList<ItemModel>> = MutableLiveData()
    private val dataSet : LiveData<ArrayList<ItemModel>>
    get() = _dataSet
    init {
        val initDataSet = arrayListOf<ItemModel>().apply {
            add(ItemModel("초코","010-1234-1234"))
            add(ItemModel("코난","010-1234-1234"))
            add(ItemModel("키키","010-1234-1234"))
            add(ItemModel("코코","010-1234-1234"))
            add(ItemModel("치치","010-1234-1234"))
            add(ItemModel("아리","010-1234-1234"))
            add(ItemModel("달이","010-1234-1234"))
        }
        setCustomAdapter(CustomAdapter(this))
        setDataSet(initDataSet)

    }

    private fun setCustomAdapter(customAdapter : CustomAdapter) {
        this.customAdapter = customAdapter
    }

    fun getCustomAdapter() = customAdapter

    private fun setDataSet(newList : List<ItemModel>) {
        this._dataSet.value = newList as ArrayList<ItemModel>
        customAdapter.submitList(newList)
    }

    fun getData() = dataSet

    override fun onChangeIsChecked(itemAtIndex: Int, itemAtPosition: ItemModel)  {
        customAdapter.currentList[itemAtIndex].isChecked = itemAtPosition.isChecked.not()
    }

    override fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = customAdapter.currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        setDataSet(newList)
    }

    override fun removeItem(position: Int) {
        val newList = customAdapter.currentList.toMutableList()
        newList.removeAt(position)
        setDataSet(newList)
    }

    override fun shuffleItem() {
        val newList = customAdapter.currentList.shuffled()
        setDataSet(newList)
    }
}