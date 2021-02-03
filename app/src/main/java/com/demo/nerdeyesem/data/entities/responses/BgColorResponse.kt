package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class BgColorResponse(
    @field:SerializedName("type") val type: String,
    @field:SerializedName("tint") val tint: String
)
