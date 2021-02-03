package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class AllReviewsResponse(
    @field:SerializedName("reviews") val reviews: List<Any>
)
