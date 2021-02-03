package com.demo.nerdeyesem.data.extensions

import com.demo.nerdeyesem.data.entities.RestaurantLocationEntity
import com.demo.nerdeyesem.data.entities.RestaurantEntity
import com.demo.nerdeyesem.data.entities.UserRatingEntity
import com.demo.nerdeyesem.domain.entities.Restaurant
import com.demo.nerdeyesem.domain.entities.RestaurantLocation
import com.demo.nerdeyesem.domain.entities.UserRating


fun RestaurantEntity.toDomain(): Restaurant {
    return Restaurant(
        id = id,
        name = name,
        url = url,
        location = location.toDomain(),
        cuisines = cuisines,
        timings = timings,
        userRating = userRating.toDomain(),
        phoneNumbers = phoneNumbers,
    )
}

fun RestaurantLocationEntity.toDomain(): RestaurantLocation {
    return RestaurantLocation(
        address = address,
        locality = locality,
        city = city,
        cityId = cityId,
        lat = lat,
        lon = lon,
        zipCode = zipCode,
        localityVerbose = localityVerbose,
    )
}

fun UserRatingEntity.toDomain(): UserRating {
    return UserRating(
        aggregateRating = aggregateRating,
        ratingText = ratingText,
        ratingColor = ratingColor,
        votes = votes,
    )
}