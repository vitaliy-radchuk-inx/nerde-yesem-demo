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
        phoneNumbers = phoneNumbers,
        address = address,
        locality = locality,
        city = city,
        cityId = cityId,
        lat = lat,
        lon = lon,
        zipCode = zipCode,
        localityVerbose = localityVerbose,
        aggregateRating = aggregateRating,
        ratingText = ratingText,
        ratingColor = ratingColor,
        votes = votes,
    )
}