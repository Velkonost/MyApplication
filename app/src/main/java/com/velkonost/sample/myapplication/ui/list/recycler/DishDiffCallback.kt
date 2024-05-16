package com.velkonost.sample.myapplication.ui.list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.velkonost.sample.myapplication.domain.entity.Dish

class DishDiffCallback : DiffUtil.ItemCallback<Dish>() {

    override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem.id == newItem.id
    }

}