package com.demo.nerdeyesem.domain.entities

data class Restaurant(
    val id: String,
    val name: String,
    val url: String,
    val location: RestaurantLocation,
    val cuisines: String,
    val timings: String,
    val userRating: UserRating,
    val phoneNumbers: String,
)
