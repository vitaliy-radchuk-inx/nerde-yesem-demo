package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class UserRatingResponse(
    @field:SerializedName("aggregate_rating") val aggregateRating: Float,
    @field:SerializedName("rating_text") val ratingText: String,
    @field:SerializedName("rating_color") val ratingColor: String,
    @field:SerializedName("rating_obj") val ratingObj: RatingObjResponse,
    @field:SerializedName("bg_color") val bgColor: BgColorResponse,
    @field:SerializedName("votes") val votes: Long
)
