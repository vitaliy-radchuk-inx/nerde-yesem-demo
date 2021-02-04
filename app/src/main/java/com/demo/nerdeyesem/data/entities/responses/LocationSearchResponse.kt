package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class LocationSearchResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("has_more") val hasMore: Int,
    @field:SerializedName("has_total") val hasTotal: Int,
    @field:SerializedName("user_has_addresses") val userHasAddresses: Boolean,
    @field:SerializedName("location_suggestions") val locationSuggestions: List<LocationSuggestionResponse>,
)
