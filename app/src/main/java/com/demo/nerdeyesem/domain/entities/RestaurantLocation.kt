package com.demo.nerdeyesem.domain.entities

data class RestaurantLocation(
    val address: String,
    val locality: String,
    val city: String,
    val cityId: Long,
    val lat: Double,
    val lon: Double,
    val zipCode: String,
    val localityVerbose: String,
)