package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class LocationSuggestionResponse(
    @field:SerializedName("entity_type") val entityType: String,
    @field:SerializedName("entity_id") val entityId: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("latitude") val lat: Float,
    @field:SerializedName("longitude") val lon: Float,
    @field:SerializedName("city_id") val cityId: Int,
    @field:SerializedName("city_name") val cityName: String,
    @field:SerializedName("country_id") val countryId: Int,
    @field:SerializedName("country_name") val countryName: String,
)

