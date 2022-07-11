package com.example.recyclerview_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val binding by lazy { com.example.recyclerview_practice.databinding.ActivityMainBinding.inflate(layoutInflater)}
    private lateinit var dataSet: ArrayList<ItemModel>
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setRecyclerView()

        binding.btn.setOnClickListener {
            setDataSet(customAdapter.currentList.shuffled())
        }

    }

    private fun setRecyclerView() {
        dataSet = arrayListOf<ItemModel>().apply {
            add(ItemModel("초코","010-1234-1234"))
            add(ItemModel("코난","010-1234-1234"))
            add(ItemModel("키키","010-1234-1234"))
            add(ItemModel("코코","010-1234-1234"))
            add(ItemModel("치치","010-1234-1234"))
            add(ItemModel("아리","010-1234-1234"))
            add(ItemModel("달이","010-1234-1234"))
        }
        customAdapter = CustomAdapter(::onChangeIsChecked, ::moveItem, ::removeItem)
        customAdapter.submitList(dataSet)

        binding.recyclerview.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = customAdapter
        }

        val itemTouchHelper = ItemTouchHelper(MyItemTouchHelperCallback(binding.recyclerview))
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)

    }

    private fun setDataSet(newData : List<ItemModel>) {
        customAdapter.submitList(newData)
    }

    private fun onChangeIsChecked(itemAtIndex: Int, itemAtPosition: ItemModel)  {
        customAdapter.currentList[itemAtIndex].isChecked = !itemAtPosition.isChecked
    }

    private fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = customAdapter.currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        customAdapter.submitList(newList)
    }

    private fun removeItem(position: Int) {
        val newList = customAdapter.currentList.toMutableList()
        newList.removeAt(position)
        customAdapter.submitList(newList)
    }

}