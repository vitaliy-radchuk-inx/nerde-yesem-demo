package com.demo.nerdeyesem.data.entities.requests

data class SearchRestaurantsRequest(
    val lat: Double,
    val lon: Double,
    val entityType: String,
    val sort: String,
    val order: String
)
