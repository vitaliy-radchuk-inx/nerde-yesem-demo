package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class MenuStatusResponse(
     @field:SerializedName("delivery") val delivery: Int,
     @field:SerializedName("takeaway") val takeaway: Int
)
