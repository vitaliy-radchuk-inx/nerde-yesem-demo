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
        city = city,
        phones = phones,
        rating = rating,
        votes = votes,
        highlights = highlights
    )
}
