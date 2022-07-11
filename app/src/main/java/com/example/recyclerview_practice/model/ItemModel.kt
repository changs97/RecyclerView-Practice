package com.example.recyclerview_practice.model

data class ItemModel (
    val name : String,
    val number: String,
    var isChecked : Boolean = false
        )