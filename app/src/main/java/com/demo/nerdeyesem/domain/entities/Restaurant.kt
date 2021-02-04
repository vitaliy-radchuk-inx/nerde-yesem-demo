package com.demo.nerdeyesem.domain.entities

data class Restaurant(
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
