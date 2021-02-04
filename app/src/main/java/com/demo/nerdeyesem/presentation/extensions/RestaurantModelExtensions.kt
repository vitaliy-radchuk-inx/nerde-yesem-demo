package com.demo.nerdeyesem.presentation.extensions

import com.demo.nerdeyesem.domain.entities.Restaurant
import com.demo.nerdeyesem.presentation.models.RestaurantModel

fun Restaurant.toModel(): RestaurantModel {
    return RestaurantModel(
        id = id,
        name = name,
        url = url,
        cuisines = cuisines,
        timings = timings,
        phones = phones,
        address = address,
        city = city,
        rating = rating,
        votes = votes,
        highlights = highlights
    )
}