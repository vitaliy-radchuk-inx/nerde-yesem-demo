package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @field:SerializedName("results_found") val resultsFound: String,
    @field:SerializedName("results_start") val resultsStart: String,
    @field:SerializedName("results_shown") val resultsShown: String,
    @field:SerializedName("restaurants") val restaurantItems: List<RestaurantItemResponse>
)
