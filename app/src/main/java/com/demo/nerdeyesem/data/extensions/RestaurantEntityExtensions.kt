package com.demo.nerdeyesem.data.extensions

import com.demo.nerdeyesem.data.entities.db.RestaurantEntity
import com.demo.nerdeyesem.domain.entities.Restaurant


fun RestaurantEntity.toDomain(): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        url = url,
        cuisines = cuisines,
        timings = timings,
        address = address,
        locality = locality,
        city = city,
        cityId = cityId,
        lat = lat,
        lon = lon,
        zipCode = zipCode,
        localityVerbose = localityVerbose,
        phoneNumbers = phoneNumbers,
        aggregateRating = aggregateRating,
        ratingText = ratingText,
        ratingColor = ratingColor,
        votes = votes,
    )
}
