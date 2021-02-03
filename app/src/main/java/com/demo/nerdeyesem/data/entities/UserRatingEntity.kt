package com.demo.nerdeyesem.data.entities

data class UserRatingEntity(
    val aggregateRating: Double,
    val ratingText: String,
    val ratingColor: String,
    val votes: Long
)
