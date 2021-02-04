package com.demo.nerdeyesem.presentation.adapters

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.models.RestaurantModel

class RestaurantViewHolder(
    itemView: View,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val restaurantName: TextView = itemView.findViewById(R.id.restaurantName)
    private val rating: RatingBar = itemView.findViewById(R.id.rating)
    private val ratingText: TextView = itemView.findViewById(R.id.ratingText)
    private val cuisines: TextView = itemView.findViewById(R.id.cuisines)
    private var currentItem: RestaurantModel? = null

    init {
        itemView.setOnClickListener {
            currentItem?.let { onClick(it.id) }
        }
    }

    fun bind(restaurant: RestaurantModel) {
        currentItem = restaurant
        restaurantName.text = restaurant.name
        rating.rating = restaurant.rating
        ratingText.text = formatRating(restaurant.rating, restaurant.votes)
        cuisines.text = restaurant.cuisines
    }

    private fun formatRating(rating: Float, votes: Long): String {
        return itemView.context.getString(R.string.restaurant_rating_text, rating, votes)
    }
}