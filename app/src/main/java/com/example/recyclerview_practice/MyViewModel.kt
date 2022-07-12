package com.example.recyclerview_practice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class MyViewModel : ViewModel() {
    private lateinit var customAdapter : CustomAdapter
    private val dataSet : MutableLiveData<ArrayList<ItemModel>> = MutableLiveData()

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

    private fun setDataSet(dataSet : List<ItemModel>) {
        this.dataSet.value = dataSet as ArrayList<ItemModel>
        customAdapter.submitList(dataSet)
    }

    fun getData() = dataSet

    fun onChangeIsChecked(itemAtIndex: Int, itemAtPosition: ItemModel)  {
        customAdapter.currentList[itemAtIndex].isChecked = itemAtPosition.isChecked.not()
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = customAdapter.currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        setDataSet(newList)
    }

    fun removeItem(position: Int) {
        val newList = customAdapter.currentList.toMutableList()
        newList.removeAt(position)
        setDataSet(newList)
    }

    fun shuffleItem() {
        val newList = customAdapter.currentList.shuffled()
        setDataSet(newList)
    }
}