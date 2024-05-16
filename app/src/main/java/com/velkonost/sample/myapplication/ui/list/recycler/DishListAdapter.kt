package com.velkonost.sample.myapplication.ui.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.velkonost.sample.myapplication.R
import com.velkonost.sample.myapplication.databinding.ItemDishBinding
import com.velkonost.sample.myapplication.domain.entity.Dish

class DishListAdapter(
    private val onCheckedChange: (index: Int, selected: Boolean) -> Unit,
    private val onItemClick: (itemId: String) -> Unit
) : ListAdapter<Dish, DishListAdapter.ViewHolder>(DishDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDishBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemDishBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Dish) = with(binding) {
                dish = item

                container.setOnClickListener {
                    onItemClick.invoke(item.id)
                }

                checkbox.setOnCheckedChangeListener { _, isChecked ->
                    onCheckedChange.invoke(bindingAdapterPosition, isChecked)
                }

                executePendingBindings()
            }
    }
}
