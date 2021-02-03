package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @field:SerializedName("address") val address: String,
    @field:SerializedName("locality") val locality: String,
    @field:SerializedName("city") val city: String,
    @field:SerializedName("city_id") val cityId: Long,
    @field:SerializedName("latitude") val lat: Double,
    @field:SerializedName("longitude") val lon: Double,
    @field:SerializedName("zipcode") val zipCode: String,
    @field:SerializedName("locality_verbose") val localityVerbose: String,
)
