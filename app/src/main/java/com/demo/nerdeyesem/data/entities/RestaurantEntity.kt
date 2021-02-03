package com.demo.nerdeyesem.data.entities

data class RestaurantEntity(
    val id: String,
    val name: String,
    val url: String,
    val location: RestaurantLocationEntity,
    val cuisines: String,
    val timings: String,
    val userRating: UserRatingEntity,
    val phoneNumbers: String,
)
