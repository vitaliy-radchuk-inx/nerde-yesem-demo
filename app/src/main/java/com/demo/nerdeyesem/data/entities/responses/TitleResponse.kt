package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class TitleResponse(
    @field:SerializedName("text") val text: String
)
