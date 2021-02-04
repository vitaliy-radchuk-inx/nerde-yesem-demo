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
        phones = phoneNumbers,
        address = location.address,
        city = location.city,
        rating = userRating.aggregateRating,
        votes = userRating.votes,
        highlights = highlights.joinToString(",")
    )
}
