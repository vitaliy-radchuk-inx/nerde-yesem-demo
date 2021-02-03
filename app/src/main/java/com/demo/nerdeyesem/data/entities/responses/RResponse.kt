package com.demo.nerdeyesem.data.entities.responses

import com.google.gson.annotations.SerializedName

data class RResponse(
    @field:SerializedName("resId") val resId: Long,
    @field:SerializedName("is_grocery_store") val isGroceryStore: Boolean,
    @field:SerializedName("has_menu_status") val menuStatus: MenuStatusResponse
)
