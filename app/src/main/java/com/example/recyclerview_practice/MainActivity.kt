package com.example.recyclerview_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    private val binding by lazy { com.example.recyclerview_practice.databinding.ActivityMainBinding.inflate(layoutInflater)}
    private val viewModel : MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setRecyclerView()

        viewModel.getData().observe(this, androidx.lifecycle.Observer {
            viewModel.getCustomAdapter().submitList(it)
            Log.d("테스트", it.toString())
        })

        binding.btn.setOnClickListener {
            viewModel.shuffleItem()
        }
    }

    private fun setRecyclerView() {
        binding.recyclerview.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = viewModel.getCustomAdapter()
        }

        val itemTouchHelper = ItemTouchHelper(MyItemTouchHelperCallback(binding.recyclerview))
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
    }
}