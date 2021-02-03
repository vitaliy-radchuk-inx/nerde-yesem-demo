package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class RestaurantItemResponse(
    @field:SerializedName("restaurant") val restaurant: RestaurantResponse
)
