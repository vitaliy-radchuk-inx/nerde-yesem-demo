package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class RatingObjResponse(
    @field:SerializedName("title") val title: TitleResponse
)
