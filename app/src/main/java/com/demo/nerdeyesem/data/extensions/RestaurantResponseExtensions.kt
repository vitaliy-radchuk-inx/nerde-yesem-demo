package com.demo.nerdeyesem.data.extensions

import com.demo.nerdeyesem.data.entities.db.RestaurantEntity
import com.demo.nerdeyesem.data.entities.responses.RestaurantResponse


fun RestaurantResponse.toEntity(): RestaurantEntity {
    return RestaurantEntity(
        id = id,
        name = name,
        url = url,
        cuisines = cuisines,
        timings = timings,
        phoneNumbers = phoneNumbers,
        address = location.address,
        locality = location.locality,
        city = location.city,
        cityId = location.cityId,
        lat = location.lat,
        lon = location.lon,
        zipCode = location.zipCode,
        localityVerbose = location.localityVerbose,
        aggregateRating = userRating.aggregateRating,
        ratingText = userRating.ratingText,
        ratingColor = userRating.ratingColor,
        votes = userRating.votes
    )
}
