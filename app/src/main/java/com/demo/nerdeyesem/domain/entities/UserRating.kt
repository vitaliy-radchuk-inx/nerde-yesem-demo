package com.demo.nerdeyesem.domain.entities

data class UserRating(
    val aggregateRating: Double,
    val ratingText: String,
    val ratingColor: String,
    val votes: Long
)
