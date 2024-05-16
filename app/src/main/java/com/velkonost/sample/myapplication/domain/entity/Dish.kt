package com.velkonost.sample.myapplication.domain.entity

data class Dish(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Int
) {
    var selected: Boolean = false
}