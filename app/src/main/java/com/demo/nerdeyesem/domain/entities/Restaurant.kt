package com.demo.nerdeyesem.domain.entities

data class Restaurant(
    val id: String,
    val name: String,
    val url: String,
    val cuisines: String,
    val timings: String,
    val phoneNumbers: String,
    val address: String,
    val locality: String,
    val city: String,
    val cityId: Long,
    val lat: Double,
    val lon: Double,
    val zipCode: String,
    val localityVerbose: String,
    val aggregateRating: Float,
    val ratingText: String,
    val ratingColor: String,
    val votes: Long
)
