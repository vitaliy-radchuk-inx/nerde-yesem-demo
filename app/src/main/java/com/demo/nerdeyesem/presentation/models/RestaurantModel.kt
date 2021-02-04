package com.demo.nerdeyesem.presentation.models

data class RestaurantModel(
    val id: String,
    val name: String,
    val url: String,
    val cuisines: String,
    val timings: String,
    val phones: String,
    val address: String,
    val city: String,
    val rating: Float,
    val votes: Long,
    val highlights: String
)
