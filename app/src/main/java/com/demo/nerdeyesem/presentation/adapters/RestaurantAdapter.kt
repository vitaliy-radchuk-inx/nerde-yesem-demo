package com.demo.nerdeyesem.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.models.RestaurantModel

class RestaurantAdapter(
    private val onClick: (String) -> Unit
) : ListAdapter<RestaurantModel, RestaurantViewHolder>(RestaurantDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object RestaurantDiffCallback : DiffUtil.ItemCallback<RestaurantModel>() {
    override fun areItemsTheSame(oldItem: RestaurantModel, newItem: RestaurantModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RestaurantModel, newItem: RestaurantModel): Boolean {
        return oldItem.id == newItem.id
    }
}