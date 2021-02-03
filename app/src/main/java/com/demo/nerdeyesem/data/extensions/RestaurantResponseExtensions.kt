package com.demo.nerdeyesem.data.extensions

import com.demo.nerdeyesem.data.entities.RestaurantEntity
import com.demo.nerdeyesem.data.entities.RestaurantLocationEntity
import com.demo.nerdeyesem.data.entities.UserRatingEntity
import com.demo.nerdeyesem.data.entities.responses.LocationResponse
import com.demo.nerdeyesem.data.entities.responses.RestaurantResponse
import com.demo.nerdeyesem.data.entities.responses.UserRatingResponse


fun RestaurantResponse.toEntity(): RestaurantEntity {
    return RestaurantEntity(
        id = id,
        name = name,
        url = url,
        location = location.toEntity(),
        cuisines = cuisines,
        timings = timings,
        userRating = userRating.toEntity(),
        phoneNumbers = phoneNumbers,
    )
}

fun LocationResponse.toEntity(): RestaurantLocationEntity {
    return RestaurantLocationEntity(
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

fun UserRatingResponse.toEntity(): UserRatingEntity {
    return UserRatingEntity(
        aggregateRating = aggregateRating,
        ratingText = ratingText,
        ratingColor = ratingColor,
        votes = votes,
    )
}